package com.example.watermyplants

import com.example.watermyplants.models.EditPlant
import org.junit.Test
import kotlin.test.assertEquals

class PlantUpdateControllUnitTest {

    @Test
    fun editPlantTest() {
    // Setup
    val nickname = "Afterglow"
    val species = "Echeveria"
    val frequency = 7

    // Execute
    val testEditPlant = EditPlant(nickname, species, frequency)

    // Check
    assertEquals(nickname, testEditPlant.nickname)
    assertEquals(species, testEditPlant.species)
    assertEquals(frequency, testEditPlant.h2oFrequency)
    }
}