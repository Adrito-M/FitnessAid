package com.contest.fitnessaid

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class PresetsScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_presets_screen)

        val sharedPreferences: SharedPreferences = getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
        val username = sharedPreferences.getString("username", " ")
        val  salutation = findViewById<TextView>(R.id.salutation_preset)
        salutation.setText("Hello, ${username}")


    }
}