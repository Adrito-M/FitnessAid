package com.contest.fitnessaid

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class Screen2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_screen2)

        val btn = findViewById<Button>(R.id.buttonwelcome)
        val username = findViewById<EditText>(R.id.username)

        btn.setOnClickListener {
            val intent = Intent(this@Screen2, PresetsScreen::class.java)
            val userNameString = username.text.toString()
            if (userNameString != "") {
                intent.putExtra("UserName", userNameString)
                val sharedPreference =  getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
                var editor = sharedPreference.edit()
                editor.putString("username", userNameString)
                editor.putString("hasLoggedIn", "YES")
                editor.commit()
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this@Screen2, "Enter your name", Toast.LENGTH_SHORT).show()
            }
        }
    }
}