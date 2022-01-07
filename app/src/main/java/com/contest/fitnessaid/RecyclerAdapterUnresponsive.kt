package com.contest.fitnessaid

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity

class RecyclerAdapterUnresponsive: RecyclerView.Adapter<RecyclerAdapterUnresponsive.ViewHolder>() {

    private var titles = arrayOf("Chest", "Back", "Biceps", "Triceps", "Leg", "Cardio")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapterUnresponsive.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.preset_card_layout_exercises, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: RecyclerAdapterUnresponsive.ViewHolder, position: Int) {
        holder.presetName.text = titles[position]
    }

    override fun getItemCount(): Int {
        return titles.size
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        var presetName : TextView
        var presetDescription : TextView

        init {
            presetName = itemView.findViewById(R.id.exercise_name)
            presetDescription = itemView.findViewById(R.id.exercise_time)
        }

    }

}