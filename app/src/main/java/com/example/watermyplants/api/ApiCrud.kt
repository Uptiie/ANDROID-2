package com.example.watermyplants.api

import com.example.watermyplants.models.EditPlant
import com.example.watermyplants.models.EditUser
import com.example.watermyplants.models.Plant
import com.example.watermyplants.models.User
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.*

interface ApiCrud{



    // User
    @GET("user")
    fun allUsers(@Header("Authorization") token: String): Call<List<User>>

    @POST("user/register")
    fun register(@Body user: EditUser): Call<EditUser>

    @POST("user/login")
    fun login(@Body user: User): Observable<User>

    @GET("user/single_user")
    fun currentUser(@Header("Authorization") token: String): Call<User>

    @PUT("user")
    fun updateInfo(@Header("Authorization") token: String, @Body user: EditUser): Call<EditUser>

    // Plant
    @GET("plants")
    fun getPlants(@Header("Authorization") token: String): Call<List<Plant>>

    @POST("plants")
    fun createPlant(@Header("Authorization") token: String, @Body plant: EditPlant): Call<Plant>

    @DELETE("plants")
    fun deletePlant(@Header("Authorization") token: String, @Body plant: Plant): Call<Plant>

    @PUT("plants")
    fun updatePlant(@Header("Authorization") token: String, @Body plant: Plant): Call<Plant>

}
