package com.example.watermyplants.viewmodel

import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.example.watermyplants.api.ApiCrud
import com.example.watermyplants.models.Plant
import com.example.watermyplants.models.UserToken
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

class TestViewModel constructor(val authApi: ApiCrud): ViewModel() {

    val plants = MediatorLiveData<List<Plant>>()

    fun getPlants(token: String) {
        val source = LiveDataReactiveStreams
            .fromPublisher(authApi.getPlants(token)
                .subscribeOn(Schedulers.io())
            )
        plants.addSource(source) {
            plants.value = it
            plants.removeSource(source)
        }
    }


}