package com.example.watermyplants.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "plant_table")
class Plant (
    val nickname: String,
    val species: String,
    val h2oFrequency: Int,
    val user_id: Int,
    @PrimaryKey(autoGenerate = true)val id: Int = 0
): Serializable

class EditPlant (
    val nickname: String,
    val species: String,
    val h2oFrequency: Int
)