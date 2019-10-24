package com.example.watermyplants

import androidx.lifecycle.LiveData
import com.example.watermyplants.api.ApiBuilder
import com.example.watermyplants.api.ApiCrud
import com.example.watermyplants.models.Plant
import com.example.watermyplants.repo.PlantListDao
import com.example.watermyplants.viewmodel.PlantListViewModel
import com.nhaarman.mockitokotlin2.verify
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.Single
import kotlinx.android.synthetic.main.create_plant_list_item.view.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricTestRunner
import kotlin.test.assertEquals

//@RunWith(RobolectricTestRunner.class) this don't even work
class PlantTest {

    @Test
    fun testPlant(){
        val nickname = "Birch Tree"
        val species = "Tree"
        val h2oFrequency = 2
        val userId = 1
        val id = 1

        val plantTest = Plant(nickname, species, h2oFrequency, userId, id)

        assertEquals( nickname,     plantTest.nickname )
        assertEquals( species,      plantTest.species )
        assertEquals( h2oFrequency, plantTest.h2oFrequency )
        assertEquals( userId,       plantTest.user_id )
        assertEquals( id,           plantTest.id )

    }

    private val plantListViewModel = mock(PlantListViewModel::class.java)

    @Mock
    val observer: Observer<List<Plant>>? = null

    @Mock
    val api: ApiCrud? = null

    @Mock
    val dao: PlantListDao? = null

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun plantListViewModel(){
        val token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VybmFtZSI6InR5bGVyIiwiaWQiOjEsImlhdCI6MTU3MTg2NjM1OSwiZXhwIjoxNTcxOTUyNzU5fQ.J8EZGEbyGd9WMpEuozlHin4_JM4pGvDZvkuw7SJqvPk"
        val test = listOf<Plant>()

        `when` (api?.getPlants(token)).thenReturn(Observable.just(test)) // Does this act as my call when I use the line below?
        //assertEquals(observer, api?.getPlants(token))
        plantListViewModel.getPlants(token)
        api?.getPlants(token)?.subscribe {
            assertEquals(test, it)
        }
        dao?.getPlants(token)



        //plantListViewModel.getPlants(token) // what is this used for





    }

}