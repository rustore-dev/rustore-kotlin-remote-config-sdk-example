package ru.rustore.remoteconfigexample

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.google.gson.GsonBuilder
import ru.rustore.remoteconfigexample.ui.theme.RemoteConfigExampleTheme
import ru.rustore.sdk.remoteconfig.RemoteConfigClient

private val gson = GsonBuilder().setPrettyPrinting().create()

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            RemoteConfigExampleTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    val context = LocalContext.current
                    var showConfig by remember { mutableStateOf(false) }

                    if (showConfig) {
                        ConfigScreen(context, onBack = { showConfig = false })
                    } else {
                        BehaviourScreen(
                            currentBehaviour = Settings.updateBehaviour(context),
                            onSelected = { selected ->
                                Settings.saveUpdateBehaviour(context, selected)
                                com.jakewharton.processphoenix.ProcessPhoenix.triggerRebirth(context)
                            },
                            onContinue = { showConfig = true },
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun BehaviourScreen(
    currentBehaviour: UpdateBehaviourType,
    onSelected: (UpdateBehaviourType) -> Unit,
    onContinue: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = "RuStore Remote Config",
            style = MaterialTheme.typography.headlineSmall,
            textAlign = TextAlign.Center,
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Choose update behaviour",
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
        )
        Spacer(modifier = Modifier.height(32.dp))

        UpdateBehaviourType.values().forEach { type ->
            BehaviourButton(type.displayName, selected = currentBehaviour == type) {
                onSelected(type)
            }
        }

        Spacer(modifier = Modifier.height(32.dp))
        Button(
            onClick = onContinue,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 6.dp),
        ) {
            Text("Continue")
        }
    }
}

@Composable
private fun BehaviourButton(label: String, selected: Boolean, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
    ) {
        Text(if (selected) "$label (selected)" else label)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ConfigScreen(context: Context, onBack: () -> Unit) {
    var account by remember { mutableStateOf(Settings.account(context)) }
    var language by remember { mutableStateOf(Settings.language(context)) }
    val events = RemoteConfigEvents.events

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            IconButton(onClick = onBack) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back to behaviour selection",
                )
            }
            Text(
                text = "Update behaviour: ${Settings.updateBehaviour(context).displayName}",
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center,
                modifier = Modifier.weight(1f),
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = account,
            onValueChange = {
                account = it
                Settings.saveAccount(context, it)
            },
            label = { Text("Account") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = language,
            onValueChange = {
                language = it
                Settings.saveLanguage(context, it)
            },
            label = { Text("Language") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
        )

        Spacer(modifier = Modifier.height(16.dp))

        ActionButton("getRemoteConfig()") {
            RemoteConfigEvents.add("getRemoteConfig() -> call")
            RemoteConfigClient.instance
                .getRemoteConfig()
                .addOnSuccessListener { config ->
                    RemoteConfigEvents.add(
                        "getRemoteConfig() -> success:\n${gson.toJson(config)}",
                    )
                }
                .addOnFailureListener {
                    RemoteConfigEvents.add("getRemoteConfig() -> failure: ${it.toLogMessage()}")
                }
        }
        ActionButton("getShortSegments()") {
            RemoteConfigEvents.add("getShortSegments() -> call")
            RemoteConfigClient.instance
                .getShortSegments()
                .addOnSuccessListener { result ->
                    RemoteConfigEvents.add("getShortSegments() -> success: ${result ?: "null"}")
                }
                .addOnFailureListener {
                    RemoteConfigEvents.add("getShortSegments() -> failure: ${it.toLogMessage()}")
                }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Divider()
        Spacer(modifier = Modifier.height(8.dp))

        EventsLog(
            events = events,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
        )
    }
}

@Composable
private fun ActionButton(label: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
    ) {
        Text(label)
    }
}

private val ErrorLineColor = Color(0xFFFFE4E1)

@Composable
private fun EventsLog(events: List<String>, modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(events) { line ->
                val background = if (isErrorLine(line)) {
                    Modifier
                        .background(ErrorLineColor, MaterialTheme.shapes.small)
                        .padding(horizontal = 6.dp)
                } else {
                    Modifier
                }
                Text(
                    text = line,
                    style = MaterialTheme.typography.bodySmall.copy(fontFamily = FontFamily.Monospace),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 1.dp)
                        .then(background),
                )
            }
        }
    }
}

private fun isErrorLine(line: String): Boolean =
    line.contains(" -> failure") ||
        line.startsWith("backgroundJobErrors") ||
        line.startsWith("remoteConfigNetworkRequestFailure")
