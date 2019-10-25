package com.example.watermyplants

import com.example.watermyplants.models.UserToken
import org.junit.Test
import kotlin.test.assertEquals

class RootControllerUnitTest {

    @Test
    fun tokenTest(){
        // Setup
        val token = "tokentest123"

        // Execute
        val testToken = UserToken(token)

        // Check
        assertEquals(token, testToken.token)
    }

}