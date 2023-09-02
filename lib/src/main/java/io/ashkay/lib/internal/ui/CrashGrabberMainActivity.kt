package io.ashkay.lib.internal.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import io.ashkay.lib.api.CrashGrabber
import io.ashkay.lib.internal.data.entity.CrashLogEntity
import io.ashkay.lib.internal.di.DaggerCrashGrabberComponent
import io.ashkay.lib.internal.domain.domain.GetCrashLogsUsecase
import io.ashkay.lib.internal.ui.ui.theme.CrashGrabberTheme
import kotlinx.coroutines.launch
import javax.inject.Inject

class CrashGrabberMainActivity : ComponentActivity() {

    @Inject
    lateinit var getCrashLogsUsecase: GetCrashLogsUsecase

    var mList : List<CrashLogEntity>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        CrashGrabber.instance.inject(this)
        lifecycleScope.launch {
            mList = getCrashLogsUsecase()
        }

        setContent {
            CrashGrabberTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("From Lib")
                    LazyColumn {
                       items(mList.orEmpty()) {
                           Text(
                               text = it.message,
                           )
                       }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CrashGrabberTheme {
        Greeting("Android")
    }
}