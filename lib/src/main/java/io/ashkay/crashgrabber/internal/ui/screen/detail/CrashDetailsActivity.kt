package io.ashkay.crashgrabber.internal.ui.screen.detail

import android.graphics.Typeface
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.ashkay.crashgrabber.api.CrashGrabber
import io.ashkay.crashgrabber.internal.data.entity.CrashLogEntity
import io.ashkay.crashgrabber.internal.ui.mapper.toDetailScreenModel
import io.ashkay.crashgrabber.internal.ui.model.DetailScreenModel
import io.ashkay.crashgrabber.internal.ui.theme.CrashGrabberTheme
import io.ashkay.crashgrabber.internal.utils.ViewModelFactory
import javax.inject.Inject

internal class CrashDetailsActivity : ComponentActivity() {
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
                        DetailsView(it.toDetailScreenModel())
                    }
                }
            }
        }
    }
}

@Composable
internal fun DetailsView(state: DetailScreenModel, modifier: Modifier = Modifier) {
    LazyColumn(

//        modifier = Modifier
//            .verticalScroll(rememberScrollState())
    ) {
        item {
            Text(
                text = state.fileName,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(8.dp)
            )

            Text(
                text = "Crash Log",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .padding(8.dp, 0.dp)
            )
            Column(
                modifier = Modifier
                    .horizontalScroll(rememberScrollState())
                    .padding(8.dp)
            ) {
                Text(
                    text = state.stacktrace,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontFamily = FontFamily(Typeface.MONOSPACE)
                    )
                )
            }
            Divider()

            Text(
                text = "Meta",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(8.dp)
            )
        }

        items(state.meta.toList()) {
            Row(modifier = Modifier.padding(8.dp, 2.dp)) {
                Text(
                    text = it.first,
                    style = MaterialTheme.typography.titleSmall,
                    modifier = Modifier.width(100.dp)
                )
                Text(
                    text = it.second,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, showSystemUi = true)
@Composable
internal fun DetailsViewPreview() {
    CrashGrabberTheme {
        Surface {
            DetailsView(
                CrashLogEntity(
                    1,
                    "MainActivity.kt",
                    "NPE",
                    """Exception in thread "main" java.lang.NullPointerException: Fictitious NullPointerException  
at ClassName.methodName1(ClassName.java:lineNumber)  
at ClassName.methodName2(ClassName.java:lineNumber)  
at ClassName.methodName3(ClassName.java:lineNumber)  
at ClassName.main(ClassName.java:lineNumber) """,
                    12345,
                    "{\"OS\":\"5.10.110-android12-9-00004-gb92ac325368e-ab8731800\",\"PRODUCT\":\"sdk_gphone64_arm64\",\"DEVICE\":\"emulator64_arm64\",\"MODEL\":\"sdk_gphone64_arm64\",\"API_LEVEL\":\"31\"}"
                ).toDetailScreenModel(),
            )
        }
    }
}