package com.example.watermyplants

import com.example.watermyplants.models.Plant
import com.example.watermyplants.models.User
import org.junit.Test
import kotlin.test.assertEquals

class PlantControllerUnitTest {

    @Test
    fun testPlant() {
        val nickname = "Birch Tree"
        val species = "Tree"
        val h2oFrequency = 2
        val userId = 1
        val id = 1

        val plantTest = Plant(nickname, species, h2oFrequency, userId, id)

        assertEquals(nickname, plantTest.nickname)
        assertEquals(species, plantTest.species)
        assertEquals(h2oFrequency, plantTest.h2oFrequency)
        assertEquals(userId, plantTest.user_id)
        assertEquals(id, plantTest.id)

    }
}
//
//    private val repo: Repo = Repo()
//    private val apiUserCrud = Mockito.mock(UserDao::class.java)
//    private val apiPlantCrud = Mockito.mock(PlantListDao::class.java)
//    private val user = Mockito.mock(User::class.java)
//    private val plant = Mockito.mock(Plant::class.java)
//
//    @Test
//    fun loginTest(){
//        val username = "msmaitran"
//        val password = "password123"
//        val expected: LiveData<Boolean>? = LiveData(true)
//
//        `when` (apiUserCrud?.login(username, password)).thenReturn(expected)
//
//        val result = repo.loginSuccessful(username, password)
//    }
//
//}