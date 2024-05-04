package com.example.currentlocation

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DistanceActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_distance)

        var tvDistance = findViewById<TextView>(R.id.distance)
        var distance = intent.getDoubleExtra("distance", -1.1)
        if (distance == -1.1)
            tvDistance.text = getString(R.string.calculating)
        else
            tvDistance.text = "${String.format("%.3f", distance)} m";

    }

}