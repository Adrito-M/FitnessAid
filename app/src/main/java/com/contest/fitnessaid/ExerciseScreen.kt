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
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.isVisible
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.w3c.dom.Text
import java.text.DecimalFormat
import java.text.NumberFormat

class ExerciseScreen : AppCompatActivity() {

    lateinit var fab:FloatingActionButton
    lateinit var timertext:TextView
    lateinit var timerExt: CountDownTimerExt


    private var isPaused = false
    private var isCancelled = false
    private var resumeFromMillis:Long = 0

    private val exercise_desc = mapOf(

        1 to "Sit back into hips and keep the back straight and the chest up, squatting down so the hips are below the knees. From the bottom of the squat, press feet into the ground and push hips forward to return to the top of the standing position",

        2 to "Perform barbell curls by grabbing a barbell with a shoulder-width supinated grip (palms facing towards your body). Hinge your elbows, and lift the barbell toward your chest",

        3 to "Lay face up on a horizontal bench, with your butt on bench and feet flat on ground. Grasp the barbell with an overhand grip. Inhale, lift up the bar and lower it to the centre of your chest, maintaining pressure on your feet the entire time",

        4 to "Lie flat on your back on a bench. Grip the bar with hands just wider than shoulder-width apart. Bring the bar slowly down to your chest as you breathe in. Push up as you breathe out, gripping the bar hard",

        5 to "Lay on back with feet placed flat on floor. Push the dumbbells directly above chest with lightly bent elbows, keeping your wrists straight. Lower the dumbbells to shoulder height. Exhale and raise the dumbbells while maintaining the arc in arms",

        6 to "Pedal at a slow, easy pace. Then boost your speed so you start to sweat. If you're riding a stationary bike, simply change the settings for a faster pace",

        7 to "Stand up straight, then push through the balls of your feet and raise your heel until you are standing on your toes. Then lower slowly back to the start",

        8 to "Press into your palms to lift your body and slide forward just far enough that your behind clears the edge of the chair. Control the movement throughout the range of motion. Push yourself back up slowly until your arms are almost straight and repeat",

        9 to "Lift the dumbbells to chest height with your palms facing forwards. Breathe out and push the dumbbells up until your arms are fully extended. Don't let the dumbbells touch. Pause, then slowly bring them back down as you inhale",

        10 to "Stand with your feet shoulder-width apart and dumbbells held in front. Raise the dumbbells above. Slowly lower the weights back behind your head. Once your forearms move beyond parallel to the floor bring the weight back up to the starting position",

        11 to "Hold an EZ-bar in an underhand grip with your arms extended and then curl the bar up towards your chest, keeping your elbows into your sides",

        12 to "Step onto the machine facing the console and start pedalling. While stepping off, make sure that the machine is stopped completely",

        13 to "Grip a barbell with hands slightly wider than shoulder-width. Press your feet firmly into the ground and keep your hips on the bench. Slowly lift bar off rack, if using, and lower the bar to the chest, allowing elbows to bend out to the side",

        14 to "Stand up straight with a dumbbell in each hand. Your palms should face your body. Keep your biceps stationary and start bending at your elbows, lifting both dumbbells. Lift until the dumbbells reach shoulder-level",

        15 to "Press the weight upward over your eyes or slightly higher, elbows fully extended. Inhale and slowly lower dumbbells or barbell slowly and with control until they touch or reach just above the chest, elbows and wrists staying out to the sides",

        16 to "Grasp the bar with a wide grip, looking forward with your torso upright. Retract your shoulder blades and pull the bar down in front of you to your upper chest. Squeeze your lats at the bottom. Resist the temptation to lean back to aid the movement",

        17 to "Place your hands on the hand bars. Lift the weight while exhaling until your legs are almost straight. Do not lock your knees. Keep your back against the backrest and do not arch your back. Exhale and lower the weight back to starting position",

        18 to "Choose appropriate weight and set the seat so that the knees are bent just over right angles. Position feet about shoulder-width apart. Try to push through your heels, keeping the knees lined up over the toes. Extend the legs until nearly straight",

        19 to "Stand with feet hip-width apart, engage your core, and take a big step backward. Activate your glutes as you bend front knee to lower your body so back knee lightly taps the floor while keeping upper body upright",

        20 to "While maintaining contact between your back and the padding, press handles outwards without locking your elbows. Exhale as you press the handles away from your chest. Allow the handles to slowly come in until they are in line with your chest",

        21 to "Move the seat pad so that when you sit down you can place your feet comfortably on the floor with the back pad supporting your spine. Grab the handles and press the arms together in front of your chest with a slow, controlled movement",

        22 to "Start with your hands on the bar approximately shoulder-width apart with your palms facing forward. With arms extended above you, stick your chest out and curve your back slightly. Pull yourself up using your back until the bar is at chest level",

        23 to "Get down on all fours, placing your hands slightly wider than your shoulders. Straighten your arms and legs. Lower your body until your chest nearly touches the floor. Pause, then push yourself back up. Repeat",

        24 to "Anchor the ends of a resistance band to a sturdy object. Lie down on your stomach with your feet hip-width apart. Bend your knee to pull your heel toward your butt, keeping your thighs and hips on the mat",

        25 to "Grab the bar with your hands, just wider than shoulder-width apart. Brace your core and squeeze your shoulders together to row the weight up until it touches your sternum, then slowly lower it back down again",

        26 to "Sit upright on the bench and plant your feet on the floor or footpads, knees bent. Extend your arms and hold the handle or cable",

        27 to "Place one hand on your thigh for support. On an exhale, engage your triceps as you slowly extend your arm back as far as you can, keeping your arm in tight by your side. Pause here, then inhale as your return your arm to the starting position",

        28 to "Bring the dumbbell up to your chest, concentrating on lifting it with your back and shoulder muscles. At the top of the movement, squeeze your shoulder and back muscles. Lower the dumbbell slowly until your arm is fully extended again",

        29 to "Lie down on a bench or stability ball. Hold weights straight out in front of you. Lower the weights towards the floor by bending your elbows. Move back to the starting position",

        30 to "Stand holding a dumbbell in each hand with your arms hanging by your sides. Keeping your upper arms stationary, exhale as you curl the weights up to shoulder level while contracting your biceps",

        31 to "Set the speed of the treadmill according to your comfort, and walk"
    )

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
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
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

        val exerciseDescription = exercise_desc[exerciseList[exerciseIndex]].toString()

        val exerciseDesText = findViewById<TextView>(R.id.exercise_description)
        exerciseDesText.setText(exerciseDescription)

        val exerciseTitleTV = findViewById<TextView>(R.id.textView)
        val picture = findViewById<ImageView>(R.id.exercise_image)
        exerciseTitleTV.text = map_exercises[exerciseList[exerciseIndex]]
        picture.setImageResource(map_pictures[exerciseList[exerciseIndex]] as Int)
        fab  = findViewById(R.id.fab)
        timertext = findViewById(R.id.timerText)
        val fabBack = findViewById<FloatingActionButton>(R.id.backFab)


        val netTimeInSec:Long = 300

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