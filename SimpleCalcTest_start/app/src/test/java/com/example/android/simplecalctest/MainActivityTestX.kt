package com.example.android.simplecalctest

import android.os.Build
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(maxSdk = Build.VERSION_CODES.S)     // specifico il massimo di versione da testare
class MainActivityTestX {

    // praticamente questo scenario mi fa aprire "virtualmente" MainActivity
    // e me la fa arrivare alla fase di launch
    private lateinit var scenario: ActivityScenario<MainActivity>

    @Before
    fun startUp(){
        scenario = ActivityScenario.launch(MainActivity::class.java)
    }

    @Test
    fun addTwoNumbers(){
        scenario.moveToState(Lifecycle.State.RESUMED)
        scenario.onActivity { activity: MainActivity ->
            // recupreo gli elementi grafici
            val operandOne = activity.findViewById<EditText>(R.id.operand_one_edit_text)
            val operandTwo = activity.findViewById<EditText>(R.id.operand_two_edit_text)
            val operationResult = activity.findViewById<TextView>(R.id.operation_result_text_view)
            val operationAdd = activity.findViewById<Button>(R.id.operation_add_btn)

            // ora imposto il contenuto degli editText
            operandOne.setText("1.0")
            operandTwo.setText("1.0")
            operationAdd.performClick()
            assertEquals("2.0", operationResult.text.toString())
        }
    }


    @Test
    fun additionIsCorrect(){
        assertEquals(4.0, 2.0+2.0, 0.0)
    }

    @After
    fun cleanUp(){
        scenario.close()
    }

}