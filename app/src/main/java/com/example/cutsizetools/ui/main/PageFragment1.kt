package com.example.cutsizetools.ui.main

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.cutsizespeed.R
import kotlinx.android.synthetic.main.activity_main.*

class PageFragment1 : Fragment() {
    companion object {
        private const val ARG_SECTION_NUMBER = "section_number"
        @JvmStatic
        fun newInstance(sectionNumber: Int): PageFragment1 {
            return PageFragment1().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }

private lateinit var viewModel: PageViewModel

override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    viewModel = ViewModelProviders.of(this).get(PageViewModel::class.java).apply {
        setIndex(arguments?.getInt(ARG_SECTION_NUMBER) ?: 1)
    }

}

override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
    val rootView1 = inflater.inflate(R.layout.page_fragment1, container, false)
//    val textView: TextView = rootView1.findViewById(R.id.section_label)
//    viewModel.text.observe(viewLifecycleOwner, Observer<String> {
//        textView.text = it
//    })
    return rootView1
}

override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    // access the items of the list
    val sizeLength = resources.getStringArray(R.array.arSizes)
    // access the spinner
    val spinner = view.findViewById<Spinner>(R.id.sizeSpinner)
    if (spinner != null) {
        val LTRadapter = ArrayAdapter(
            this.activity!!, android.R.layout.simple_spinner_item,
            sizeLength)

        LTRadapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
        spinner.adapter = LTRadapter

        spinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>,
                                        view: View, position: Int, id: Long) {
                val iSpinnerPos : Int = position
                val sSizeLength = when (iSpinnerPos){
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

     // Get the clicked radio button instance
    //val iRadioCheckedId: Int = radio_group.checkedRadioButtonId
    //val radioGroup1 = view.findViewById<Button>(R.id.radio_group)
   radio_group.setOnCheckedChangeListener(
       RadioGroup.OnCheckedChangeListener{ group, checkedId ->
   //     var text = "test"
       if(R.id.bRadioButton1 ==checkedId) {
           tvSpeedUnit.text =getString(R.string.speedUnit2)
       }else {
           tvSpeedUnit.text = getString(R.string.speedUnit1)

       }
    })

   //     RadioGroup.OnCheckedChangeListener{radio_group,}
   // )
       // tvSpeedUnit.text =getString(R.string.speedUnit2)


        // Change Unit Speed textView
    //    if(iRadioCheckedId == R.id.bRadioButton1){
    //        tvSpeedUnit.text =getString(R.string.speedUnit2)
    //    }else{
    //        tvSpeedUnit.text = getString(R.string.speedUnit1)
    //    }

    //editText id's for validation
    val ids = intArrayOf(
        R.id.etInputSpeed,
        R.id.etLength,
        R.id.etSheets,
        R.id.etWebs,
        R.id.etPockets
    )

    //listen to button click event
    val button1 = view.findViewById<Button>(R.id.circleButton1)
    button1.setOnClickListener(View.OnClickListener {
        //make sure all edit text are not empty
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

                val dSpeedInput: Double = etInputSpeed.text.toString().toDouble()
                val dSizeLength: Double = etLength.text.toString().toDouble()
                val iSheets: Int = etSheets.text.toString().toInt()
                val iWebs: Int = etWebs.text.toString().toInt()
                val iPockets: Int = etPockets.text.toString().toInt()

                // Get the checked radio button id from radio group
                val id: Int = radio_group.checkedRadioButtonId
                if (id != -1) { // If any radio button checked from radio group
                    //Check Conversion type m/min to r/min
                    val dOutputSpeedTV :Double = when(id){
                        R.id.bRadioButton1 -> dSpeedInput * iWebs * iPockets * 1000 / dSizeLength / iSheets
                        R.id.bRadioButton2 -> dSpeedInput * dSizeLength * iSheets / 1000 / iWebs / iPockets
                        else -> 0.0
                    }
                    tvOutputSpeed.text = dOutputSpeedTV.toString()
                } else {
                    // If no radio button checked in this radio group
                   // Toast.makeText(applicationContext, "On button click :" +
                   //         " nothing selected", Toast.LENGTH_SHORT).show()
                }
            }
        }
    })


}

override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
   // viewModel = ViewModelProviders.of(this).get(PageViewModel::class.java).apply {
   //     setIndex(arguments?.getInt(ARG_SECTION_NUMBER) ?: 1)
   //     }
   //      (getActivity() as AppCompatActivity?)!!.supportActionBar!!.
   //          setTitle("Speed Conversion")
    }

}