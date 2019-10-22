package com.example.watermyplants.controllers

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.recyclerview.widget.LinearLayoutManager
import com.bluelinelabs.conductor.Controller
import com.bluelinelabs.conductor.ControllerChangeHandler
import com.bluelinelabs.conductor.ControllerChangeType
import com.bluelinelabs.conductor.RouterTransaction
import com.bluelinelabs.conductor.changehandler.HorizontalChangeHandler
import com.example.watermyplants.R
import com.example.watermyplants.adapter.PlantAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.plant_list.view.*

class PlantListController : Controller {

    constructor() : super()
    constructor(args: Bundle?) : super(args)

    // Inflate Plant List Layout
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        val view = inflater.inflate(R.layout.plant_list, container, false)
        // Inflate Options Menu
        setHasOptionsMenu(true)

        view.plant_recycler_view.apply {
            layoutManager = LinearLayoutManager(activity)
//            adapter = PlantAdapter
        }
        return view
    }

    // Inflate Options Menu
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.option_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    // When "Edit Profile" is clicked, the profile_layout will inflate from the ProfileController
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.menu_edit_profile) {
            router.pushController(
                RouterTransaction.with(ProfileController(args))
                    .pushChangeHandler(HorizontalChangeHandler())
                    .popChangeHandler(HorizontalChangeHandler())
            )
        }
        // When "Logout" is clicked, the login_layout will inflate from the RootController
        if (id == R.id.menu_logout) {
            router.pushController(
                RouterTransaction.with(RootController(args))
                    .pushChangeHandler(HorizontalChangeHandler())
                    .popChangeHandler(HorizontalChangeHandler())
            )
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onChangeEnded(
        changeHandler: ControllerChangeHandler,
        changeType: ControllerChangeType
    ) {
        super.onChangeEnded(changeHandler, changeType)

        // When FloatingActionButton is clicked, the create_plant_list_item will inflate from the PlantController
        view?.findViewById<FloatingActionButton>(R.id.fab)?.setOnClickListener {
            router.pushController(
                RouterTransaction.with(PlantController(args))
                    .pushChangeHandler(HorizontalChangeHandler())
                    .popChangeHandler(HorizontalChangeHandler())
            )
        }
    }
}