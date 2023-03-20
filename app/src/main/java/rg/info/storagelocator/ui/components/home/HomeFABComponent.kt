package rg.info.storagelocator.ui.components.home

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.QrCodeScanner
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanOptions
import rg.info.storagelocator.data.Containers
import java.util.UUID

@Composable
fun HomeFABComponent(navigateToContainer: (UUID) -> Unit) {

    val wrongUUID = remember { mutableStateOf(false) }
    // Dialog to show when the scanned UUID is not valid
    if (wrongUUID.value) {
        AlertDialog(
            onDismissRequest = { wrongUUID.value = false },
            title = { Text(text = "UUID invalide") },
            text = { Text(text = "L'UUID scanné n'est pas valide.") },
            confirmButton = {
                OutlinedButton(
                    onClick = { wrongUUID.value = false },
                ) {
                    Text(text = "OK")
                }
            }
        )
    }

    val scanLauncher = rememberLauncherForActivityResult(
        contract = ScanContract(),
        onResult = { result ->
            if (result.contents != null && isParsableUUID(result.contents))
                navigateToContainer(UUID.fromString(result.contents))
            else
                wrongUUID.value = true
        }
    )

    Column(
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.padding(16.dp)
    ) {

        FloatingActionButton(
            onClick = {
                val options = ScanOptions()
                options.setOrientationLocked(false)
                    .setBeepEnabled(false)
                    .setPrompt("Scannez l'UUID du conteneur")

                scanLauncher.launch(options)
            },
            modifier = Modifier.padding(bottom = 10.dp)
        ) {
            Icon(Icons.Filled.QrCodeScanner, contentDescription = "Search")
        }

        ExtendedFloatingActionButton(
            onClick = {
                // accessing the container screen
                navigateToContainer(Containers.getRandomUUID())
            },
            content = {
                Icon(Icons.Filled.Add, contentDescription = "Add")
                Spacer(modifier = Modifier.padding(10.dp))
                Text(text = "Nouveau conteneur")
            }
        )
    }
}

// isParsableUUID is a function that checks if a string is a valid UUID or not.
// It returns true if the string is a valid UUID, false otherwise.
fun isParsableUUID(uuid: String): Boolean {
    return try {
        UUID.fromString(uuid)
        true
    } catch (e: IllegalArgumentException) {
        false
    }
}