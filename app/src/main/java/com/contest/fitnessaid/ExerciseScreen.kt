package com.contest.fitnessaid

import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.util.Log
import android.widget.Button
import android.widget.ImageView
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


    val map_pictures = mapOf(
        1 to R.drawable.backsquats,
        2 to R.drawable.barbellcurl,
        3 to R.drawable.benchtricepdips,
        4 to R.drawable.bentoverrow,
        5 to R.drawable.calfraises,
        6 to R.drawable.chestfly,
        7 to R.drawable.cycle,
        8 to R.drawable.dips,
        9 to R.drawable.dumbbellbenchpress,
        10 to R.drawable.dumbbelloverheadtricepsextension,
        11 to R.drawable.ezbarcurl,
        12 to R.drawable.elliptical,
        13 to R.drawable.flatbenchpress,
        14 to R.drawable.hammercurl,
        15 to R.drawable.inclinedbenchpress,
        16 to R.drawable.latpulldown,
        17 to R.drawable.legextensions,
        18 to R.drawable.legpress,
        19 to R.drawable.lunges,
        20 to R.drawable.machinechestpress,
        21 to R.drawable.machinefly,
        22 to R.drawable.pullup,
        23 to R.drawable.pushups,
        24 to R.drawable.reverselegcurlmachine,
        25 to R.drawable.reversegripbentoverrow,
        26 to R.drawable.seatedcablerowlats,
        27 to R.drawable.singlearmtricepkickback,
        28 to R.drawable.singlearmrow,
        29 to R.drawable.skullcrusher,
        30 to R.drawable.standingdumbbellcurl,
        31 to R.drawable.treadmill
    )



    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise_screen)
        val sp : SharedPreferences = getSharedPreferences("PREFERENCE_NAME", MODE_PRIVATE)
        val intent = getIntent()
        val presetName = intent.getStringExtra("PresetName")
        val exerciseListSize = sp.getInt("${presetName}_routine_size", 0)
        val exerciseList = mutableListOf<Int>()
        for (i in 0 until exerciseListSize) {
            exerciseList.add(sp.getInt("${presetName}_routine${i}",0))
        }

        val exerciseIndex = intent.getIntExtra("CurrentExerciseIndex", 0)
        val exerciseTitleTV = findViewById<TextView>(R.id.textView)
        val picture = findViewById<ImageView>(R.id.exercise_image)
        exerciseTitleTV.text = map_exercises[exerciseList[exerciseIndex]]
        picture.setImageResource(map_pictures[exerciseList[exerciseIndex]] as Int)
        fab  = findViewById(R.id.fab)
        timertext = findViewById(R.id.timerText)
        val fabBack = findViewById<FloatingActionButton>(R.id.backFab)


        val netTimeInSec:Long = 10

        var p = true

        resumeFromMillis = netTimeInSec*1000



        timerExt = object : CountDownTimerExt(resumeFromMillis, 1000) {
            override fun onTimerTick(millisUntilFinished: Long) {
                val f: NumberFormat = DecimalFormat("00")
                var sec :Long = millisUntilFinished/1000
                sec %= 60
                val min:Long = (millisUntilFinished / 60000) % 60
                timertext.setText("${f.format(min)}:${f.format(sec)}")
            }
            override fun onTimerFinish() {
                timertext.setText("done!")
                fab.hide()


                val intent2 = Intent(this@ExerciseScreen, CongratulationsPage::class.java)
                intent2.putExtra("PresetName", presetName)
                var isMore = true
                if (exerciseIndex == exerciseListSize - 1) {
                    isMore = false
                }
                intent2.putExtra("isMore", isMore)
                intent2.putExtra("CurrentExerciseIndex", exerciseIndex)
                startActivity(intent2)
                finish()
            }

        }

        timerExt.start()

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

        fabBack.setOnClickListener {
            onBackPressed()
        }
    }

    @Override
    override fun onBackPressed() {
        timerExt.pause()
        finish()
        super.onBackPressed()
    }


}