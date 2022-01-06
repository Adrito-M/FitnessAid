package com.contest.fitnessaid

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val sharedPreferences: SharedPreferences = getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
        val cond = sharedPreferences.getString("hasLoggedIn", "No")
        supportActionBar?.hide()

        Handler().postDelayed({
            if(cond == "YES"){
                //scree2 needs to be changed to presetScreen
                val intent = Intent(this@MainActivity, Screen2::class.java )
                startActivity(intent)
                finish()
            }else{
                val intent = Intent(this@MainActivity, Screen2::class.java )
                startActivity(intent)
                finish()
            }

        }, 3000)
    }
}