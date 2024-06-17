package it.unipd.dei.esp2021.hellowithbuttond

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity()
{
    // Called when the activity is first created
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        // Display the layout
        setContentView(R.layout.activity_main)

        // Get references to the TextView and the button
        // Do it AFTER setContentView()! Before setContentView()
        // the objects have not been instantiated yet
        // Once and for all: Kotlin synthetics are not a recommended practice
        // (https://proandroiddev.com/the-argument-over-kotlin-synthetics-735305dd4ed0)
        val tv : TextView = findViewById(R.id.tv)
        val bu : Button = findViewById(R.id.bu)

        // Set the action to be performed when the button is pressed
        bu.setOnClickListener { // Perform action on click
            tv.text = getString(R.string.good_job)
        }
    }
}
