package com.example.currentlocation

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

class MainActivity : AppCompatActivity() {

    private var producer = EventProducer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var tvLatitude = findViewById<TextView>(R.id.latitude)
        var tvLongitude = findViewById<TextView>(R.id.longitude)
        var button = findViewById<Button>(R.id.distance_button)


        var distance = -1.1;

        getCurrentLocation()

        val listener = EventListener { location: MyLocation ->

            if (location == null) {
                tvLatitude.text = getString(R.string.coordinates_placeholder_error)
                tvLongitude.text = getString(R.string.coordinates_placeholder_error)
            } else {
                tvLatitude.text = "${getString(R.string.latitude)}: ${location.latitude}"
                tvLongitude.text = "${getString(R.string.longitude)}: ${location.longitude}"

                distance = location.distance(
                    MyLocation(
                        getString(R.string.prato_della_valle_lat).toDouble(),
                        getString(R.string.prato_della_valle_lon).toDouble()
                    )
                )

            }
        }

        button.setOnClickListener { view ->
            var intent = Intent(view.context, DistanceActivity::class.java)
            intent.putExtra("distance", distance)
            startActivity(intent)
        }


        producer.setEventListener(listener)

    }

    private fun getCurrentLocation(): MyLocation? {
        var myLocation: MyLocation? = null

        if (checkPermission()) {
            if (isLocationEnabled()) {
                // actually get location
                var fusedLocationProviterClient =
                    LocationServices.getFusedLocationProviderClient(this)
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