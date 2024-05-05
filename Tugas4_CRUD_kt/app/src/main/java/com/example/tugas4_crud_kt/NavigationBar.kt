package com.example.tugas4_crud_kt

import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.tugas4_crud_kt.ui.theme.Blue_kt

@Composable
fun NavigationBar(navHostController: NavHostController) {
    val screens = listOf(
        Screens.CreatePage,
        Screens.ReadPage,
        Screens.UpdatePage,
        Screens.DeletePage
    )
    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    BottomNavigation(
        backgroundColor = Blue_kt,
    ) {
        screens.forEach { screen ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        imageVector = screen.icon,
                        contentDescription = null,
                        modifier = Modifier.padding(bottom = 5.dp)
                    )
               },
                label = {
                    Text(
                        text = screen.title,
                        fontSize = 13.sp
                    )
                },
                selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                onClick = {
                    navHostController.navigate(screen.route)
                },
                selectedContentColor = Color.Black,
                unselectedContentColor = Color.DarkGray,
                modifier = Modifier.padding(vertical = 4.dp)
            )
        }
    }
}