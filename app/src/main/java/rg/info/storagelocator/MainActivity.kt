package rg.info.storagelocator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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
@Preview
@Composable
fun StorageLocatorApp() {
    StorageLocatorTheme {
        val navController = rememberNavController()
        Containers.loadContainers(LocalContext.current)

        Scaffold { padding ->
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                NavHost(
                    navController = navController,
                    startDestination = Screen.Home.route
                ) {
                    composable(Screen.Home.route) {
                        HomeScreen(
                            navigateToContainer = { uuid ->
                                navController.navigate(
                                    Screen.Container.createRoute(
                                        uuid.toString()
                                    )
                                )
                            }
                        )
                    }

                    composable(Screen.Container.createRoute("{uuid}")) { entry ->
                        // entry contains the arguments passed to the route
                        ContainerScreen(
                            uuid = UUID.fromString(entry.arguments?.getString("uuid")),
                            navigateToHome = {
                                navController.navigate(Screen.Home.route)
                            },
                            navigateAfterDelete = {
                                navController.popBackStack()
                            }
                        )
                    }
                }
            }
        }
    }
}