package io.ashkay.lib.internal.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import io.ashkay.lib.api.CrashGrabber
import io.ashkay.lib.internal.data.entity.CrashLogEntity
import io.ashkay.lib.internal.ui.ui.theme.CrashGrabberTheme
import io.ashkay.lib.internal.utils.ViewModelFactory
import javax.inject.Inject

class CrashGrabberMainActivity : ComponentActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: CrashGrabberMainViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        CrashGrabber.getOrCreate(this).inject(this)


        setContent {
            CrashGrabberTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val state = viewModel.uiState.collectAsState()
                    CrashLogList(state.value.crashLogs)
                }
            }
        }
    }
}

@Composable
fun CrashLogList(crashLogs: List<CrashLogEntity>, modifier: Modifier = Modifier) {
    LazyColumn {
        items(crashLogs) {
            val context = LocalContext.current
            Column(Modifier.clickable {
                context.startActivity(Intent(context, CrashDetailsActivity::class.java).apply {
                    putExtra("id", it.id)
                })
            }) {
                Text(
                    text = it.fileName,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = it.stacktrace.substring(0..50),
                )
                Divider()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CrashGrabberTheme {
        CrashLogList(
            listOf(
//                CrashLogEntity("preview crash list"),
//                CrashLogEntity("preview crash list 2")
            )
        )
    }
}