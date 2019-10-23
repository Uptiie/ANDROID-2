package com.example.watermyplants.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.watermyplants.App.Companion.repo
import com.example.watermyplants.models.EditPlant
import com.example.watermyplants.models.Plant
import com.example.watermyplants.repo.PlantListDao

class PlantListViewModel: ViewModel() {

    fun createPlant(token: String, plant: EditPlant){
        repo?.createPlant(token, plant)
    }

    fun plantCreated(): LiveData<Plant>?{
        return repo?.plantCreated()
    }

    fun getPlants(token: String){
        repo?.getPlants(token)
    }

    fun getPlantList(): LiveData<List<Plant>>?{
        return repo?.getPlantList()
    }

    fun resetPlantList(){
        repo?.resetPlantList()
    }

    fun updatePlant(token: String, plant: Plant){
        repo?.updatePlant(token, plant)
    }

    fun plantUpdated(): LiveData<Int>?{
        return repo?.plantUpdated()
    }

    fun deletePlant(token: String, plant: Plant){
        repo?.deletePlant(token, plant)
    }

    fun plantDeleted(): LiveData<Plant>?{
        return repo?.plantDeleted()
    }

}