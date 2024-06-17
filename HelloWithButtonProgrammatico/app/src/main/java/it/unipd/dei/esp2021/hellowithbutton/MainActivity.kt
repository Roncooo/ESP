package it.unipd.dei.esp2021.hellowithbutton

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet

class MainActivity : AppCompatActivity()
{
    // Called when the activity is first created
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        Log.v(TAG, "entered onCreate()")

        // Create the TextView
        val tv = TextView(this)
        tv.text = "Press the button, please"

        // Create the Button
        val bu = Button(this)
        bu.text = "Press me"

        val bu2 = Button(this)
        bu2.text = "+1"
        var value: Int = 0;

        val bu3 = Button(this)
        bu3.text = "reset"

        bu2.setOnClickListener{
            value += 1
            tv.text = value.toString()
        }

        bu3.setOnClickListener{
            value = 0
            tv.text = value.toString()
        }

        // Set the action to be performed when the button is pressed
        bu.setOnClickListener { // Perform action on click
            tv.text = "Good job!"
        }

        // All UI elements must have IDs to use ConstraintSet
        bu.id = View.generateViewId()
        bu2.id = View.generateViewId()
        bu3.id = View.generateViewId()
        tv.id = View.generateViewId()

        // Create the layout
        val myLayout = ConstraintLayout(this)

        // Add the UI elements to the layout
        myLayout.addView(bu)
        myLayout.addView(bu2)
        myLayout.addView(bu3)
        myLayout.addView(tv)

        // Add constraints to the layout so that UI elements are positioned correctly
        val mySet = ConstraintSet()
        mySet.clone(myLayout)
        mySet.connect(bu.id, ConstraintSet.LEFT, ConstraintSet.PARENT_ID, ConstraintSet.LEFT)
        mySet.connect(bu.id, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP)

        mySet.connect(bu2.id, ConstraintSet.LEFT, ConstraintSet.PARENT_ID, ConstraintSet.LEFT)
        mySet.connect(bu2.id, ConstraintSet.TOP, bu.id, ConstraintSet.BOTTOM)
        mySet.connect(bu3.id, ConstraintSet.LEFT, ConstraintSet.PARENT_ID, ConstraintSet.LEFT)
        mySet.connect(bu3.id, ConstraintSet.TOP, bu2.id, ConstraintSet.BOTTOM)

        mySet.connect(tv.id, ConstraintSet.LEFT, bu.id, ConstraintSet.RIGHT)
        mySet.connect(tv.id, ConstraintSet.BASELINE, bu.id, ConstraintSet.BASELINE)
        mySet.applyTo(myLayout)


        // Display the layout
        setContentView(myLayout)
    }



    override fun onPause(){
        super.onPause()
        Log.v(TAG, "entered onPause()")
    }


    override fun onStop(){
        super.onStop()
        Log.v(TAG, "entered onStop()")
    }


    override fun onStart(){
        super.onStart()
        Log.v(TAG, "entered onStart()")
    }

    override fun onRestart(){
        super.onRestart()
        Log.v(TAG, "entered onRestart()")
    }

    override fun onDestroy(){
        super.onDestroy()
        Log.v(TAG, "entered onDestroy()")
    }

    companion object{
        private val TAG = "MyActiviy"
    }

}
