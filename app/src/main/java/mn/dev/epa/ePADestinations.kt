package mn.dev.epa

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController

object ePADestinations {
    const val HOME_ROUTE = "home"
    const val OFFICIAL_DIARY_ROUTE = "official_diary"
    const val SOCIAL_ROUTE = "social"
}

class ePANavigationsActions(navController: NavHostController){
    val navigateToHome: () -> Unit = {
        navController.navigate(ePADestinations.HOME_ROUTE) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
    val navigateToOfficialDiary: () -> Unit = {
        navController.navigate(ePADestinations.OFFICIAL_DIARY_ROUTE) {
            popUpTo(navController.graph.findStartDestination().id){
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
    val navigateToSocial: () -> Unit = {
        navController.navigate(ePADestinations.SOCIAL_ROUTE) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
}