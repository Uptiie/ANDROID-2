package com.example.watermyplants.repo

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.watermyplants.api.ApiBuilder
import com.example.watermyplants.models.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

class Repo(context: Context) {

    // User

    fun getLoginToken(user: User){
        AuthDao.getLoginToken(user)
    }

    fun loginSuccessful(): LiveData<Boolean>{
        return AuthDao.loginSuccessful
    }

    fun resetLoginCheck(){
        AuthDao.resetLoginCheck()
    }

    fun registerUser(user: EditUser){
        AuthDao.registerUser(user)
    }

    fun registerSuccessful(): LiveData<Boolean>{
        return AuthDao.registerSuccessful
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





}