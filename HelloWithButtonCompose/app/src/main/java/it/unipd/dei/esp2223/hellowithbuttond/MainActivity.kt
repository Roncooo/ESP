package it.unipd.dei.esp2223.hellowithbuttond

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import it.unipd.dei.esp2223.hellowithbuttond.ui.theme.ComposeHelloWithButtonTheme

class MainActivity: ComponentActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        setContent{
            ComposeHelloWithButtonTheme{
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    // The Box layout composable will size itself to fit the content.
                    // Default alignment is TopStart
                    Box()
                    {
                        MyButtonTextPair()
                    }
                }
            }
        }
    }
}

@Composable
fun MyButtonTextPair()
{
    val pleasePress = stringResource(R.string.please_press)
    val goodJob = stringResource(R.string.good_job)

    // Reference: https://developer.android.com/jetpack/compose/state
    var t by remember { mutableStateOf(pleasePress) }

    // The Row layout composable places its children in a horizontal sequence
    Row(verticalAlignment = Alignment.CenterVertically)
    {
        Button(modifier = Modifier.padding(4.dp), onClick = { t = goodJob })
        {
            Text(text = stringResource(R.string.press_me))
        }
        Text(text = t)
    }
}
