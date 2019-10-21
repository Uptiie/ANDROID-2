package com.example.watermyplants.controllers

import android.os.Bundle
import android.view.*
import android.widget.Button
import com.bluelinelabs.conductor.Controller
import com.bluelinelabs.conductor.ControllerChangeHandler
import com.bluelinelabs.conductor.ControllerChangeType
import com.bluelinelabs.conductor.RouterTransaction
import com.bluelinelabs.conductor.changehandler.HorizontalChangeHandler
import com.example.watermyplants.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class PlantListController : Controller {

    constructor() : super()
    constructor(args: Bundle?) : super(args)

    // Inflate Plant List Layout
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        val view = inflater.inflate(R.layout.plant_list, container, false)
        return view
    }

    override fun onChangeEnded(
        changeHandler: ControllerChangeHandler,
        changeType: ControllerChangeType
    ) {
        super.onChangeEnded(changeHandler, changeType)

        // When FloatingActionButton is clicked, the create_plant_list_item will inflate from the PlantController
        view?.findViewById<FloatingActionButton>(R.id.fab)?.setOnClickListener {
            router.pushController(
                RouterTransaction.with(PlantController())
                    .pushChangeHandler(HorizontalChangeHandler())
                    .popChangeHandler(HorizontalChangeHandler())
            )
        }
    }
}