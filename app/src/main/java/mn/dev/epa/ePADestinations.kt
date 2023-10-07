package mn.dev.epa

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController

object ePADestinations {
    const val NEWS_ROUTE = "news"
    const val OFFICIAL_DIARY_ROUTE = "official_diary"
    const val SOCIAL_ROUTE = "social"
}

class ePANavigationsActions(navController: NavHostController){
    val navigateToNews: () -> Unit = {
        navController.navigate(ePADestinations.NEWS_ROUTE) {
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