package com.example.android.simplecalctest

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class) // diciamo che i test girano con junit4
class CalculatorTest {

    private var mCalculator: Calculator? = null
    @Before     // va eseguito prima di ogni altra cosa perché è la chiamata al costruttore
    fun setUp(){
        mCalculator = Calculator()
    }

    // volendo potrei avere anche un metodo annotato con @After, ad esempio
    // per cancellare dati che ho scritto in memoria per provare

    @Test
    fun addTwoNumbers(){
        val resultAdd = mCalculator!!.add(1.0,1.0)
        assertEquals(2.0, resultAdd, 0.0)
    }

    @Test
    fun addTwoNumbers1(){
        val resultAdd = mCalculator!!.add(-1.0,2.0)
        assertEquals(1.0, resultAdd, 0.0)
    }

    @Test
    fun addTwoNumbers2(){
        val resultAdd = mCalculator!!.add(-1.0,-17.0)
        assertEquals(-18.0, resultAdd, 0.0)
    }

    @Test
    fun addTwoNumbers3(){
        val resultAdd = mCalculator!!.add(1.111,1.111)
        assertEquals(2.222, resultAdd, 0.0)
    }

    @Test
    fun addTwoNumbers4(){
        val resultAdd = mCalculator!!.add(123456781.0, 111111111.0)
        assertEquals(234567892.0, resultAdd, 0.0)
    }

    @Test
    fun subtractTwoNumbers1(){
        val resultAdd = mCalculator!!.sub(1.0, 1.0)
        assertEquals(0.0, resultAdd, 0.0)
    }

    @Test
    fun subtractTwoNumbers2(){
        val resultAdd = mCalculator!!.sub(1.0, 17.0)
        assertEquals(-16.0, resultAdd, 0.0)
    }

    @Test
    fun divideTwoNumbers1(){
        val resultAdd = mCalculator!!.div(32.0, 2.0)
        assertEquals(16.0, resultAdd, 0.0)
    }

    @Test
    fun multiplyTwoNumbers2(){
        val resultAdd = mCalculator!!.mul(32.0, 2.0)
        assertEquals(64.0, resultAdd, 0.0)
    }

}