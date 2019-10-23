package com.example.watermyplants.repo

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.watermyplants.models.*

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





}