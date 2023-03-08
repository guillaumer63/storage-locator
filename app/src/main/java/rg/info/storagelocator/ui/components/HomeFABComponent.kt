package rg.info.storagelocator.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.QrCodeScanner
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import rg.info.storagelocator.Screen
import rg.info.storagelocator.data.Containers

@Composable
fun HomeFABComponent(navController: NavController) {
    Column(
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.padding(16.dp)
    ) {

        FloatingActionButton(
            onClick = { /*TODO*/ },
            content = {
                Icon(Icons.Filled.QrCodeScanner, contentDescription = "Search")
            },
            modifier = Modifier.padding(bottom = 10.dp)
        )
        ExtendedFloatingActionButton(
            onClick = {
                // accessing the container screen
                navController.navigate(
                    Screen.Container.route + "/${Containers.getRandomUUID()}"
                )
            },
            content = {
                Icon(Icons.Filled.Add, contentDescription = "Add")
                Spacer(modifier = Modifier.padding(10.dp))
                Text(text = "Nouveau conteneur")
            }
        )
    }
}