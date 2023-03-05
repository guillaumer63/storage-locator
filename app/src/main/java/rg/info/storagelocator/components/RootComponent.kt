package rg.info.storagelocator.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import rg.info.storagelocator.components.container.CreateComponent
import rg.info.storagelocator.components.home.HomeComponent
import rg.info.storagelocator.components.util.BottomBarComponent
import rg.info.storagelocator.components.util.TopBarComponent
import rg.info.storagelocator.ui.theme.StorageLocatorTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RootComponent() {
    StorageLocatorTheme {
        val navController = rememberNavController()
        Scaffold(
            topBar = {
                TopBarComponent()
            },
            bottomBar = {
                BottomBarComponent()
            },
        ) { padding ->
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
            ) {
                NavHost(
                    navController = navController,
                    startDestination = "home"
                ) {
                    composable(route = "home") { HomeComponent() }
                    composable(route = "create") { CreateComponent() }
                }
            }
        }
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun RootComponentLightPreview() {
    StorageLocatorTheme {
        RootComponent()
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun RootComponentDarkPreview() {
    StorageLocatorTheme {
        RootComponent()
    }
}