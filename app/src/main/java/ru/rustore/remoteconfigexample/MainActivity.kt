package ru.rustore.remoteconfigexample

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import ru.rustore.remoteconfigexample.ui.theme.RemoteConfigExampleTheme
import ru.rustore.sdk.remoteconfig.RemoteConfigClient

class MainActivity : ComponentActivity() {
    val DEFAULT_COLOR = "default"
    val RED_COLOR = "red"
    val GREEN_COLOR = "green"
    val BLUE_COLOR = "blue"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val color = mutableStateOf(Color(0xFFFFFFFF))

        setContent {
            LaunchedEffect(true) {
                RemoteConfigClient.instance
                    .getRemoteConfig()
                    .addOnSuccessListener { rc ->
                        Log.i(
                            "RemoteConfigClient",
                            rc.getString("background")
                        )

                        color.value = when (rc.getString("background")) {
                            DEFAULT_COLOR ->
                                Color(0xFFFFFFFF)
                            RED_COLOR ->
                                Color(0xFFFF0000)
                            GREEN_COLOR ->
                                Color(0xFF00FF00)
                            BLUE_COLOR ->
                                Color(0xFF0000FF)
                            else ->
                                Color(0xFFFFFFFF)
                        }
                    }
            }

            // Color(red, green, blue, alpha)
            RemoteConfigExampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = color.value) {
                    Greeting("Android")
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
    RemoteConfigExampleTheme {
        Greeting("Android")
    }
}
