package it.unipd.dei.esp2021.switchactivity

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class Activity2 : AppCompatActivity()
{
    // Called when the activity is first created
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_2)

        val previous : Button = findViewById(R.id.Button2)
        previous.setOnClickListener {
            finish()

            // Note: if you need to send results back to the calling activity, look up
            // https://developer.android.com/reference/android/app/Activity#starting-activities-and-getting-results
        }
    }
}
