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

        val flower_name = intent.getStringExtra(ARG_FLOWER_NAME)

        val detailActivityTextView: TextView = findViewById(R.id.detailActivityTextView)
        detailActivityTextView.text = getString(R.string.flower_phrase, flower_name)

    }

    override fun onDestroy(){
        super.onDestroy()

    }

    companion object{
        const val ARG_FLOWER_NAME = "flower_name"
    }

}