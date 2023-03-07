package rg.info.storagelocator

sealed interface Screen {
    val route: String

    object Home : Screen {
        override val route: String = "home"
    }

    object Container : Screen {
        override val route: String = "search"
    }
}