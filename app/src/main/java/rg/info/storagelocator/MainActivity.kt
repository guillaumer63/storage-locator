package rg.info.storagelocator

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import rg.info.storagelocator.data.Containers
import rg.info.storagelocator.ui.screens.ContainerScreen
import rg.info.storagelocator.ui.screens.HomeScreen
import rg.info.storagelocator.ui.theme.StorageLocatorTheme
import java.util.UUID


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StorageLocatorApp()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StorageLocatorApp() {
    val navController = rememberNavController()

    StorageLocatorTheme {
        // Scaffold is a layout component that provides a top app bar and a bottom navigation bar
        Scaffold(
            topBar = { TopAppBar(title = { Text(text = "Storage Locator") }) }
        ) { padding ->
            // Surface is a layout component that provides a background color
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
            ) {
                NavHost(
                    navController = navController,
                    startDestination = Screen.Home.route,
                ) {
                    // Home Screen
                    composable(
                        route = Screen.Home.route
                    ) {
                        HomeScreen(navController = navController)
                    }

                    // Container Screen
                    composable(
                        route = Screen.Container.route + "/{uuid}",
                        // we are passing the uuid as a parameter to the ContainerScreen
                        arguments = listOf(
                            navArgument("uuid") {
                                // the argument is a string so the default value is a random uuid
                                type = NavType.StringType
                                defaultValue = Containers.getRandomUUID().toString()
                            }
                        )
                    ) { entry ->
                        ContainerScreen(
                            uuid = UUID.fromString(entry.arguments?.getString("uuid")),
                            navController = navController
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun RootComponentLightPreview() {
    StorageLocatorApp()
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun RootComponentDarkPreview() {
    StorageLocatorApp()
}