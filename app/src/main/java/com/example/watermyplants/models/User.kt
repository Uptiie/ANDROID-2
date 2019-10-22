package com.example.watermyplants.models

class User (
    val username: String,
    val password: String,
    val phoneNumber: String = "",
    val token: String = "",

    val id: Int = 0
)

class EditUser (
    val username: String,
    val password: String,
    val phoneNumber: String
)