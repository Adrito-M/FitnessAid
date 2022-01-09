package com.contest.fitnessaid

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
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

        adapter = RecyclerAdapter(this)
        recyclerView.adapter=adapter

        val gym_places = findViewById<Button>(R.id.gym_places)

        val sharedPreferences: SharedPreferences = getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
        val username = sharedPreferences.getString("username", " ")
        val  salutation = findViewById<TextView>(R.id.salutation_preset)
        salutation.setText("Hello,\n${username}")

        gym_places.setOnClickListener{
            val intent = Intent(this@PresetsScreen, Screen2::class.java)
            startActivity(intent)
            finish()
        }
    }
}