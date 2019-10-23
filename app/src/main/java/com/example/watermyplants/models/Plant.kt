package com.example.watermyplants.models

import java.io.Serializable

class Plant (
    val nickname: String,
    val species: String,
    val h2oFrequency: Int,
    val user_id: Int,

    val id: Int = 0
): Serializable

class EditPlant (
    val nickname: String,
    val species: String,
    val h2oFrequency: Int
)