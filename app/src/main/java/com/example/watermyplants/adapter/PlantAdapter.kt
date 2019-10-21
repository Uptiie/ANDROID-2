package com.example.watermyplants.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.watermyplants.R
import kotlinx.android.synthetic.main.plant_card_view.view.*

class PlantAdapter(private val list: ArrayList<String>):  RecyclerView.Adapter<PlantAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.plant_card_view, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val plant = list[position]
        /*holder.nickname.text = plant.nickname
        holder.species.text = plant.species
        holder.frequency.text = plant.h2oFrequency*/
    }


    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val nickname = view.tv_plant_nickname
        val species = view.tv_plant_species
        val frequency = view.tv_plant_frequency
        val plantCard = view.cv_plant

    }
}