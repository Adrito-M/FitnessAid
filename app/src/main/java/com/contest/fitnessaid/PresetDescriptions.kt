package com.contest.fitnessaid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class PresetDescriptions : AppCompatActivity() {

    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<RecyclerAdapterUnresponsive.ViewHolder>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preset_descriptions)

        val intent = getIntent()
        val name = intent.getStringExtra("PresetName")

        val heading : TextView= findViewById(R.id.preset_name)
        heading.setText(name)

        val  recyclerView=findViewById<RecyclerView>(R.id.unresponsive_recycler_view)
        layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        adapter=RecyclerAdapterUnresponsive()
        recyclerView.adapter=adapter
    }
}