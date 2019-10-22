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

class PlantController : Controller {

    constructor() : super()
    constructor(args: Bundle?) : super(args)

    // Inflate Create Plant List Item
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        val view = inflater.inflate(R.layout.create_plant_list_item, container, false)

        // When "Add" is clicked, the plant_list will inflate from the PlantListController
        view?.findViewById<Button>(R.id.btn_listing_add)?.setOnClickListener {
            router.pushController(
                RouterTransaction.with(PlantListController(args))
                    .pushChangeHandler(HorizontalChangeHandler())
                    .popChangeHandler(HorizontalChangeHandler())
            )
        }

        return view
    }
}