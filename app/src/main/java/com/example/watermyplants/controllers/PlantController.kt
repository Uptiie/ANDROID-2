package com.example.watermyplants.controllers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.bluelinelabs.conductor.RouterTransaction
import com.bluelinelabs.conductor.changehandler.HorizontalChangeHandler
import com.example.watermyplants.App
import com.example.watermyplants.R
import com.example.watermyplants.models.EditPlant
import com.example.watermyplants.models.Plant
import com.example.watermyplants.util.showToast
import com.example.watermyplants.viewmodel.PlantListViewModel
import kotlinx.android.synthetic.main.create_plant_list_item.view.*
import work.beltran.conductorviewmodel.ViewModelController

class PlantController : ViewModelController {

    constructor() : super()
    constructor(args: Bundle?) : super(args)

    // Inflate Create Plant List Item
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        val view = inflater.inflate(R.layout.create_plant_list_item, container, false)

        val viewModel = viewModelProvider().get(PlantListViewModel::class.java)

        val token = App.sharedPref?.getString(App.TOKEN_KEY, "")

        // When "Add" is clicked, the plant_list will inflate from the PlantListController
        val nickname = view?.et_nickname_add
        val species = view?.et_species_add
        val frequency = view?.et_h20frequency_int_add



        view?.btn_listing_add?.setOnClickListener {
            val nicknameValue = nickname?.text.toString()
            val speciesValue = species?.text.toString()
            val frequencyValue = frequency?.text.toString()
            if (nicknameValue.isNotEmpty() && speciesValue.isNotEmpty() && frequencyValue.isNotEmpty()){
                val plant = EditPlant(nicknameValue, speciesValue, frequencyValue.toInt())
                if (token != null){
                    viewModel.createPlant(token, plant)
                    viewModel.plantCreated()?.observe(this, Observer<Plant>{
                        if (it != null){
                            view.context.showToast("Plant created")
                            router.pushController(
                                RouterTransaction.with(PlantListController(args))
                                    .pushChangeHandler(HorizontalChangeHandler())
                                    .popChangeHandler(HorizontalChangeHandler())
                            )
                        } else {
                            view.context.showToast("Failed to create plant")
                        }
                    })
                }
            }
        }
        return view
    }
}