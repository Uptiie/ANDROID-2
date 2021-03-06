package com.example.watermyplants.api


import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

object ApiBuilder {
    private const val BASE_URL = "https://build-week-4.herokuapp.com/api/"
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    fun apiCall(): ApiCrud {
        return retrofit.create(ApiCrud::class.java)
    }

}