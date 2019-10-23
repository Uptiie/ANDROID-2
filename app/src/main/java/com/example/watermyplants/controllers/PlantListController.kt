package com.example.watermyplants.controllers

import android.content.SharedPreferences
import android.os.Bundle
import android.view.*
import androidx.core.content.edit
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bluelinelabs.conductor.Controller
import com.bluelinelabs.conductor.ControllerChangeHandler
import com.bluelinelabs.conductor.ControllerChangeType
import com.bluelinelabs.conductor.RouterTransaction
import com.bluelinelabs.conductor.changehandler.HorizontalChangeHandler
import com.example.watermyplants.App
import com.example.watermyplants.R
import com.example.watermyplants.models.Plant
import com.example.watermyplants.viewmodel.PlantListViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.plant_card_view.view.*
import kotlinx.android.synthetic.main.plant_list.view.*
import work.beltran.conductorviewmodel.ViewModelController

class PlantListController : ViewModelController {

    companion object{
        val PLANT_KEY = "plant_key"
    }

    private val list = ArrayList<Plant>()
    lateinit var viewModel: PlantListViewModel

    constructor() : super()
    constructor(args: Bundle?) : super(args)

    // Inflate Plant List Layout
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        val view = inflater.inflate(R.layout.plant_list, container, false)
        // Inflate Options Menu
        setHasOptionsMenu(true)

        viewModel = viewModelProvider().get(PlantListViewModel::class.java)


        val token = App.sharedPref?.getString(App.TOKEN_KEY, "")

        view.plant_recycler_view.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = PlantAdapter(list)
        }

        if (token != null){
            viewModel.getPlants(token)
        }


        //TODO: after like 1-3 updates or deletes it stops updating the recycler view right away
        viewModel.getPlantList()?.observe(this, Observer<List<Plant>>{
            if (it != null) {
                val sortedList = it.sortedBy { it.id }
                sortedList.forEach {
                    list.add(it)
                    view.plant_recycler_view.adapter?.notifyDataSetChanged()
                }
            }
        })
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
            App.sharedPref?.edit()?.clear()?.apply()

            router.pushController(
                RouterTransaction.with(RootController())
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

    inner class PlantAdapter(private val list: ArrayList<Plant>):  RecyclerView.Adapter<PlantAdapter.ViewHolder>(){
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.plant_card_view, parent, false)
            return ViewHolder(view)
        }

        override fun getItemCount() = list.size

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val plant = list[position]
            holder.nickname.text = plant.nickname
            holder.species.text = plant.species
            holder.frequency.text = "${plant.h2oFrequency}"

            holder.plantCard.setOnClickListener {
                args.putSerializable(PLANT_KEY, plant)
                router.pushController(
                    RouterTransaction.with(PlantUpdateController(args))
                        .pushChangeHandler(HorizontalChangeHandler())
                        .popChangeHandler(HorizontalChangeHandler())
                )
            }
        }


        inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
            val nickname = view.tv_plant_nickname
            val species = view.tv_plant_species
            val frequency = view.tv_plant_frequency
            val plantCard = view.cv_plant

        }
    }

    override fun onDestroyView(view: View) {
        super.onDestroyView(view)
        list.clear()
        viewModel.resetPlantList()

    }

}