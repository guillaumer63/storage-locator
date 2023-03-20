package rg.info.storagelocator.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import rg.info.storagelocator.data.Containers
import rg.info.storagelocator.ui.components.container.ContainerFABComponent
import rg.info.storagelocator.ui.components.container.SwipeableTextComponent
import rg.info.storagelocator.ui.components.container.TopAppBarComponent
import java.util.UUID


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContainerScreen(
    uuid: UUID,
    navigateToHome: () -> Unit,
    navigateAfterDelete: () -> Unit
) {
    val container = Containers.getContainer(uuid)
    var containerItems by remember {
        mutableStateOf(container.getItems())
    }

    Scaffold(
        topBar = {
            TopAppBarComponent(
                uuid = uuid,
                navigateToHome = navigateToHome,
                navigateAfterDelete = navigateAfterDelete
            )
        },
        floatingActionButton = {
            ContainerFABComponent(
                uuid = uuid,
                onContainerChanged = { containerItems = container.getItems() },
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier.padding(padding)
        ) {
            items(containerItems) { item ->
                SwipeableTextComponent(
                    uuid = uuid,
                    item = item,
                    onContainerChanged = {
                        containerItems = container.getItems()
                    },
                )
                Divider()
            }
        }
    }
}

@Preview
@Composable
fun ContainerScreenPreview() {
    ContainerScreen(
        uuid = UUID.randomUUID(),
        navigateToHome = {},
        navigateAfterDelete = {}
    )
}