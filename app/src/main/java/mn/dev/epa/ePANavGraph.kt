package mn.dev.epa

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navDeepLink
import mn.dev.epa.data.AppContainer
import mn.dev.epa.ui.home.HomeVM
import mn.dev.epa.ui.news.NewsVM

const val PUBLICATION_ID = "publicationId"
const val NEWS_ID = "newsId"

@Composable
fun ePANavGrap(
    appContainer: AppContainer,
    isExpandedScreen: Boolean,
    modifier: Modifier = Modifier,
    navHostController: NavHostController = rememberNavController(),
    startDestination: String = ePADestinations.NEWS_ROUTE
) {
    NavHost(
        navController = navHostController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(
            route = ePADestinations.NEWS_ROUTE,
            deepLinks = listOf(
                navDeepLink {
                    uriPattern = ""
                }
            )
        ){navBackStackEntry ->
            val newsVM: NewsVM = viewModel(
                factory = NewsVM.provideFactory(
                    newsRepository = appContainer.newsRepository,
                    preSelectedNewsId = navBackStackEntry.arguments?.getString(NEWS_ID)
                )
            )
        }
    }
}