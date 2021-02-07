package `in`.diptanuchakraborty.agecalculator

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDelegate
import androidx.constraintlayout.solver.widgets.ConstraintWidget.INVISIBLE
import androidx.constraintlayout.widget.ConstraintSet
import com.airbnb.lottie.LottieAnimationView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.muddassir.connection_checker.ConnectionChecker
import com.muddassir.connection_checker.ConnectionState
import com.muddassir.connection_checker.ConnectivityListener
import org.joda.time.*
import org.json.JSONObject
import java.util.*
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {

        //changing the status bar color
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //#####my code starts from here#################
        //-----------initializing the animations --------------------------------

        val ttb =
            AnimationUtils.loadAnimation(this, R.anim.ttb) //declaring the animation
        val ttb2 =
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
        // ####################################
        //-----------Elements animations top to bottom approach--------------
        var section_heading_one = findViewById<TextView>(R.id.section_heading_one).also {
            it.startAnimation(ttb)
        }
        var dateSelecterBtn = findViewById<Button>(R.id.dateSelecterBtn).also {
            ttb2.startOffset = 500
            it.startAnimation(ttb2)
        }
        var selectedDateField = findViewById<TextView>(R.id.selectedDateField).also {


        }
        var robotAnimation = findViewById<LottieAnimationView>(R.id.robotAnimation).also {
            zoomscale.startOffset = 600
            it.startAnimation(zoomscale)
        }
        var matrix_section_text = findViewById<TextView>(R.id.matrix_section_text).also {
            fadein1.startOffset = 700
            it.startAnimation(fadein1)
        }
        var radio_years = findViewById<RadioButton>(R.id.radio_years).also {
            fadein2.startOffset = 800
            it.startAnimation(fadein2)
        }
        var radio_days = findViewById<RadioButton>(R.id.radio_days).also {
            fadein3.startOffset = 850
            it.startAnimation(fadein3)
        }
        var radio_time = findViewById<RadioButton>(R.id.radio_time).also {
            fadein4.startOffset = 900
            it.startAnimation(fadein4)
        }
        var footerbg = findViewById<ImageView>(R.id.footerbg).also {
            footerbtt.startOffset = 1000
            it.startAnimation(footerbtt)
        }
        var checkagebtn = findViewById<Button>(R.id.checkagebtn).also {
            footerbtt1.startOffset = 1100
            it.startAnimation(footerbtt1)
        }
        var copyrightText = findViewById<TextView>(R.id.copyrightText).also {
            footerbtt2.startOffset = 1300
            it.startAnimation(footerbtt2)
        }

        //  ####################################
        var dset: Boolean = false //trigger for setting the date
        var rset: Boolean = false //trigger for setting the matrixSelection
        var matrixSelection: Char = '0' // Global Declarations
        var selectedDayOfMonth: Int = 0// Global Declarations
        var selectedMonth: Int = 0// Global Declarations
        var selectedYear: Int = 0// Global Declarations
        fun getvalues_dates(sdom: Int, sm: Int, sy: Int) {
            selectedDayOfMonth = sdom
            selectedMonth = sm
            selectedYear = sy
        }

        //        date picker function
        fun datePickerFunction() {
            val myCalender = Calendar.getInstance()
            val year = myCalender.get(Calendar.YEAR)
            val month = myCalender.get(Calendar.MONTH)
            val day = myCalender.get(Calendar.DAY_OF_MONTH)

            //date picker dialog
            var dpd = DatePickerDialog(
                this,
                R.style.datePickerDialog,
                DatePickerDialog.OnDateSetListener { view, selectedYear, selectedMonth, selectedDayOfMonth ->
                    //set text view display birth date selected

                    var selectedDate = "$selectedDayOfMonth / ${selectedMonth + 1} / $selectedYear"


                    selectedDateField.run {
                        setText(getString(R.string.selectedDate_text) + "$selectedDate")
                        startAnimation(fadein)
                    }
                    // Date set Triggered
                    dset = true

                    getvalues_dates(selectedDayOfMonth, selectedMonth, selectedYear)
                },
                year,
                month,
                day
            )
            // set the limit of dates to choose from
            dpd.datePicker.maxDate = System.currentTimeMillis()
            dpd.show()

        }
        //###################################################

        dateSelecterBtn.setOnClickListener { datePickerFunction() } // date selector button

//#################################################
//radio item day time year
        radio_years.setOnClickListener {
            matrixSelection = 'Y'
            rset = true

        }
        radio_days.setOnClickListener {
            matrixSelection = 'D'
            rset = true

        }
        radio_time.setOnClickListener {
            matrixSelection = 'T'
            rset = true
        }
        //        #######################################

        //redirect to new activity with variables
        fun displayRedirect() {

//            Toast.makeText(this, "$selectedDayOfMonth", Toast.LENGTH_SHORT).show()
            Intent(this, DisplayAge_Layout::class.java).also {
                it.putExtra("matrixSelection", matrixSelection.toString())
                it.putExtra("selectedYear", selectedYear.toString().toInt())
                it.putExtra("selectedMonth", selectedMonth.toString().toInt())
                it.putExtra("selectedDayOfMonth", selectedDayOfMonth.toString().toInt())
                startActivity(it)

            }
        }


        //#########################################
        //button check age Go
        fun validation() {
            if (rset and dset) {
                displayRedirect()


            } else {
                Toast.makeText(
                    this,
                    getString(R.string.validateEntriesErrorText),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        checkagebtn.setOnClickListener { validation() }

        //check for update json data
        val queue = Volley.newRequestQueue(this)
        val url = "https://agecalculatorapp.diptanuchakraborty.in/api/data"

        // Request a string response from the provided URL.
        val stringRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->
                // Display the first 500 characters of the response string.
                var jsobj = JSONObject()
                jsobj = response //converted to json JSONObject
                var update = response["update"].toString().toInt() //check for update
                var version = response["version"].toString().toDouble() //check for version
                var link = response["link"].toString() //check for link
                var forceupdate =
                    response["forceupdate"].toString().toInt() //check for force update
                //fetch current version from app
                var currentVersion = getString(R.string.version_num).toDouble()

                if (forceupdate == 0) {
                    if (update == 1 && (version > currentVersion)) {
                        val builder = AlertDialog.Builder(this, R.style.datePickerDialog)
                        builder.setTitle("New Version")
                        builder.setMessage(R.string.update_text_normal)


                        builder.setPositiveButton(" Download") { dialog, which ->
                            //download update link
                            val dUrl = "$link"
                            val i = Intent(Intent.ACTION_VIEW)
                            i.data = Uri.parse(dUrl)
                            startActivity(i)
                        }

                        builder.setNegativeButton("Close") { dialog, which ->
                            Toast.makeText(
                                applicationContext,
                                "😒", Toast.LENGTH_SHORT
                            ).show()
                        }

                        builder.show()
                    } //inner if

                } //outerif
                else {
//                        delete everything
                            var  contentView:View = findViewById(R.id.screen1)
                    contentView.visibility = View.GONE
                    val builder = AlertDialog.Builder(this, R.style.datePickerDialog)
                    builder.setTitle("New Version")
                    builder.setMessage(R.string.update_text_crucial)



                    builder.setPositiveButton(" Download") { dialog, which ->
                        //download update link
                        val dUrl = "$link"
                        val i = Intent(Intent.ACTION_VIEW)
                        i.data = Uri.parse(dUrl)
                        startActivity(i)

                    }
                    builder.show()
                }

            },
            { })

// Add the request to the RequestQueue.
        queue.add(stringRequest)


    }


}


