package com.example.watermyplants.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.watermyplants.models.Plant

@Database(entities = [Plant::class], version = 1, exportSchema = false)
abstract class PlantDatabase : RoomDatabase() {

    abstract fun plantDao(): PlantRoomCrud

}