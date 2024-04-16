package com.example.recyclersample

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailActivity() : AppCompatActivity() {

    // Called when the activity is first created
    override fun onCreate(savedInstanceState: Bundle?)
    {
        Log.v("mytag", "entered detail activity oncreate")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val flower_name = intent.getStringExtra("flower_name")
        val detailActivityTextView: TextView = findViewById(R.id.detailActivityTextView)
        detailActivityTextView.text = flower_name + " is a " + getString(R.string.flower_string)

    }

}