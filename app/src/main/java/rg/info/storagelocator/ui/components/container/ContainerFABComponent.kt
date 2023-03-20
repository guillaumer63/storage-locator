package rg.info.storagelocator.ui.components.container

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import rg.info.storagelocator.data.Containers
import java.util.UUID

@Composable
fun ContainerFABComponent(uuid: UUID, onContainerChanged: () -> Unit) {
    val addDialog = remember { mutableStateOf(false) }
    val container = Containers.getContainer(uuid)

    if (addDialog.value) {
        val (newItemName, onNewItemNameChange) = remember { mutableStateOf("") }
        AlertDialog(
            onDismissRequest = { addDialog.value = false },
            icon = { Icon(Icons.Filled.Add, contentDescription = "Add") },
            title = { Text("Ajouter un objet") },
            text = {
                InputComponent(
                    value = newItemName,
                    onValueChange = onNewItemNameChange,
                    "Nom"
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        container.addItem(newItemName)
                        addDialog.value = false
                        // refresh container
                        onContainerChanged()
                    },
                    // green color
                    colors = ButtonDefaults.buttonColors(
                        contentColor = Color.White,
                        containerColor = Color.Green.copy(alpha = 0.5f),
                    ),
                    modifier = Modifier.padding(bottom = 10.dp)
                ) {
                    Text("Ajouter")
                }
            },
            dismissButton = {
                OutlinedButton(
                    onClick = { addDialog.value = false },
                    // align the button to the end of the dialog
                    modifier = Modifier.padding(
                        // to the left
                        start = 16.dp,
                    )
                ) {
                    Text("Annuler")
                }
            }
        )
    }

    FloatingActionButton(
        onClick = {
            addDialog.value = true
        },
        modifier = Modifier.padding(bottom = 10.dp)
    ) {
        Icon(Icons.Filled.Add, contentDescription = "Ajouter")
    }
}