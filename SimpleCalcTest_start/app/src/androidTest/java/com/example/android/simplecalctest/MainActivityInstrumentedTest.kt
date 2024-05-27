package com.example.android.simplecalctest

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityInstrumentedTest {
	
	@get:Rule
	val rule: ActivityScenarioRule<MainActivity> = ActivityScenarioRule(MainActivity::class.java)
	
	@Test
	fun addTwoNumbers() {
		// ora le varie text view o edit text non sono più nello spazio di indirizzamento della funzione
		// ma sono nell'emulatore quindi devo andarle a pescare in un modo che non è più findViewById
		
		onView(withId(R.id.operand_one_edit_text)).perform(typeText("1.0"), closeSoftKeyboard())
		onView(withId(R.id.operand_two_edit_text)).perform(typeText("2.0"), closeSoftKeyboard())
		// click in espresso è un oggetto che rappresenta l'azione quindi diciamo tipo design pattern messenger
		// c'è un pezzo di espresso nel mio computer e un pezzo nel dispositivo (perché l'abbiamo scritto in gradle)
		onView(withId(R.id.operation_add_btn)).perform(click())
		
		// recupero il risultato e faccio l'equivalente di una assert
		onView(withId(R.id.operation_result_text_view)).check(matches(withText("3.0")))
		
	}
	
}