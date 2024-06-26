package it.unipd.dei.esp2021.simplebgplayer

import android.Manifest
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.PermissionChecker

class MainActivity : AppCompatActivity()
{
    private lateinit var buPlay: Button
    private lateinit var buStop: Button

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PermissionChecker.PERMISSION_GRANTED)
                requestPermissions(arrayOf(Manifest.permission.POST_NOTIFICATIONS), REQUEST_CODE)
        }

        // Play button: starts the playback music service
        buPlay = findViewById(R.id.PlayButton)
        buPlay.setOnClickListener {
            val i = Intent(applicationContext, PlayerService::class.java)
            i.putExtra(PlayerService.PLAY_START, true)
            startService(i)
            buPlay.isEnabled = false
            buStop.isEnabled = true
        }

        // Stop button: stops the music by stopping the service
        buStop = findViewById(R.id.StopButton)
        buStop.isEnabled = false
        buStop.setOnClickListener {
            val i = Intent(applicationContext, PlayerService::class.java)
            stopService(i)
            buPlay.isEnabled = true
            buStop.isEnabled = false
        }
		// devo impostare due valori di default:
		//  - uno se savedInstance è null (quello con elvis operator)
		//  - uno se i bundle non sono ancora stati impostati (all'interno di getBoolean)
		buPlay.isEnabled = savedInstanceState?.getBoolean(PLAY_ENABLED, true) ?: true
		buStop.isEnabled = savedInstanceState?.getBoolean(STOP_ENABLED, false) ?: false
	}
	
	override fun onSaveInstanceState(savedInstanceState: Bundle) {
		super.onSaveInstanceState(savedInstanceState)
		savedInstanceState.putBoolean(PLAY_ENABLED, buPlay.isEnabled)
		savedInstanceState.putBoolean(STOP_ENABLED, buStop.isEnabled)
	}
	
	override fun onRequestPermissionsResult(
		requestCode: Int, permissions: Array<out String>, grantResults: IntArray
	) {
		val p = grantResults[0] == PermissionChecker.PERMISSION_GRANTED
		super.onRequestPermissionsResult(requestCode, permissions, grantResults)
		Log.i(TAG, "Notification runtime permission granted: $p")
	}
	
	companion object {
		// Request code for the POST_NOTIFICATIONS permission
		private const val REQUEST_CODE = 21983
		
		private const val PLAY_ENABLED = "play button enabled"
		private const val STOP_ENABLED = "stop button enabled"
		
		// Logcat tag
		private val TAG = MainActivity::class.simpleName
	}
}
