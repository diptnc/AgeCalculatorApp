package `in`.diptanuchakraborty.agecalculator

import android.content.Intent
import android.os.Bundle
import android.util.TypedValue
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.joda.time.LocalDateTime
import org.joda.time.Period
import org.joda.time.PeriodType


class DisplayAge_Layout : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_age__layout)
//        ####################################
//        My code starts
        //initializing animation
        //-----------initializing the animations --------------------------------

        val ttb =
            AnimationUtils.loadAnimation(this, R.anim.ttb) //declaring the animation
        val ttb2 =
            AnimationUtils.loadAnimation(this, R.anim.ttb) //declaring the animation
        val ttb3 =
            AnimationUtils.loadAnimation(this, R.anim.ttb) //declaring the animation
        val zoomscale =
            AnimationUtils.loadAnimation(this, R.anim.zoomscale) //declaring the animation

        val fadein =
            AnimationUtils.loadAnimation(this, R.anim.fadein) //declaring the animation
        val fadein1 =
            AnimationUtils.loadAnimation(this, R.anim.fadein) //declaring the animation
        val fadein2 =
            AnimationUtils.loadAnimation(this, R.anim.fadeinbtt_1)//declaring the animation
        val fadein3 =
            AnimationUtils.loadAnimation(this, R.anim.fadeinbtt_1) //declaring the animation
        val fadein4 =
            AnimationUtils.loadAnimation(this, R.anim.fadeinbtt_1) //declaring the animation
        val fadeinbtt =
            AnimationUtils.loadAnimation(this, R.anim.fadeinbtt) //declaring the animation

        val footerbtt =
            AnimationUtils.loadAnimation(this, R.anim.footerbtt) //declaring the animation
        val footerbtt1 =
            AnimationUtils.loadAnimation(this, R.anim.footerbtt) //declaring the animation
        val footerbtt2 =
            AnimationUtils.loadAnimation(this, R.anim.footerbtt) //declaring the animation
        //initializing widgets  top to bottom
        var el1 = findViewById<TextView>(R.id.el1).also {
            ttb.startOffset = 300
            it.startAnimation(ttb)
        }
        var selectedDateFieldFirstLine =
            findViewById<TextView>(R.id.selectedDateFieldFirstLine).also {
                ttb2.startOffset = 400
                it.startAnimation(ttb2)
            }
        var selectedDateField = findViewById<TextView>(R.id.selectedDateField).also {
            ttb3.startOffset = 450
            it.startAnimation(ttb3)
        }

        var centerImageVector = findViewById<ImageView>(R.id.centerImageVector).also {
            zoomscale.startOffset = 500
            it.startAnimation(zoomscale)
        }
        var footerbg = findViewById<ImageView>(R.id.footerbg).also {
            footerbtt.startOffset = 200
            it.startAnimation(footerbtt)
        }

        var el3 = findViewById<TextView>(R.id.el3).also {
            it.startAnimation(ttb3)
        }
        var circularShapeHolder = findViewById<ImageView>(R.id.circularShapeHolder).also {
            fadein.startOffset = 600
            it.startAnimation(fadein)
        }
        var circularMainText = findViewById<TextView>(R.id.circularMainText).also {
            fadein4.startOffset = 900
            it.startAnimation(fadein4)
        }
        var circular_maintext_2 = findViewById<TextView>(R.id.circular_maintext_2).also {
            fadein2.startOffset = 850
            it.startAnimation(fadein2)
        }
        var result_main_text_3 = findViewById<TextView>(R.id.result_main_text_3).also {
            fadein3.startOffset = 900
            it.startAnimation(fadein3)
        }
        var button2 = findViewById<Button>(R.id.button2).also {
            footerbtt1.startOffset = 1000
            it.startAnimation(footerbtt1)
        }

        var button3 = findViewById<Button>(R.id.button3).also {
            footerbtt1.startOffset = 1100
            it.startAnimation(footerbtt1)
        }
        var copyrightText = findViewById<TextView>(R.id.copyrightText).also {
            footerbtt2.startOffset = 1200
            it.startAnimation(footerbtt2)
        }
        //All the data from previous intent
        val matrixSelection = intent.getStringExtra("matrixSelection")
        var selectedYear = intent.getIntExtra("selectedYear", 0)
        var selectedMonth = intent.getIntExtra("selectedMonth", 0)
        var selectedDayOfMonth = intent.getIntExtra("selectedDayOfMonth", 0)

        var birthday =

            LocalDateTime(
                selectedYear,
                selectedMonth + 1,
                selectedDayOfMonth,
                23,
                59,
                59
            ) //Birth date

// #########################################
        //set text the birth date
        selectedDateField.setText("$selectedDayOfMonth / ${selectedMonth + 1} / $selectedYear")

//        #####################
        // Logic For Age Calculations

        if (matrixSelection == "Y") {
            var nowTime = LocalDateTime().withTime(23, 59, 59, 0)
            var ageInYears_raw =
                Period(birthday, nowTime, PeriodType.yearMonthDayTime()) //age in years
            var ageInYears_one = ageInYears_raw.days
            var ageInYears_two = ageInYears_raw.months
            var ageInYears_Three = ageInYears_raw.years

// when year is selected set text
            circularMainText.setText("$ageInYears_Three")//21
            circular_maintext_2.setText("Years")//years selected
            result_main_text_3.setText("$ageInYears_two months & $ageInYears_one days old")

        }
        if (matrixSelection == "D") {
            var nowTime = LocalDateTime()
            var ageInDate_raw =
                Period(birthday, nowTime, PeriodType.days()) //age in days
            var ageInDate_one = ageInDate_raw.days
            ageInDate_one++ //exact days including the current date
// when Date is selected set text
            if (ageInDate_one > 999) {
                circularMainText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 35F)
            }
            if (ageInDate_one > 999999) {
                circularMainText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15F)
            }
            circularMainText.setText("$ageInDate_one")//21
            circular_maintext_2.setText("Days")//years selected
            result_main_text_3.setText("OLD")

        }
        if (matrixSelection == "T") {
            var nowTime = LocalDateTime()
            var ageInTime_raw =
                Period(birthday, nowTime, PeriodType.time()) //age in time
            var ageInTime_one = ageInTime_raw.hours
            var ageInTime_two = ageInTime_raw.minutes
            var ageInTime_three = ageInTime_raw.seconds
// when time is selected set text
            if (ageInTime_one > 999) {
                circularMainText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 35F)
            }
            if (ageInTime_one > 999999) {
                circularMainText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15F)
            }

            circularMainText.setText("$ageInTime_one")//21
            circular_maintext_2.setText("Hours")//years selected
            //to prevent negative values
            if (ageInTime_two < 0) {
                result_main_text_3.setText("Null")
            } else {
                result_main_text_3.setText("$ageInTime_two minutes & $ageInTime_three seconds old")
            }

        }
//        #######################################################
//buttons functions #################################
        fun redirectBack(){
    Intent(this, MainActivity::class.java).also {
        startActivity(it)
    }
        }

        fun redirectSupport(){
            Intent(this, SupportMePage::class.java).also {
                startActivity(it)
            }
        }
        //try again
        button2.setOnClickListener { redirectBack() }
//support page
        button3.setOnClickListener { redirectSupport() }
    }
}