package rg.info.storagelocator.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import rg.info.storagelocator.data.Container
import rg.info.storagelocator.data.Containers

@Composable
fun ContainersComponent() {
    val context = LocalContext.current
    val containers = Containers(context)

    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            TextButton(onClick = { }) {
                Text(
                    text = "Voici les conteneurs"
                )
            }
        }
        Spacer(modifier = Modifier.height(24.dp))
        for (container in containers.getContainers()) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = container.name
                )
            }
        }
        Spacer(modifier = Modifier.height(24.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            TextButton(onClick = {
                containers.addContainer(
                    Container("name", "desc", "loc"), context
                )
            }) {
                Text(
                    text = "Ajouter un conteneur"
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun ContainersComponentPreview() {
    ContainersComponent()
}