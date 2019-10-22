package com.example.watermyplants.controllers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.bluelinelabs.conductor.Controller
import com.bluelinelabs.conductor.RouterTransaction
import com.bluelinelabs.conductor.changehandler.HorizontalChangeHandler
import com.example.watermyplants.R

class PlantUpdateController : Controller {

    constructor() : super()
    constructor(args: Bundle?) : super(args)

    // Inflate Plant Details Layout
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        val view = inflater.inflate(R.layout.plant_details_layout, container, false)

        // When "Cancel" button is clicked, the plant_list will inflate from the PlantListController
        view?.findViewById<Button>(R.id.btn_listing_cancel_details)?.setOnClickListener {
            router.pushController(
                RouterTransaction.with(PlantListController(args))
                    .pushChangeHandler(HorizontalChangeHandler())
                    .popChangeHandler(HorizontalChangeHandler())
            )
        }

        // When "Update" button is clicked, the plant_list will inflate from the PlantListController
        view?.findViewById<Button>(R.id.btn_listing_update_details)?.setOnClickListener {
            router.pushController(
                RouterTransaction.with(PlantListController(args))
                    .pushChangeHandler(HorizontalChangeHandler())
                    .popChangeHandler(HorizontalChangeHandler())
            )
        }

        // When "Delete" button is clicked, the plant_list will inflate from the PlantListController
        view?.findViewById<Button>(R.id.btn_listing_delete_details)?.setOnClickListener {
            router.pushController(
                RouterTransaction.with(PlantListController(args))
                    .pushChangeHandler(HorizontalChangeHandler())
                    .popChangeHandler(HorizontalChangeHandler())
            )
        }

        return view
    }
}