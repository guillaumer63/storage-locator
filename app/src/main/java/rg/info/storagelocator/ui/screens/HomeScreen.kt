package rg.info.storagelocator.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import rg.info.storagelocator.data.Containers
import rg.info.storagelocator.ui.components.home.HomeFABComponent
import rg.info.storagelocator.ui.components.home.ListComponent
import rg.info.storagelocator.ui.components.home.SearchComponent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    Containers.loadContainers(LocalContext.current)

    val (search, onSearchChange) = remember { mutableStateOf("") }

    Scaffold(
        topBar = { TopAppBar(title = { Text(text = "Storage Locator") }) },
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = { HomeFABComponent(navController = navController) },

        content = { padding ->
            Column(modifier = Modifier.padding(padding)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    TextField(
                        value = search,
                        onValueChange = onSearchChange,
                        leadingIcon = { Icon(Icons.Filled.Search, null) },
                        trailingIcon = {
                            IconButton(
                                onClick = { onSearchChange("") },
                            ) {
                                search.let {
                                    if (it.isNotEmpty())
                                        Icon(Icons.Filled.Close, null)
                                }
                            }
                        },
                        placeholder = { Text(text = "Objet") },
                        shape = RoundedCornerShape(40),
                        // hide underline
                        colors = TextFieldDefaults.textFieldColors(
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent,
                        )
                    )
                }
                // if search is empty, we display all containers
                if (search.isEmpty())
                    ListComponent(Containers.getContainers(), navController = navController)
                else
                    SearchComponent(search = search, navController = navController)
            }
        }
    )
}