package com.rakuishi.postalcode3.presentation.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.rakuishi.postalcode3.presentation.App
import com.rakuishi.postalcode3.presentation.navigation.NavigationItem
import com.rakuishi.postalcode3.presentation.theme.AppTheme
import com.rakuishi.postalcode3.presentation.ui.favorite.FavoriteScreen
import com.rakuishi.postalcode3.presentation.ui.search.SearchScreen
import com.rakuishi.postalcode3.presentation.ui.search.SearchViewModelFactory

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                Surface {
                    MainScreen()
                }
            }
        }
    }

    @Composable
    private fun MainScreen() {
        val navController = rememberNavController()

        Scaffold(
            bottomBar = {
                BottomNavigationBar(navController)
            }
        ) {
            NavigationHost(
                app = applicationContext as App,
                navController
            )
        }
    }
}

@Composable
private fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        NavigationItem.Search,
        NavigationItem.Favorite
    )

    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry.value?.destination?.route

    NavigationBar {
        items.forEach { item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        painterResource(id = item.icon),
                        contentDescription = stringResource(id = item.name)
                    )
                },
                label = {
                    Text(text = stringResource(id = item.name))
                },
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        launchSingleTop = true
                        restoreState = true
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                    }
                }
            )
        }
    }
}

@Composable
private fun NavigationHost(
    app: App,
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = NavigationItem.Search.route
    ) {
        composable(NavigationItem.Search.route) {
            SearchScreen(
                viewModel = viewModel(
                    factory = SearchViewModelFactory(app.postalCodeRepository)
                )
            )
        }
        composable(NavigationItem.Favorite.route) {
            FavoriteScreen()
        }
    }
}
