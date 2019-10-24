package com.example.watermyplants.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.watermyplants.api.ApiBuilder
import com.example.watermyplants.models.EditPlant
import com.example.watermyplants.models.Plant
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

object PlantListDao {

    private val disposableCreatePlant = CompositeDisposable()

    private val _plantCreated = MutableLiveData<Plant>()
    val plantCreated: LiveData<Plant> = _plantCreated

    fun postPlant(token: String, plant: EditPlant){
        val observable = ApiBuilder.apiCall().createPlant(token, plant)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
        disposableCreatePlant.add(observable.subscribeWith(object: DisposableObserver<Plant>(){
            override fun onComplete() {}

            override fun onNext(t: Plant) {
                _plantCreated.value = t
            }

            override fun onError(e: Throwable) {
                println(e)
                _plantCreated.value = null
            }

        }))
    }


    private val disposablePlantList = CompositeDisposable()

    private val _plantList = MutableLiveData<List<Plant>>()
    val plantList = _plantList

    fun resetPlantList(){
        _plantList.value = null
        _plantCreated.value = null
        _plantDeleted.value = null
        _plantUpdated.value = null
    }

    fun getPlants(token: String){
        val observable = ApiBuilder.apiCall().getPlants(token)
            .toObservable()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
        disposablePlantList.add(observable.subscribeWith(object: DisposableObserver<List<Plant>>(){
            override fun onComplete() {}

            override fun onNext(t: List<Plant>) {
                _plantList.value = t
            }

            override fun onError(e: Throwable) {
                println(e)
            }

        }))
    }

    private val disposableUpdatePlant = CompositeDisposable()

    private val _plantUpdated = MutableLiveData<Int>()
    val plantUpdated: LiveData<Int> = _plantUpdated

    fun updatePlant(token: String, plant: Plant){
        val observable = ApiBuilder.apiCall().updatePlant(token, plant)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
        disposableUpdatePlant.add(observable.subscribeWith(object: DisposableObserver<List<Int>>(){
            override fun onComplete() {}

            override fun onNext(t: List<Int>) {
                _plantUpdated.value = t[0]
            }

            override fun onError(e: Throwable) {
                println(e)
                _plantCreated.value = null
            }

        }))
    }

    private val disposableDeletePlant = CompositeDisposable()

    private val _plantDeleted = MutableLiveData<Plant>()
    val plantDeleted: LiveData<Plant> = _plantDeleted

    fun deletePlant(token: String, plant: Plant){
        val observable = ApiBuilder.apiCall().deletePlant(token, plant)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
        disposableDeletePlant.add(observable.subscribeWith(object: DisposableObserver<Plant>(){
            override fun onComplete() {}

            override fun onNext(t: Plant) {
                _plantDeleted.value = t
            }

            override fun onError(e: Throwable) {
                println(e)
                _plantDeleted.value = null
            }

        }))
    }




}