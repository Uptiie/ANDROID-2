package com.example.watermyplants.controllers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bluelinelabs.conductor.Controller
import com.example.watermyplants.R

class PlantListController : Controller {

    constructor() : super()
    constructor(args: Bundle?) : super(args)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        val view = inflater.inflate(R.layout.plant_list, container, false)
        return view
    }
}