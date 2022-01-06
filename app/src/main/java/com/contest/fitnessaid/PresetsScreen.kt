package com.contest.fitnessaid

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class PresetsScreen : AppCompatActivity() {

    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<RecyclerAdapter.ViewHolder>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_presets_screen)

        val  recyclerView=findViewById<RecyclerView>(R.id.preset_view)
        layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        adapter=RecyclerAdapter()
        recyclerView.adapter=adapter

        val sharedPreferences: SharedPreferences = getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
        val username = sharedPreferences.getString("username", " ")
        val  salutation = findViewById<TextView>(R.id.salutation_preset)
        salutation.setText("Hello,\n${username}")


    }
}