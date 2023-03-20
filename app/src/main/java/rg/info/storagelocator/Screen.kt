package rg.info.storagelocator

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Container : Screen("container") {
        fun createRoute(containerId: String) = "$route/$containerId"
    }
}