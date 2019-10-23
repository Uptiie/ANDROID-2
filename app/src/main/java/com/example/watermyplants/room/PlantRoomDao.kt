package com.example.watermyplants.room

import androidx.room.*
import com.example.watermyplants.models.Plant

@Dao
interface PlantRoomDao {

    @Query("SELECT * FROM plant_table")
    fun getAllPlants(): MutableList<Plant>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(plant: Plant)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(plant: Plant)

    @Delete
    fun deletePlant(plant: Plant)

}