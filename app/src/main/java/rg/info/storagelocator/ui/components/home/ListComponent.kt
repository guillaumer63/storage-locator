package rg.info.storagelocator.ui.components.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import rg.info.storagelocator.Screen
import rg.info.storagelocator.data.model.Container

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListComponent(containers: List<Container>, navController: NavController) {
    // this lazy column is used to display the list of containers
    // since there are a lot of containers, we use a lazy column
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(16.dp)
    ) {
        // we use the items function to iterate over the list of containers
        items(containers,
            key = { container -> container.getUUID() }) { container ->
            ListItem(
                headlineText = { Text(text = container.name) },
                modifier = Modifier.clickable {
                    // Navigate to the container screen when clicked
                    navController.navigate(Screen.Container.route + "/${container.getUUID()}")
                },
                trailingContent = {
                    Icon(
                        Icons.Outlined.KeyboardArrowRight,
                        contentDescription = container.name,
                    )
                }

            )
            Divider()
        }
    }
}