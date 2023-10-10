package io.ashkay.crashgrabber.internal.ui.screen.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.ashkay.crashgrabber.api.CrashGrabber
import io.ashkay.crashgrabber.internal.data.entity.CrashLogEntity
import io.ashkay.crashgrabber.internal.ui.mapper.toMainScreenModel
import io.ashkay.crashgrabber.internal.ui.model.MainScreenModel
import io.ashkay.crashgrabber.internal.ui.screen.detail.CrashDetailsActivity
import io.ashkay.crashgrabber.internal.ui.theme.CrashGrabberTheme
import io.ashkay.crashgrabber.internal.utils.ViewModelFactory
import io.ashkay.crashgrabber.internal.utils.toTime
import javax.inject.Inject

internal class CrashGrabberMainActivity : ComponentActivity() {

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
                    val crashLogs = state.value.crashLogs.map { it.toMainScreenModel() }
                    CrashLogList(crashLogs)
                }
            }
        }
    }
}

@Composable
internal fun CrashLogList(crashLogs: List<MainScreenModel>, modifier: Modifier = Modifier) {
    LazyColumn {
        items(crashLogs) {
            val context = LocalContext.current
            Column(
                modifier = Modifier
                    .clickable {
                        startDetailsActivity(context, it.id)
                    }) {
                Column(
                    modifier = Modifier
                        .padding(8.dp)
                ) {
                    Text(
                        text = it.fileName,
                        style = MaterialTheme.typography.titleMedium,
                    )
                    Text(
                        text = it.message,
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Gray
                    )
                    Text(
                        text = it.timestamp.toTime(),
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Gray
                    )
                }
                Divider()
            }
        }
    }
}

internal fun String.substringOrFull(endIndex: Int): String {
    val length = this.length
    val minLength = minOf(length, endIndex) - 1
    return this.substring(0..minLength)
}

private fun startDetailsActivity(
    context: Context,
    crashId: Int
) {
    context.startActivity(
        Intent(
            context,
            CrashDetailsActivity::class.java
        ).apply {
            putExtra("id", crashId)
        })
}

@Preview(showBackground = true)
@Preview(showBackground = true, showSystemUi = true)
@Composable
internal fun MainActivityPreview() {
    CrashGrabberTheme {
        Surface {
            CrashLogList(
                listOf(
                    CrashLogEntity(1, "MainActivity.kt", "NPE", "lorem",12345, "jsonmeta").toMainScreenModel(),
                    CrashLogEntity(1, "DetailsActivity.kt", "uninit var access", "lorem",1000, "jsonmeta").toMainScreenModel(),
                )
            )
        }
    }
}