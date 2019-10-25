package com.example.watermyplants

import com.example.watermyplants.models.EditUser
import org.junit.Test
import kotlin.test.assertEquals

class ProfileControllerUnitTest {

    @Test
    fun updateProfileTest(){
        // Setup
        val username = "msmaitran"
        val password = "password"
        val phoneNumber = "1234567890"

        // Execute
        val testUpdateProfile = EditUser(username, password, phoneNumber)

        // Check
        assertEquals(username, testUpdateProfile.username)
        assertEquals(password, testUpdateProfile.password)
        assertEquals(phoneNumber, testUpdateProfile.phoneNumber)
    }
}