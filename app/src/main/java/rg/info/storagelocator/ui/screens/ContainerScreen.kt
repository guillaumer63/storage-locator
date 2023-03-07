package rg.info.storagelocator.ui.screens

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import rg.info.storagelocator.Screen
import rg.info.storagelocator.data.Containers
import rg.info.storagelocator.data.model.Container
import rg.info.storagelocator.ui.theme.StorageLocatorTheme
import java.util.UUID


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContainerScreen(uuid: UUID, navController: NavController) {
    // loading container from Containers
    var container = refreshContainer(uuid)
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
            Button(onClick = {
                container.name = containerName
                container.description = containerDescription
                container.location = containerLocation

                Containers.addContainer(container, context)
                navController.navigate(Screen.Home.route)
            }) {
                Text("Enregistrer")
            }
            Button(onClick = {
                openDialog.value = true
            }) {
                Text("Supprimer")
            }
            OutlinedButton(onClick = { navController.navigate(Screen.Home.route) }) {
                Text("Retour")
            }
        }
    }
}

fun refreshContainer(uuid: UUID): Container? {
    return Containers.getContainer(uuid)
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun CreateComponentPreview() {
    StorageLocatorTheme {
        ContainerScreen(
            uuid = Containers.getRandomUUID(),
            navController = NavController(LocalContext.current)
        )
    }
}


@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun CreateComponentPreviewDark() {
    StorageLocatorTheme {
        ContainerScreen(
            uuid = Containers.getRandomUUID(),
            navController = NavController(LocalContext.current)
        )
    }
}