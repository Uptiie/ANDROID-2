package com.example.watermyplants.repo

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.example.watermyplants.models.*
import com.example.watermyplants.room.PlantDatabase
import io.reactivex.FlowableSubscriber
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.internal.operators.single.SingleObserveOn
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import org.reactivestreams.Subscriber

class Repo(context: Context) {

    // User

    fun getLoginToken(user: User){
        UserDao.getLoginToken(user)
    }

    fun loginSuccessful(): LiveData<Boolean>{
        return UserDao.loginSuccessful
    }

    fun resetLoginCheck(){
        UserDao.resetLoginCheck()
    }

    fun registerUser(user: EditUser){
        UserDao.registerUser(user)
    }

    fun registerSuccessful(): LiveData<Boolean>{
        return UserDao.registerSuccessful
    }

    fun updateUser(token: String, user: EditUser){
        UserDao.updateUser(token, user)
    }

    fun userUpdated(): LiveData<Int>{
        return UserDao.updateSuccessful
    }


    // Plant

    fun createPlant(token: String, plant: EditPlant){
        PlantListDao.postPlant(token, plant)
    }

    fun plantCreated(): LiveData<Plant>{
        return PlantListDao.plantCreated
    }

    fun getPlants(token: String){
        PlantListDao.getPlants(token)
    }

    fun getPlantList(): LiveData<List<Plant>>{
        return PlantListDao.plantList
    }

    fun resetPlantList(){
        PlantListDao.resetPlantList()
    }

    fun updatePlant(token: String, plant: Plant){
        PlantListDao.updatePlant(token, plant)
    }

    fun plantUpdated(): LiveData<Int>{
        return PlantListDao.plantUpdated
    }

    fun deletePlant(token: String, plant: Plant){
        PlantListDao.deletePlant(token, plant)
    }

    fun plantDeleted(): LiveData<Plant>{
        return PlantListDao.plantDeleted
    }

    // Room


    val db = Room.databaseBuilder(
        context,
        PlantDatabase::class.java,
        "plant-table"
    ).fallbackToDestructiveMigration().build()


    fun addPlantRoom(plant: Plant){
        db.plantDao().insert(plant)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object: SingleObserver<Long> {
                override fun onSuccess(t: Long) {
                    println("nice")
                }

                override fun onSubscribe(d: Disposable) {
                    println("nice")
                }

                override fun onError(e: Throwable) {
                    println(e)
                }

            })
    }

    private val _allPlantsRoom = MutableLiveData<List<Plant>>()
    val allPlantsRoom: LiveData<List<Plant>> = _allPlantsRoom

    @SuppressLint("CheckResult")
    fun getPlantsRoom(){
        db.plantDao().getAllPlants()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {_allPlantsRoom.value = it},
                {e -> println(e)}
            )
    }







}