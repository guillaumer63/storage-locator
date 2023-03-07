package rg.info.storagelocator.ui.components

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
import androidx.navigation.NavController
import rg.info.storagelocator.Screen
import rg.info.storagelocator.data.Containers
import rg.info.storagelocator.ui.theme.StorageLocatorTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListComponent(navController: NavController) {
    // loading containers from the shared preferences

//    print containers

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .verticalScroll(
                rememberScrollState()
            )
            .padding(16.dp)
    ) {

        for (container in Containers.getContainers()) {
            ListItem(
                headlineText = { Text(text = container.name) },
                trailingContent = {
                    IconButton(onClick = {
                        // accessing the container screen
                        navController.navigate(
                            Screen.Container.route + "/${container.getUUID()}"
                        )

                    }) {
                        Icon(
                            Icons.Outlined.KeyboardArrowRight,
                            contentDescription = "Localized description",
                        )
                    }
                }
            )
            Divider()
        }
    }
}


@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun ContainerListComponentLightPreview() {
    StorageLocatorTheme {
        ListComponent(navController = NavController(LocalContext.current))
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ContainerListComponentDarkPreview() {
    StorageLocatorTheme {
        ListComponent(navController = NavController(LocalContext.current))
    }
}