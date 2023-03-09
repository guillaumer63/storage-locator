package rg.info.storagelocator.ui.components.container

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Delete
import androidx.compose.material.icons.twotone.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import me.saket.swipe.SwipeAction
import me.saket.swipe.SwipeableActionsBox
import rg.info.storagelocator.Screen
import rg.info.storagelocator.data.Containers
import rg.info.storagelocator.data.model.Container
import rg.info.storagelocator.ui.theme.Purple40
import java.util.UUID

@Composable
fun SwipeableTextComponent(uuid: UUID, item: String, navController: NavController) {

    val editDialog = remember { mutableStateOf(false) }
    val deleteDialog = remember { mutableStateOf(false) }
    val (newItemName, onNewItemNameChange) = remember { mutableStateOf("") }
    var container = Containers.getContainer(uuid)
    if (container == null) {
        container = Container("Conteneur", "", "", Containers.getRandomUUID())
    }

    val edit = SwipeAction(
        icon = rememberVectorPainter(image = Icons.TwoTone.Edit),
        background = Purple40,
        onSwipe = { editDialog.value = true }
    )

    val delete = SwipeAction(
        icon = rememberVectorPainter(image = Icons.TwoTone.Delete),
        background = Red,
        onSwipe = { deleteDialog.value = true }
    )

    if (editDialog.value) {
        AlertDialog(
            onDismissRequest = { editDialog.value = false },
            title = { Text("Modifier un objet") },
            text = {
                InputComponent(
                    value = newItemName,
                    onValueChange = onNewItemNameChange,
                    "Nom"
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        if (newItemName == "") {
                            onNewItemNameChange(item)
                        }
                        container.updateItem(oldItem = item, newItem = newItemName)
                        editDialog.value = false
                        // refresh container
                        navController.navigate(Screen.Container.route + "/${uuid}")
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
            }
        )
    }

    if (deleteDialog.value) {
        AlertDialog(
            onDismissRequest = { deleteDialog.value = false },
            title = { Text("Supprimer un objet") },
            text = { Text("Voulez-vous vraiment supprimer cet objet ?") },
            confirmButton = {
                Button(
                    onClick = {
                        container.removeItem(item)
                        deleteDialog.value = false
                        // refresh container
                        navController.navigate(Screen.Container.route + "/${uuid}")
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Red
                    ),
                    modifier = Modifier.padding(bottom = 10.dp)
                ) {
                    Text("Supprimer")
                }
            },
            dismissButton = {
                OutlinedButton(
                    onClick = { deleteDialog.value = false },
                    modifier = Modifier.padding(bottom = 10.dp)
                ) {
                    Text("Annuler")
                }
            }
        )
    }

    SwipeableActionsBox(
        startActions = listOf(edit),
        endActions = listOf(delete),
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = item,
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        )
    }
}
