package io.ashkay.sample

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.ashkay.sample.MainActivity.CrashObjectContainer
import io.ashkay.sample.ui.theme.CrashGrabberTheme
import io.ashkay.crashgrabber.api.CrashGrabber

class MainActivity : ComponentActivity() {
    class CrashObjectContainer {
        lateinit var unInitLateInitVar: Context
        val nullObject: Context? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CrashGrabberTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    MainView(CrashObjectContainer())
                }
            }
        }

        CrashGrabber.init(this)
        CrashGrabber.createShortcut(this)
        startActivity(CrashGrabber.getLaunchIntent(this))
    }

    override fun onDestroy() {
        CrashGrabber.clear()
        super.onDestroy()
    }
}

@Composable
fun MainView(
    crashObjectContainer: CrashObjectContainer,
    modifier: Modifier = Modifier
) {
    Column(modifier = Modifier.padding(8.dp)) {
        Button(onClick = {
            throw Exception("!TEST CRASH MESSAGE!")
        }) {
            Text(
                text = "Custom message Crash",
                modifier = modifier,
            )
        }

        Button(onClick = {
            crashObjectContainer.unInitLateInitVar.theme
        }) {
            Text(
                text = "Crash lateinit var",
                modifier = modifier,
            )
        }

        Button(onClick = {
            crashObjectContainer.nullObject!!.theme
        }) {
            Text(
                text = "Crash null object access",
                modifier = modifier,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainViewPreview() {
    CrashGrabberTheme {
        MainView(CrashObjectContainer())
    }
}