package rg.info.storagelocator.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.tooling.preview.Preview
import rg.info.storagelocator.ui.components.home.HomeFABComponent
import rg.info.storagelocator.ui.components.home.SearchComponent
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navigateToContainer: (UUID) -> Unit) {

    val (search, onSearchChange) = remember { mutableStateOf("") }

    Scaffold(
        topBar = { TopAppBar({ Text("Storage Locator") }) },
        floatingActionButton = {
            HomeFABComponent(navigateToContainer = navigateToContainer)
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                // text field with search icon and close icon
                TextField(
                    value = search,
                    onValueChange = onSearchChange,
                    leadingIcon = { Icon(Icons.Filled.Search, "Search") },
                    trailingIcon = {
                        IconButton(
                            onClick = { onSearchChange("") },
                        ) {
                            search.let { search ->
                                if (search.isNotEmpty())
                                    Icon(Icons.Filled.Close, "Close")
                            }
                        }
                    },
                    placeholder = { Text(text = "Conteneur - Objet") },
                    shape = RoundedCornerShape(40),
                    // hide underline
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                    )
                )
            }

            SearchComponent(
                search = search,
                navigateToContainer = navigateToContainer
            )
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(navigateToContainer = {})
}