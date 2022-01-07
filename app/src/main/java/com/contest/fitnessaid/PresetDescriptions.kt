package com.contest.fitnessaid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class PresetDescriptions : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preset_descriptions)

        val intent = getIntent()
        val name = intent.getStringExtra("PresetName")

        val heading : TextView= findViewById(R.id.preset_name)
        heading.setText(name)
    }
}