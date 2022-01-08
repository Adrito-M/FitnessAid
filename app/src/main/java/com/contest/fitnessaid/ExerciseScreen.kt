package com.contest.fitnessaid

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.text.DecimalFormat
import java.text.NumberFormat

class ExerciseScreen : AppCompatActivity() {

    lateinit var fab:FloatingActionButton
    lateinit var timertext:TextView

    lateinit var timerExt: CountDownTimerExt


    private var isPaused = false
    private var isCancelled = false
    private var resumeFromMillis:Long = 0
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



    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise_screen)
        /*
        val index = getIntExtra('key', 'default')
        val counter = getIntExtra('sdf','sdfds')
        val size = getIntExtra('dsf','sdf')
        val presetName = getStringExtra('fdg','')

        val string = map.get(index)

        text.setText(string)
        */
//        val intent = getIntent()
//        val preset_name = intent.getStringExtra("PresetName")
//        val exercise_index = map_exercises[index]

        fab  = findViewById<FloatingActionButton>(R.id.fab)
        timertext = findViewById<TextView>(R.id.timerText)
        val fabBack = findViewById<FloatingActionButton>(R.id.backFab)

        //nextBtn.isVisible = false

        val netTimeInSec:Long = 30
        val millisInFuture:Long = netTimeInSec*1000
        val countDownInterval:Long = 1000
        var remTime: Long = 0

        var p: Boolean = true
        var increment:Long = 1000


        resumeFromMillis = netTimeInSec*1000



        timerExt = object : CountDownTimerExt(resumeFromMillis, 1000) {
            override fun onTimerTick(millisUntilFinished: Long) {
                //Log.d("TAG", "onTimerTick $millisUntilFinished")
                val f: NumberFormat = DecimalFormat("00")
                var sec :Long = millisUntilFinished/1000
                sec %= 60
                val min:Long = (millisUntilFinished / 60000) % 60
                timertext.setText("${f.format(min)}:${f.format(sec)}")
            }
            override fun onTimerFinish() {
                //Log.d("TAG", "onTimerFinish")
                timertext.setText("done!")
                fab.hide()


                val intent = Intent(this@ExerciseScreen, CongratulationsPage::class.java)
                startActivity(intent)
                finish()
            }

        }

        timerExt.start()


        //fab onclick toggle function
        fab.setOnClickListener{
            if(p){
                fab.setImageResource(R.drawable.ic_baseline_play_arrow_24)
                isPaused = true
                timerExt.pause()
            }else{
                fab.setImageResource(R.drawable.ic_baseline_pause_24)
                isPaused = false
                timerExt.start()
            }
            p = !p
        }
    }


}