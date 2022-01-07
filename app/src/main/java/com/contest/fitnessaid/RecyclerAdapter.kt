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

class RecyclerAdapter: RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    private var titles = arrayOf("Chest", "Back", "Biceps", "Triceps", "Leg", "Cardio")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.presets_card_layout, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: RecyclerAdapter.ViewHolder, position: Int) {
        holder.presetTitle.text = titles[position]
        holder.viewButton.setOnClickListener {
            val activity = holder.viewButton.context as Activity
            val intent = Intent(activity, PresetDescriptions::class.java)
            intent.putExtra("PresetName", titles[position])
            activity.startActivity(intent)
        }
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