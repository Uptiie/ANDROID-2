package com.example.watermyplants.controllers

import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.bluelinelabs.conductor.ControllerChangeHandler
import com.bluelinelabs.conductor.ControllerChangeType
import com.bluelinelabs.conductor.RouterTransaction
import com.bluelinelabs.conductor.changehandler.HorizontalChangeHandler
import com.example.watermyplants.App
import com.example.watermyplants.R
import com.example.watermyplants.models.EditPlant
import com.example.watermyplants.models.Plant
import com.example.watermyplants.util.NotificationUtils
import com.example.watermyplants.util.hideKeyboard
import com.example.watermyplants.util.showToast
import com.example.watermyplants.viewmodel.PlantListViewModel
import kotlinx.android.synthetic.main.create_plant_list_item.view.*
import work.beltran.conductorviewmodel.ViewModelController
import java.util.*

class PlantController : ViewModelController {

    constructor() : super()
    constructor(args: Bundle?) : super(args)

    lateinit var viewModel: PlantListViewModel

    // Inflate Create Plant List Item
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        val view = inflater.inflate(R.layout.create_plant_list_item, container, false)

        viewModel = viewModelProvider().get(PlantListViewModel::class.java)

        val token = App.sharedPref?.getString(App.TOKEN_KEY, "")

        // When "Add" is clicked, the plant_list will inflate from the PlantListController
        val nickname = view?.et_nickname_add
        val species = view?.et_species_add
        val frequency = view?.et_h20frequency_int_add



        view?.btn_listing_add?.setOnClickListener {

            var mediaPlayer: MediaPlayer
            val mNotificationTime = Calendar.getInstance().timeInMillis + 5000
            var mNotified = false

            val nicknameValue = nickname?.text.toString()
            val speciesValue = species?.text.toString()
            val frequencyValue = frequency?.text.toString()
            if (nicknameValue.isNotEmpty() && speciesValue.isNotEmpty() && frequencyValue.isNotEmpty()){
                val plant = EditPlant(nicknameValue, speciesValue, frequencyValue.toInt())
                if (token != null){
                    viewModel.createPlant(token, plant)
                    viewModel.plantCreated()?.observe(this, Observer<Plant>{
                        if (it != null) {
                            if (it.id != -1) {
                                view.context.showToast("Plant created")
                                view.hideKeyboard()
                                router.popCurrentController()

                                // Plays audio when plant is added
                                mediaPlayer = MediaPlayer.create(activity, R.raw.water)
                                mediaPlayer.start()

                                // Sends notification
                                NotificationUtils().setNotification(mNotificationTime, activity!!)

                            } else view.context.showToast("Failed to create plant")
                        }
                    })
                }
            } else view.context.showToast("Please fill out all fields")
        }
        return view
    }

    override fun onChangeEnded(changeHandler: ControllerChangeHandler, changeType: ControllerChangeType) {
        super.onChangeEnded(changeHandler, changeType)

        viewModel.resetPlantList()
    }
}