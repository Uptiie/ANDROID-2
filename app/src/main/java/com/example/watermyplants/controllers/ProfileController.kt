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

class ProfileController : Controller {

    constructor() : super()
    constructor(args: Bundle?) : super(args)

    // Inflate Profile Layout
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        val view = inflater.inflate(R.layout.profile_layout, container, false)

        // When "Update" is clicked, the plant_list will inflate from the PlantListController
        view?.findViewById<Button>(R.id.btn_update)?.setOnClickListener {
            router.pushController(
                RouterTransaction.with(PlantListController(args))
                    .pushChangeHandler(HorizontalChangeHandler())
                    .popChangeHandler(HorizontalChangeHandler())
            )
        }
        return view
    }
}