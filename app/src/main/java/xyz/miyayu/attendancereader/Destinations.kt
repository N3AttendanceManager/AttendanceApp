package xyz.miyayu.attendancereader

sealed interface Destinations {
    val route: String
    val routeWithArgs: String
        get() = route

    data object Login : Destinations {
        override val route = "login"
    }

    data object Top : Destinations {
        override val route = "top"
    }
}

