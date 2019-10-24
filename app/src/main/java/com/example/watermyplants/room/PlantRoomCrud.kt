package com.example.watermyplants.room

import androidx.room.*
import com.example.watermyplants.models.Plant
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import java.util.*

@Dao
interface PlantRoomCrud {

    @Query("SELECT * FROM plant_table")
    fun getAllPlants(): Flowable<List<Plant>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(plant: Plant): Single<Long>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(plant: Plant)

    @Delete
    fun deletePlant(plant: Plant)

}