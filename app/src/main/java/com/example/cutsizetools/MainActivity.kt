package com.example.cutsizetools

//import com.example.cutsizetools.ui.main.SectionsPagerAdapter

import android.os.Bundle
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.cutsizespeed.R


class MainActivity : AppCompatActivity() {

  //  @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

 override fun onCreateOptionsMenu(menu: Menu?): Boolean {
     // Inflate the menu; this adds items to the action bar if it is present.
     menuInflater.inflate(R.menu.menu, menu)
     return true
 }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here.
        val id = item.getItemId()

       if (id == R.id.action_one) {
           val toast = Toast.makeText( applicationContext,
              "v2.0 by Bunut 12.3", Toast.LENGTH_LONG)
          toast.setGravity(Gravity.BOTTOM or Gravity.CENTER_HORIZONTAL, 0, 50)
          toast.show()
          return true
     }
     return super.onOptionsItemSelected(item)

  }


}
