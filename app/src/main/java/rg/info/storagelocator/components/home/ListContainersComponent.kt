package rg.info.storagelocator.components.home

import android.content.Context
import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import rg.info.storagelocator.data.Containers
import rg.info.storagelocator.ui.theme.StorageLocatorTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContainerListComponent() {
    val context: Context = LocalContext.current

    // loading containers from the shared preferences
    val containers = Containers(context)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .verticalScroll(
                rememberScrollState()
            )
            .padding(16.dp)
    ) {

        for (container in containers.getContainers()) {
            ListItem(
                headlineText = { Text(text = container.name) },
                trailingContent = {
                    IconButton(onClick = {
                        // list all the items in the container

                    }) {
                        Icon(
                            Icons.Outlined.KeyboardArrowRight,
                            contentDescription = "Localized description",
                        )
                    }
                }
            )
        }
        Divider()
    }
}


@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun ContainerListComponentLightPreview() {
    StorageLocatorTheme {
        ContainerListComponent()
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ContainerListComponentDarkPreview() {
    StorageLocatorTheme {
        ContainerListComponent()
    }
}