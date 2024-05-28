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
        
/*      COME L'HO FATTO IO
        running = savedInstanceState?.getBoolean(RUNNING_KEY, false) ?: false
        if(running){
            stopwatch.base = savedInstanceState?.getLong(BASE_KEY, 0) ?: 0
            saveOffset()
            stopwatch.start()
        }else{
            offset = savedInstanceState?.getLong(OFFSET_KEY, 0) ?: 0
            setBaseTime()
            stopwatch.stop()
        }*/
        
        // COME LO FA FANTOZZI
        if(savedInstanceState != null){
            offset = savedInstanceState.getLong(OFFSET_KEY)
            running = savedInstanceState.getBoolean(RUNNING_KEY)
            if(running){
                stopwatch.base = savedInstanceState.getLong(BASE_KEY)
                stopwatch.start()
            }
            else setBaseTime()  // non è necessaria perché base viene settata quando si clicca start
        }
        
    }
    
    override fun onSaveInstanceState(outState: Bundle) {
        // non cambia niente fare la super prima o dopo
        super.onSaveInstanceState(outState)
        outState.putLong(OFFSET_KEY, offset)
        outState.putBoolean(RUNNING_KEY, running)
        outState.putLong(BASE_KEY, stopwatch.base)
    }
    
    private fun saveOffset(){
        // è il tempo da quando ho fatto partire il cronometro ad adesso
        offset = SystemClock.elapsedRealtime() - stopwatch.base
    }
    
    private fun setBaseTime(){
        stopwatch.base = SystemClock.elapsedRealtime() - offset
    }
    
    companion object{
        private const val OFFSET_KEY = "offset_key"
        private const val BASE_KEY = "base_key"
        private const val RUNNING_KEY = "running_key"
    }
}
