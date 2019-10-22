package com.example.watermyplants.models

class User (
    val username: String,
    val password: String,
    val phoneNumber: String = "",

    val id: Int = 0
)

class UserToken (
    val token: String
)

class EditUser (
    val username: String,
    val password: String,
    val phoneNumber: String
)