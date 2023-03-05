package rg.info.storagelocator.components.container

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import rg.info.storagelocator.ui.theme.StorageLocatorTheme
import java.util.UUID


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateComponent() {
    val (containerName, onContainerNameChange) = remember { mutableStateOf("") }
    val (containerDescription, onContainerDescriptionChange) = remember { mutableStateOf("") }
    val (containerLocation, onContainerLocationChange) = remember { mutableStateOf("") }

    val uuid: UUID = UUID.randomUUID()

    Column(
        verticalArrangement = Arrangement.Top,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
        ) {
            OutlinedTextField(
                value = uuid.toString(),
                onValueChange = onContainerNameChange,
                label = { Text("Identifiant") },
                enabled = false
            )

        }
        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            OutlinedTextField(value = containerName,
                onValueChange = onContainerNameChange,
                label = { Text("Nom") })
        }
        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            OutlinedTextField(value = containerDescription,
                onValueChange = onContainerDescriptionChange,
                label = { Text("Description") })
        }
        Spacer(modifier = Modifier.height(24.dp))


        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            OutlinedTextField(value = containerLocation,
                onValueChange = onContainerLocationChange,
                label = { Text("Localisation") })
        }
        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Button(onClick = { /*TODO*/ }) {
                Text("Ajouter")
            }
            OutlinedButton(onClick = { /*TODO*/ }) {
                Text("Annuler")
            }
        }
    }
}


@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun CreateComponentPreview() {
    StorageLocatorTheme {
        CreateComponent()
    }
}


@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun CreateComponentPreviewDark() {
    StorageLocatorTheme {
        CreateComponent()
    }
}