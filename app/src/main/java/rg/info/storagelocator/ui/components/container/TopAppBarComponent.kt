package rg.info.storagelocator.ui.components.container

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import rg.info.storagelocator.data.Containers
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarComponent(uuid: UUID, navigateToHome: () -> Unit, navigateAfterDelete: () -> Unit) {
    val editDialog = remember { mutableStateOf(false) }
    val deleteComponentDialog = remember { mutableStateOf(false) }
    val container = Containers.getContainer(uuid)

    val (containerName, onContainerNameChange) = remember {
        mutableStateOf(container.name)
    }
    val (containerDescription, onContainerDescriptionChange) = remember {
        mutableStateOf(container.description)
    }
    val (containerLocation, onContainerLocationChange) = remember {
        mutableStateOf(container.location)
    }
    val context = LocalContext.current

    if (editDialog.value) {
        AlertDialog(
            onDismissRequest = { editDialog.value = false },
            icon = { Icon(Icons.Filled.Edit, contentDescription = "Edit") },
            title = { Text("Modifier un objet") },
            text = {
                Column {
                    InputComponent(
                        value = uuid.toString(),
                        onValueChange = { },
                        text = "Identifiant",
                        enabled = false
                    )
                    InputComponent(
                        value = containerName,
                        onValueChange = onContainerNameChange,
                        text = "Nom"
                    )

                    InputComponent(
                        value = containerDescription,
                        onValueChange = onContainerDescriptionChange,
                        text = "Description"
                    )

                    InputComponent(
                        value = containerLocation,
                        onValueChange = onContainerLocationChange,
                        text = "Localisation"
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        container.name = containerName
                        container.description = containerDescription
                        container.location = containerLocation

                        Containers.saveContainer(container, context)
                        editDialog.value = false
                    },
                    // green color
                    colors = ButtonDefaults.buttonColors(
                        contentColor = Color.White,
                        containerColor = Color.Green.copy(alpha = 0.5f),
                    ),
                    modifier = Modifier.padding(bottom = 10.dp)
                ) {
                    Text("Modifier")
                }
            },
            dismissButton = {
                OutlinedButton(
                    onClick = { editDialog.value = false },
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

    if (deleteComponentDialog.value) {
        AlertDialog(
            onDismissRequest = { deleteComponentDialog.value = false },
            icon = { Icon(Icons.Filled.Delete, contentDescription = "Delete") },
            title = { Text("Supprimer le conteneur ?") },
            text = { Text("Voulez-vous vraiment supprimer ce conteneur ?") },

            confirmButton = {
                Button(
                    onClick = {
                        Containers.removeContainer(container.getUUID(), context)
                        navigateAfterDelete()
                    },
                    // red color
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Red,
                        contentColor = Color.White,
                    ),
                ) {
                    Text("Supprimer")
                }
            },
            dismissButton = {
                OutlinedButton(
                    onClick = { deleteComponentDialog.value = false },
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

    TopAppBar(
        title = { Text(container.name) },
        navigationIcon = {
            IconButton(onClick = { navigateToHome() }) {
                Icon(Icons.Filled.Close, contentDescription = "Back")
            }
        },
        actions = {
            IconButton(onClick = { editDialog.value = true }) {
                Icon(Icons.Filled.Edit, contentDescription = "Edit")
            }
            IconButton(onClick = { deleteComponentDialog.value = true }) {
                Icon(Icons.Filled.Delete, contentDescription = "Delete")
            }
            IconButton(onClick = {
                container.name = containerName
                container.description = containerDescription
                container.location = containerLocation

                Containers.saveContainer(container, context)
                navigateToHome()
            }) {
                Icon(Icons.Filled.Save, contentDescription = "Save")
            }
        }
    )
}