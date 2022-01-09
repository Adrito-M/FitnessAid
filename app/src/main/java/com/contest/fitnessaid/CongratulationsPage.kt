package com.contest.fitnessaid

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import nl.dionsegijn.konfetti.core.Party
import nl.dionsegijn.konfetti.core.Position
import nl.dionsegijn.konfetti.core.emitter.Emitter
import nl.dionsegijn.konfetti.xml.KonfettiView
import java.util.concurrent.TimeUnit

class CongratulationsPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_congratulations_page)

        val konfetti = findViewById<KonfettiView>(R.id.konfettiView)
        val party = Party(
            speed = 0f,
            maxSpeed = 30f,
            damping = 0.9f,
            spread = 360,
            colors = listOf(0xfce18a, 0xff726d, 0xf4306d, 0xb48def),
            position = Position.Relative(0.5, 0.3),
            emitter = Emitter(duration = 100, TimeUnit.MILLISECONDS).max(100)
        )
        konfetti.start(party)

        val ring = MediaPlayer.create(this, R.raw.konfett)
        ring.start()



        val intent = getIntent()
        val isMore = intent.getBooleanExtra("isMore", false)
        val presetName = intent.getStringExtra("PresetName")
        val currentExerciseIndex = intent.getIntExtra("CurrentExerciseIndex",0)
        val nextButton = findViewById<Button>(R.id.buttonNext)

        if(isMore) {
            nextButton.setOnClickListener {
                val intent2 = Intent(this@CongratulationsPage, ExerciseScreen::class.java)
                intent2.putExtra("PresetName", presetName)
                intent2.putExtra("CurrentExerciseIndex", currentExerciseIndex + 1)
                startActivity(intent2)
                finish()
            }
        }
        else {
            val congratsDesc = findViewById<TextView>(R.id.textView8)
            congratsDesc.text = "You Finished The ${presetName} Routine!!"
            nextButton.setOnClickListener {
                val intent3 = Intent(this@CongratulationsPage, PresetsScreen::class.java)
                startActivity(intent3)
                finish()
            }
        }

    }
}