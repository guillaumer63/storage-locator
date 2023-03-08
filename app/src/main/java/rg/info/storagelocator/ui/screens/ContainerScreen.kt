package rg.info.storagelocator.ui.screens

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import rg.info.storagelocator.Screen
import rg.info.storagelocator.data.Containers
import rg.info.storagelocator.data.model.Container
import rg.info.storagelocator.ui.components.InputComponent
import rg.info.storagelocator.ui.theme.StorageLocatorTheme
import java.util.UUID


@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun ContainerScreen(uuid: UUID, navController: NavController) {
    // loading container from Containers
    var container = Containers.getContainer(uuid)
    // if container is null, we are creating a new container with a random UUID
    if (container == null) {
        container = Container("", "", "", Containers.getRandomUUID())
    }

    val context = LocalContext.current

    val (containerName, onContainerNameChange) = remember { mutableStateOf(container.name) }
    val (containerDescription, onContainerDescriptionChange) = remember { mutableStateOf(container.description) }
    val (containerLocation, onContainerLocationChange) = remember { mutableStateOf(container.location) }
    val openDialog = remember { mutableStateOf(false) }

    if (openDialog.value) {
        AlertDialog(
            onDismissRequest = { openDialog.value = false },
            icon = { Icon(Icons.Filled.Delete, contentDescription = "Delete") },
            title = { Text("Supprimer le conteneur ?") },
            text = { Text("Voulez-vous vraiment supprimer ce conteneur ?") },

            confirmButton = {
                Button(
                    onClick = {
                        Containers.removeContainer(container.getUUID(), context)
                        navController.popBackStack()
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
                    onClick = { openDialog.value = false },
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
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    openDialog.value = true
                },
                content = {
                    Icon(Icons.Filled.Delete, contentDescription = "Ajouter")
                }
            )
        }, content = { paddingValues ->
            Column(
                Modifier.padding(paddingValues)
            ) {
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

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Button(onClick = {

                        // saving the container
                        container.name = containerName
                        container.description = containerDescription
                        container.location = containerLocation

                        Containers.saveContainer(container, context)
                        navController.navigate(Screen.Home.route)

                    }) {
                        Text("Enregistrer")
                    }
                    OutlinedButton(onClick = { navController.navigate(Screen.Home.route) }) {
                        Text("Retour")
                    }
                }
                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.Top,
                ) {
                    items(container.getItems()) { item ->
                        ListItem(
                            headlineText = { Text(item) },
                        )
                        Divider()
                    }
                }
            }
        }
    )
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun ContainerPreview() {
    StorageLocatorTheme {
        ContainerScreen(
            uuid = Containers.getRandomUUID(),
            navController = NavController(LocalContext.current)
        )
    }
}


@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ContainerPreviewDark() {
    StorageLocatorTheme {
        ContainerScreen(
            uuid = Containers.getRandomUUID(),
            navController = NavController(LocalContext.current)
        )
    }
}