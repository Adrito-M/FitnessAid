package com.contest.fitnessaid

import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView

class RecyclerAdapter: RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    private var titles = arrayOf("Chest", "Back", "Biceps", "Triceps", "Leg", "Cardio")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.presets_card_layout, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: RecyclerAdapter.ViewHolder, position: Int) {
        holder.presetTitle.text = titles[position]
    }

    override fun getItemCount(): Int {
        return titles.size
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        var presetTitle : TextView
        var activitiesIn : TextView
        var viewButton : Button

        init {
            presetTitle = itemView.findViewById(R.id.preset_title)
            activitiesIn = itemView.findViewById(R.id.activities_in)
            viewButton = itemView.findViewById(R.id.view_button)
        }

    }

}