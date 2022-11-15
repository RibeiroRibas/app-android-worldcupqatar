package com.ribeiroribas.worldcupqatar.ui.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ribeiroribas.worldcupqatar.R
import com.ribeiroribas.worldcupqatar.ui.screen.GroupStageScreen
import com.ribeiroribas.worldcupqatar.ui.screen.MatchesByDateScreen
import com.ribeiroribas.worldcupqatar.ui.screen.RecebaScreen

@Composable
fun WorldCupQatarApp(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    Scaffold(
        topBar = { WorldCupAppBar() },
        bottomBar = { AppBottomNavigation(navController = navController) },
        modifier = modifier,
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screens.Matches.route,
            Modifier.padding(innerPadding)
        ) {
            composable(Screens.Matches.route) { MatchesByDateScreen() }
            composable(Screens.Groups.route) { GroupStageScreen() }
            composable(Screens.Receba.route) { RecebaScreen() }
        }
    }
}

@Composable
private fun WorldCupAppBar() {
    TopAppBar(
        navigationIcon = {
            Image(
                painter = painterResource(id = R.drawable.cup),
                contentDescription = null,
                modifier = Modifier
                    .padding(4.dp)
                    .requiredWidth(100.dp)
            )
        },
        title = {
            Image(
                painter = painterResource(id = R.drawable.worldcupcatar),
                contentDescription = null,
                modifier = Modifier
                    .padding(vertical = 4.dp)
                    .requiredWidth(125.dp)
            )
        },
        backgroundColor = MaterialTheme.colors.primary
    )
}


@Composable
private fun AppBottomNavigation(navController: NavHostController) {
    val items = listOf(
        Screens.Matches,
        Screens.Groups,
        Screens.Receba
    )
    BottomNavigation(
        backgroundColor = MaterialTheme.colors.primary
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        items.forEach { screen ->
            BottomNavigationItem(icon = {
                Image(
                    painter = painterResource(screen.drawable),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
            },
                label = { Text(stringResource(screen.resourceId)) },
                selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                    }
                }
            )
        }
    }
}