package com.contest.fitnessaid

import android.app.Activity
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import java.security.AccessController.getContext

class RecyclerAdapter(context: Context): RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    private var titles = arrayOf("Chest", "Back", "Biceps", "Triceps", "Leg", "Cardio")
    private val map_exercises = mutableMapOf<Int, String>(
        1 to "Back squats",
        2 to "Barbell curl",
        3 to "Bench",
        4 to "Bent-over row",
        5 to "Calf raises",
        6 to "Chest Fly",
        7 to "Cycle",
        8 to "Dip",
        9 to "Dumb bell bench press",
        10 to "Dumbbell overhead Triceps extension",
        11 to "EZ bar curl",
        12 to "Elliptical",
        13 to "Flat bench press",
        14 to "Hammer Curl",
        15 to "Inclined bench press",
        16 to "Lat pulldown",
        17 to "Leg Extension ",
        18 to "Leg press",
        19 to "Lunges",
        20 to "Machine Chest press",
        21 to "Machine fly",
        22 to "Pull-up",
        23 to "Pushups",
        24 to "Reverse leg curl machine",
        25 to "Reverse-Grip Bent-Over Row",
        26 to "Seated Cable Row(Lats)",
        27 to "Single arm tricep kickback",
        28 to "Single-arm row",
        29 to "Skullcrusher",
        30 to "Standing dumbbell curl",
        31 to "Treadmill"
    )
    private val sp =  context.getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.ViewHolder {

        val v = LayoutInflater.from(parent.context).inflate(R.layout.presets_card_layout, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: RecyclerAdapter.ViewHolder, position: Int) {

        holder.presetTitle.text = titles[position]

        holder.activitiesIn.text = "${map_exercises[sp.getInt("${titles[position]}_routine${0}", 0)]}, ${map_exercises[sp.getInt("${titles[position]}_routine${1}", 0)]}, etc"




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