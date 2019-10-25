package com.example.watermyplants

import com.example.watermyplants.controllers.RegisterController
import com.example.watermyplants.models.User
import com.example.watermyplants.util.isNotBlank
import kotlin.test.assertEquals
import org.junit.Test

class RegisterControllerUnitTest {
    private val registerControllerUnitTest = RegisterController()

    @Test
    fun passwordVerified(){
        // Setup
        val password = "password123"
        val confirmPassword = "password123"
        val expected = true

        // Execute
        val result = (password == confirmPassword)

        // Check
        assertEquals(expected, result)
    }

    @Test
    fun passwordCheck(){
        // Setup
        val password = "password123"
        val confirmPassword = "password"
        val expected = false

        // Execute
        val result = (password == confirmPassword)

        // Check
        assertEquals(expected, result)
    }

    @Test
    fun passwordCapsCheck(){
        // Setup
        val password = "Password123"
        val confirmPassword = "password123"
        val expected = false

        // Execute
        val result = (password == confirmPassword)

        // Check
        assertEquals(expected, result)
    }

    @Test
    fun testUser(){
        // Setup
        val username = "msmaitran"
        val password = "password123"
        val phoneNumber = "0123456789"
        val id = 2

        // Execute
        val userTest = User(username, password, phoneNumber, id)

        // Check
        assertEquals(username, userTest.username)
        assertEquals(password, userTest.password)
        assertEquals(phoneNumber, userTest.phoneNumber)
        assertEquals(id, userTest.id)
    }
}