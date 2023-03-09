package rg.info.storagelocator.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import rg.info.storagelocator.data.Containers
import rg.info.storagelocator.data.model.Container
import rg.info.storagelocator.ui.components.container.ContainerFABComponent
import rg.info.storagelocator.ui.components.container.TopAppBarComponent
import java.util.UUID


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContainerScreen(uuid: UUID, navController: NavController) {
    // loading container from Containers
    var container = Containers.getContainer(uuid)
    // if container is null, we are creating a new container with a random UUID
    if (container == null) {
        container = Container("Conteneur", "", "", Containers.getRandomUUID())
    }

    Scaffold(
        topBar = { TopAppBarComponent(uuid = uuid, navController = navController) },
        floatingActionButton = {
            ContainerFABComponent(
                uuid = uuid
            )
        }, content = { paddingValues ->
            LazyColumn(
                modifier = Modifier.padding(paddingValues),
                verticalArrangement = Arrangement.Top,
            ) {
                items(container.getItems()) { item ->
                    var oldItem = item

                    ListItem(
                        headlineText = {
                            Row(
                                horizontalArrangement = Arrangement.Start,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                IconButton(onClick = {
                                    container.removeItem(item)
                                }) {
                                    Icon(Icons.Filled.Delete, contentDescription = "Supprimer")
                                }
                                TextField(value = item, onValueChange = {
                                    container.updateItem(oldItem, it)
                                    oldItem = it
                                })
                            }
                        }
                    )
                    Divider()
                }
            }
        }
    )
}