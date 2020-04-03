package com.example.cutsizetools.ui.main

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.cutsizespeed.R
import kotlinx.android.synthetic.main.page_fragment2.*
import kotlin.math.PI
import kotlin.math.floor

class PageFragment2 : Fragment() {
    @Nullable
    override fun onCreateView(inflater: LayoutInflater, @Nullable container: ViewGroup?, @Nullable savedInstanceState: Bundle?): View {
        super.onCreateView(inflater, container, savedInstanceState)
        val rootView: View = inflater.inflate(R.layout.page_fragment2, container, false)

        return rootView
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // access the items of the list
        val arrayGSM = resources.getStringArray(R.array.arGSM)
        // access the spinner
        val spinnerSelectGSM = view.findViewById<Spinner>(R.id.spSelectGsm)
        if (spinnerSelectGSM != null) {
            val spAdapter = ArrayAdapter(
                this.activity!!, android.R.layout.simple_spinner_item,
                arrayGSM)
            spAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
            spinnerSelectGSM.adapter = spAdapter
            spinnerSelectGSM.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>,
                                            view: View, position: Int, id: Long) {
                    val iSpinnerPos : Int = position
                    val sThickness = when (iSpinnerPos ){
                        0 -> "92.0"
                        1 -> "97.0"
                        2 -> "120.0"
                        else ->"0.0"
                    }
                    etThickness.setText(sThickness)
                }
                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }

       // define variable text view from resource
        val tvResult1 = view.findViewById<TextView>(R.id.tvDynRollLength)
        val tvResult2 = view.findViewById<TextView>(R.id.tvDynTimeToEndH)
        val tvResult3 = view.findViewById<TextView>(R.id.tvDynTimeToEndM)
        val tvUnit1 = view.findViewById<TextView>(R.id.tvUnitMeters)
        val tvUnit2 = view.findViewById<TextView>(R.id.tvUnitHours)
        val tvUnit3 = view.findViewById<TextView>(R.id.tvUnitMinutes)
      //  var sEt: String = et.text.toString()

        //editText id's for validation
        val ids = intArrayOf(
            R.id.etThickness,
            R.id.etRollDia,
            R.id.etCoreDia,
            R.id.etSpeed2
        )

        //listen to button click event
       val button2 = view.findViewById<Button>(R.id.circleButton2)
        button2.setOnClickListener(View.OnClickListener {

            var isEmpty = false
            for (id in ids) {
                val et = view.findViewById<View>(id) as EditText
                if (TextUtils.isEmpty(et.text.toString())) {
                    et.error = "harap diisi"
                    isEmpty = true
                }
            }

            when {
                isEmpty -> {
                    /* nothing */
                }
                else -> {
                    val dThickness: Double = etThickness.text.toString().toDouble()
                    val dRollDia: Double = etRollDia.text.toString().toDouble()
                    val dCoreDia: Double = etCoreDia.text.toString().toDouble()
                    val iSpeed: Int = etSpeed2.text.toString().toInt()

                    val dRestLength : Double = ((dRollDia * dRollDia)-(dCoreDia*dCoreDia))* PI/4.0/dThickness
                    //calculate in minutes
                    val dTimeToEnd : Double = dRestLength/iSpeed
                    //get the hours
                    val iTimeToEndHours : Int = floor(dTimeToEnd/60).toInt()
                    //get the minutes
                    val iTimeToEndMinutes :Int = (dTimeToEnd % 60).toInt()

                    tvResult1.text = dRestLength.toString()
                    tvResult2.text = iTimeToEndHours.toString()
                    tvResult3.text = iTimeToEndMinutes.toString()
                    //set visibility
                    tvUnit1.setVisibility(View.VISIBLE)
                    tvUnit2.setVisibility(View.VISIBLE)
                    tvUnit3.setVisibility(View.VISIBLE)
                }
            }
        })
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
  //      (getActivity() as AppCompatActivity?)!!.supportActionBar!!.
   //         setTitle("Time Calculation")
    }
}
