package com.example.recyclersample

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {

    // Called when the activity is first created
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        // val next : Button = findViewById(R.id.activity_detail)
        /* next.setOnClickListener { view ->
            val myIntent = Intent(view.context, DetailActivity::class.java)

            // Start Activity2. No information will be received when Activity2 exits
            startActivity(myIntent)

            // Note: if you need more control over the starting process,
            // consider the alternative method startActivity(Intent!,Bundle?).
            // If you need to pass data to the starting activity,
            // consider adding them as intent extras.
            // If you need to receive a result from the starting activity,
            // look up https://developer.android.com/training/basics/intents/result
        }

         */
    }

}