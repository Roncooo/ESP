package com.hfad.stopwatch

import android.os.Bundle
import android.os.SystemClock
import android.widget.Button
import android.widget.Chronometer
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    
    private lateinit var stopwatch: Chronometer
    private var running = false
    private var offset: Long = 0
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        stopwatch = findViewById(R.id.stopwatch)
        
        val startButton = findViewById<Button>(R.id.start_button)
        startButton.setOnClickListener{
            if(!running){
                setBaseTime()
                stopwatch.start()
                running = true
            }
        }
        
        val pauseButton = findViewById<Button>(R.id.pause_button)
        pauseButton.setOnClickListener{
            if(running){
                saveOffset()
                stopwatch.stop()
                running = false
            }
        }
        
        val resetButton = findViewById<Button>(R.id.reset_button)
        resetButton.setOnClickListener{
            offset = 0
            setBaseTime()
            // decidiamo che con reset il cronometro riparte da 0 ma non cambia lo stato di running
        }
        
    }
    
    private fun saveOffset(){
        // è il tempo da quando ho fatto partire il cronometro ad adesso
        offset = SystemClock.elapsedRealtime() - stopwatch.base
    }
    
    private fun setBaseTime(){
        stopwatch.base = SystemClock.elapsedRealtime() - offset
    }
    
    
}
