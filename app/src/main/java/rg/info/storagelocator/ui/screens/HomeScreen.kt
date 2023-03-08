package rg.info.storagelocator.ui.screens

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import rg.info.storagelocator.data.Containers
import rg.info.storagelocator.ui.components.HomeFABComponent
import rg.info.storagelocator.ui.components.ListComponent
import rg.info.storagelocator.ui.theme.StorageLocatorTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    Containers.loadContainers(LocalContext.current)

    val (search, onSearchChange) = remember { mutableStateOf("") }

    Scaffold(
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
                        textStyle = TextStyle(fontSize = 17.sp),
                        leadingIcon = { Icon(Icons.Filled.Search, null, tint = Color.Gray) },
                        modifier = Modifier
                            .padding(10.dp)
                            .background(Color(0xFFE7F1F1), RoundedCornerShape(24.dp)),
                        placeholder = { Text(text = "Objet") },
                        colors = TextFieldDefaults.textFieldColors(
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            cursorColor = Color.DarkGray
                        ),
                        shape = RoundedCornerShape(40)
                    )
                }
                ListComponent(navController = navController)
            }
        }
    )
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun HomePreview() {
    StorageLocatorTheme {
        HomeScreen(navController = NavController(LocalContext.current))
    }
}


@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun HomePreviewDark() {
    StorageLocatorTheme {
        HomeScreen(navController = NavController(LocalContext.current))
    }
}