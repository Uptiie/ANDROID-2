package com.example.watermyplants.models

class Plant (
    val nickname: String,
    val species: String,
    val h2oFrequency: String,
    val user_id: Int,

    val id: Int = 0
)

class EditPlant (
    val nickname: String,
    val species: String,
    val h2oFrequency: String
)