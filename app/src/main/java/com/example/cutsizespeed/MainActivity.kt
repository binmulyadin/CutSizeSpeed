package com.example.cutsizespeed

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.TextUtils
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val activityTitle :String = resources.getString(R.string.app_name)
        this.title = activityTitle
        // default value
        tvSpeedUnit.text = "r/min"

        //editText id's for validation
        val ids = intArrayOf(
            R.id.etInputSpeed,
            R.id.etLength,
            R.id.etSheets,
            R.id.etWebs,
            R.id.etPockets
        )

        // access the items of the list
        val sizeLength = resources.getStringArray(R.array.arSizes)

        // access the spinner
        val spinner = findViewById<Spinner>(R.id.sizeSpinner)
        if (spinner != null) {
            val adapter = ArrayAdapter(this,
                R.layout.spinner_style, sizeLength)

            spinner.adapter = adapter
            spinner.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>,
                                            view: View, position: Int, id: Long) {
                 var iSpinnerPos : Int = position
                 var sSizeLength = when (iSpinnerPos){
                     0 -> "297.0"
                     1 -> "420.0"
                     2 -> "257.0"
                     3 -> "364.0"
                     4 -> "279.0"
                     5 -> "356.0"
                     6 -> "297.0"
                    else ->"0.0"
                 }
                    etLength.setText(sSizeLength)
                }
                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }

        // Get radio group selected status and text using button click event
        circleButton.setOnClickListener{

         //check data input validation
         if(!validateEditText(ids)) {
             //if not empty then proceed the data calculation
             //tvOutputSpeed.text ="0.0"
             val dSpeedInput: Double = etInputSpeed.text.toString().toDouble()
             var dSizeLength: Double = etLength.text.toString().toDouble()
             val iSheets: Int = etSheets.text.toString().toInt()
             val iWebs: Int = etWebs.text.toString().toInt()
             val iPockets: Int = etPockets.text.toString().toInt()

             // Get the checked radio button id from radio group
             val id: Int = radio_group.checkedRadioButtonId
             if (id != -1) { // If any radio button checked from radio group
                 //Check Conversion type m/min to r/min
                 var dOutputSpeedTV :Double = when(id){
                     R.id.bRadioButton1 -> dSpeedInput * iWebs * iPockets * 1000 / dSizeLength / iSheets
                     R.id.bRadioButton2 -> dSpeedInput * dSizeLength * iSheets / 1000 / iWebs / iPockets
                     else -> 0.0
                 }
                 tvOutputSpeed.text = dOutputSpeedTV.toString()
             } else {
                 // If no radio button checked in this radio group
                 Toast.makeText(applicationContext, "On button click :" +
                             " nothing selected", Toast.LENGTH_SHORT).show()
             }

         }else{
             //if empty do somethingelse
         }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here.
        val id = item.getItemId()

        if (id == R.id.action_one) {
            val toast = Toast.makeText( applicationContext,
                "v1.0 by Bunut 12.3", Toast.LENGTH_LONG)
            toast.setGravity(Gravity.BOTTOM or Gravity.CENTER_HORIZONTAL, 0, 50)
            toast.show()
            return true
        }
        return super.onOptionsItemSelected(item)

    }

    // Get the selected radio button text using radio button on click listener
    fun radio_button_click(view: View) {
        // Get the clicked radio button instance
       // val radio: RadioButton = findViewById(radio_group.checkedRadioButtonId)
       // Toast.makeText(applicationContext,"On click : ${radio.text}",
       //     Toast.LENGTH_SHORT).show()
        // Change Unit Speed textView
        val iRadioCheckedId: Int = radio_group.checkedRadioButtonId
        if(iRadioCheckedId == R.id.bRadioButton1){
            tvSpeedUnit.setText("r/min")
        }else{
            tvSpeedUnit.setText("m/min")
        }
    }

    fun validateEditText(ids: IntArray): Boolean {
        var isEmpty = false
        for (id in ids) {
            val et = findViewById<View>(id) as EditText
            if (TextUtils.isEmpty(et.text.toString())) {
                et.error = "harap diisi"
                isEmpty = true
            }
        }
        return isEmpty
    }

}
