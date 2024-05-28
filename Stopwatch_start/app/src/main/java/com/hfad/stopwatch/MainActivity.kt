package com.hfad.stopwatch

import android.os.Bundle
import android.os.SystemClock
import android.widget.Button
import android.widget.Chronometer
import androidx.appcompat.app.AppCompatActivity
import com.hfad.stopwatch.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityMainBinding
    
    private var running = false
    private var offset: Long = 0
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        
        binding.startButton.setOnClickListener{
            if(!running){
                setBaseTime()
                binding.stopwatch.start()
                running = true
            }
        }
        
        binding.pauseButton.setOnClickListener{
            if(running){
                saveOffset()
                binding.stopwatch.stop()
                running = false
            }
        }
        
        binding.resetButton.setOnClickListener{
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
                binding.stopwatch.base = savedInstanceState.getLong(BASE_KEY)
                binding.stopwatch.start()
            }
            else setBaseTime()  // non è necessaria perché base viene settata quando si clicca start
        }
        
    }
    
    override fun onPause() {
        super.onPause()
        if(running){
            saveOffset()
            binding.stopwatch.stop()
            // running = false // se ce lo metto vuol dire che riaprendo devo premere start
                                // inoltre onStart non saprebbe se era stato fermato dall'utente oppure
                                // da onStop, e quindi non sa se farlo ripartire o no
        }
    }
    
    override fun onResume() {
        super.onResume()
        if(running){
            setBaseTime()
            binding.stopwatch.start()
            offset = 0 // secondo fantozzi è inutile
        }
    }
    
    override fun onSaveInstanceState(outState: Bundle) {
        // non cambia niente fare la super prima o dopo
        super.onSaveInstanceState(outState)
        outState.putLong(OFFSET_KEY, offset)
        outState.putBoolean(RUNNING_KEY, running)
        outState.putLong(BASE_KEY, binding.stopwatch.base)
    }
    
    private fun saveOffset(){
        // è il tempo da quando ho fatto partire il cronometro ad adesso
        offset = SystemClock.elapsedRealtime() - binding.stopwatch.base
    }
    
    private fun setBaseTime(){
        binding.stopwatch.base = SystemClock.elapsedRealtime() - offset
    }
    
    companion object{
        private const val OFFSET_KEY = "offset_key"
        private const val BASE_KEY = "base_key"
        private const val RUNNING_KEY = "running_key"
    }
}
