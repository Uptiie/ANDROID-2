package com.example.watermyplants.controllers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.Observer
import com.bluelinelabs.conductor.*
import com.bluelinelabs.conductor.changehandler.HorizontalChangeHandler
import com.bluelinelabs.conductor.changehandler.VerticalChangeHandler
import com.example.watermyplants.App
import com.example.watermyplants.R
import com.example.watermyplants.models.Plant
import com.example.watermyplants.util.hideKeyboard
import com.example.watermyplants.util.showToast
import com.example.watermyplants.viewmodel.PlantListViewModel
import kotlinx.android.synthetic.main.plant_details_layout.view.*
import work.beltran.conductorviewmodel.ViewModelController

class PlantUpdateController : ViewModelController {

    constructor() : super()
    constructor(args: Bundle?) : super(args){
        args?.getSerializable(PlantListController.PLANT_KEY)
    }

    lateinit var viewModel: PlantListViewModel

    // Inflate Plant Details Layout
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        val view = inflater.inflate(R.layout.plant_details_layout, container, false)

        viewModel = viewModelProvider().get(PlantListViewModel::class.java)

        val plant = args.getSerializable(PlantListController.PLANT_KEY) as Plant

        val checkNullToken = App.sharedPref?.getString(App.TOKEN_KEY, "")

        val nickname = view.et_nickname_details
        val species = view.et_species_details
        val frequency = view.et_h20frequency_int_details

        nickname.setText(plant.nickname)
        species.setText(plant.species)
        frequency.setText(plant.h2oFrequency.toString())
        var token = ""

        if (checkNullToken != null){
            token = checkNullToken
        }

        // When "Cancel" button is clicked, the plant_list will inflate from the PlantListController
        view?.btn_listing_cancel_details?.setOnClickListener {
            view.hideKeyboard()
            returnToList(router)
        }

        // When "Update" button is clicked, the plant_list will inflate from the PlantListController
        view?.btn_listing_update_details?.setOnClickListener {
            val nicknameValue = nickname.text.toString()
            val speciesValue = species.text.toString()
            val frequencyValue = frequency.text.toString().toInt()

            val updatedPlant = Plant(nicknameValue, speciesValue, frequencyValue, plant.user_id, plant.id)

            viewModel.updatePlant(token, updatedPlant)

            viewModel.plantUpdated()?.observe(this, Observer<Int>{
                if (it != null) {
                    if (it != -1) {
                        view.context.showToast("Plant Updated")
                        view.hideKeyboard()
                        returnToList(router)
                    } else view.context.showToast("Failed to create plant")
                }
            })
        }

        // When "Delete" button is clicked, the plant_list will inflate from the PlantListController
        view?.btn_listing_delete_details?.setOnClickListener {

            viewModel.deletePlant(token, plant)

            viewModel.plantDeleted()?.observe(this, Observer<Plant>{
                if (it != null){
                    view.context.showToast("Plant Deleted")
                    view.hideKeyboard()
                    returnToList(router)
                }
            })
        }

        return view
    }

    override fun onChangeEnded(changeHandler: ControllerChangeHandler, changeType: ControllerChangeType) {
        super.onChangeEnded(changeHandler, changeType)

        viewModel.resetPlantList()

    }

}

fun returnToList(router: Router){
    router.popCurrentController()
}