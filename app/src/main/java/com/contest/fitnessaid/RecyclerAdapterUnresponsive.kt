package com.contest.fitnessaid

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.Intent.getIntent
import android.content.SharedPreferences
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity

class RecyclerAdapterUnresponsive(context: Context): RecyclerView.Adapter<RecyclerAdapterUnresponsive.ViewHolder>() {


    val map_exercises = mutableMapOf<Int, String>(
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

    val sp : SharedPreferences = context.getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
    val presetname = sp.getString("PresetName", "err")
    val size = sp.getInt(presetname + "_routine_size", 0)
    val routines = arrayListOf<Int>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapterUnresponsive.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.preset_card_layout_exercises, parent, false)
        for (i in 0 until size)
            routines.add(sp.getInt(presetname + "_routine${i}", 0))
        return ViewHolder(v)
    }


    override fun onBindViewHolder(holder: RecyclerAdapterUnresponsive.ViewHolder, position: Int) {
        holder.exerciseName.text = map_exercises[routines[position]]
    }

    override fun getItemCount(): Int {
        return size
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        var exerciseName : TextView
        var exerciseTime : TextView
        //val sharedPreference =  context.getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
        //val preset_name = sharedPreference.getString("PresetName", "err")
        //val editor = sharedPreference.edit()

        init {
            exerciseName = itemView.findViewById(R.id.exercise_name)
            exerciseTime = itemView.findViewById(R.id.exercise_time)
        }

    }

}

public class ValueCreator (context: Context){
    //lateinit var sp : SharedPreferences
    public var s = 5
    init {
        //sp = context.getSharedPreferences()

    }
}