package rg.info.storagelocator.components.util

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import rg.info.storagelocator.ui.theme.StorageLocatorTheme

@Composable
fun BottomBarComponent() {
    val selectedIndex = remember { mutableStateOf(0) }

    val itemNames = listOf("Accueil", "Chercher", "Ajouter", "Modifier")

    val itemIcons = listOf(
        Icons.Outlined.Home,
        Icons.Outlined.Search,
        Icons.Outlined.Add,
        Icons.Outlined.Edit
    )
    val itemIconsFilled = listOf(
        Icons.Filled.Home,
        Icons.Filled.Search,
        Icons.Filled.Add,
        Icons.Filled.Edit
    )

    NavigationBar {
        itemNames.forEachIndexed { index, name ->
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = if (selectedIndex.value == index)
                            itemIconsFilled[index]
                        else
                            itemIcons[index],
                        contentDescription = name
                    )
                },
                label = { if (selectedIndex.value == index) Text(text = name) },
                selected = selectedIndex.value == index,
                onClick = { selectedIndex.value = index }
            )
        }
    }
}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_NO)
@Composable
fun BottomBarComponentLightPreview() {
    StorageLocatorTheme {
        BottomBarComponent()
    }
}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun BottomBarComponentDarkPreview() {
    StorageLocatorTheme {
        BottomBarComponent()
    }
}
