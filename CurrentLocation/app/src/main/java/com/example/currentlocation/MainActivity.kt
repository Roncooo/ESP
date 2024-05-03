package com.example.currentlocation

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

class MainActivity : AppCompatActivity() {

    private lateinit var fusedLocationProviterClient: FusedLocationProviderClient
    private lateinit var tvLatitude: TextView
    private lateinit var tvLongitude: TextView
    private lateinit var tvDistance: TextView
    private var producer = EventProducer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fusedLocationProviterClient = LocationServices.getFusedLocationProviderClient(this)
        tvLatitude = findViewById<TextView>(R.id.latitude)
        tvLongitude = findViewById<TextView>(R.id.longitude)
        tvDistance = findViewById<TextView>(R.id.distance)

        getCurrentLocation()

        val listener = EventListener { location: MyLocation ->
            Unit
            if (location == null) {
                tvLatitude.text = getString(R.string.coordinates_placeholder_error)
                tvLongitude.text = getString(R.string.coordinates_placeholder_error)
                tvDistance.text = getString(R.string.coordinates_placeholder_error)
            } else {
                tvLatitude.text = "${getString(R.string.latitude)}: ${location.latitude}"
                tvLongitude.text = "${getString(R.string.longitude)}: ${location.longitude}"

                var distance = location.distance(
                    MyLocation(
                        getString(R.string.pad_lat).toDouble(),
                        getString(R.string.pad_lon).toDouble()
                    )
                )
                tvDistance.text = "${getString(R.string.distance)}: ${distance} m"
            }
        }

        producer.setEventListener(listener)

    }

    private fun getCurrentLocation(): MyLocation? {
        var myLocation: MyLocation? = null

        if (checkPermission()) {
            if (isLocationEnabled()) {
                // actually get location
                var task =
                    fusedLocationProviterClient.lastLocation.addOnCompleteListener(this) { task ->
                        val location: Location? = task.result
                        if (location == null) {
                            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
                            myLocation = MyLocation(location.latitude, location.longitude)
                            producer.triggerEvent(myLocation!!)
                        }
                    }
            } else {
                // please enable location
                Toast.makeText(this, "Turn on location", Toast.LENGTH_SHORT).show()
                val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                startActivity(intent)
            }
        } else {
            // request permission
            requestPermission()
        }

        return myLocation
    }

    private fun isLocationEnabled(): Boolean {
        val locationManager: LocationManager =
            getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
        )
    }

    private fun checkPermission(): Boolean {
        return (ActivityCompat.checkSelfPermission(
            this, android.Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
            this, android.Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED)
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(
            this, arrayOf(
                android.Manifest.permission.ACCESS_COARSE_LOCATION,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ), PERMISSION_REQUEST_LOCATION
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<out String>, grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == PERMISSION_REQUEST_LOCATION) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(applicationContext, "Granted", Toast.LENGTH_SHORT).show()
                getCurrentLocation()
            } else {
                Toast.makeText(applicationContext, "Denied", Toast.LENGTH_SHORT).show()
            }
        }
    }

    companion object {
        // the value doesn't matter
        private const val PERMISSION_REQUEST_LOCATION = 100
    }

}