package io.ashkay.crashgrabber

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import io.ashkay.crashgrabber.ui.theme.CrashGrabberTheme
import io.ashkay.lib.api.CrashGrabber
import java.text.SimpleDateFormat
import java.util.Calendar

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CrashGrabberTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("From Client")
                }
            }
        }

        CrashGrabber.init(application)
        CrashGrabber.createShortcut(this)
//        CrashGrabber.launchActivity(this)
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Column {
        Text(
            text = "Hello $name!",
            modifier = modifier,
        )
        Button(onClick = {
            throw Exception(
                "WTF client ${
                    SimpleDateFormat("dd-M-yyyy hh:mm:ss").format(
                        Calendar.getInstance().time
                    )
                }"
            )
        }) {
            Text(
                text = "CrashMe",
                modifier = modifier,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CrashGrabberTheme {
        Greeting("Android")
    }
}