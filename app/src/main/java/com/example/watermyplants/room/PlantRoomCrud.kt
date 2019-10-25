package com.example.watermyplants.room

import androidx.room.*
import com.example.watermyplants.models.Plant
import io.reactivex.Flowable
import io.reactivex.Single

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