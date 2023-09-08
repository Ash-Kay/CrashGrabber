package io.ashkay.lib.internal.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import io.ashkay.lib.api.CrashGrabber
import io.ashkay.lib.internal.data.entity.CrashLogEntity
import io.ashkay.lib.internal.ui.ui.theme.CrashGrabberTheme
import io.ashkay.lib.internal.utils.ViewModelFactory
import javax.inject.Inject

class CrashDetailsActivity : ComponentActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: CrashDetailsViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        CrashGrabber.getOrCreate(this).inject(this)

        viewModel.getCrashById(intent.getIntExtra("id", -1))

        setContent {
            CrashGrabberTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val state = viewModel.uiState.collectAsState()
                    state.value?.let {
                        MainView(it)
                    }
                }
            }
        }
    }
}

@Composable
fun MainView(state: CrashLogEntity, modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier.verticalScroll(rememberScrollState())
    ) {
        Text(
            text = state.timestamp.toString(),
            modifier = modifier
        )
        Divider()
        Text(
            text = state.fileName,
            modifier = modifier
        )
        Divider()
        Text(
            text = state.stacktrace,
            modifier = modifier
        )
        Divider()
        Text(
            text = state.meta.orEmpty(),
            modifier = modifier
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    CrashGrabberTheme {
//        MainView("Android")
    }
}