package com.example.tugas4_crud_kt.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.tugas4_crud_kt.presentation.screens.Screens
import com.example.tugas4_crud_kt.presentation.screens.CreateScreen
import com.example.tugas4_crud_kt.presentation.screens.DeleteScreen
import com.example.tugas4_crud_kt.presentation.screens.ReadScreen
import com.example.tugas4_crud_kt.presentation.screens.UpdateScreen

@Composable
fun NavigationRoute(navHostController: NavHostController, values: PaddingValues) {
    NavHost(
        navController = navHostController,
        startDestination = Screens.CreatePage.route
    ) {
        composable(route = Screens.CreatePage.route){
            CreateScreen(values)
        }
        composable(route = Screens.ReadPage.route){
            ReadScreen(values)
        }
        composable(route = Screens.UpdatePage.route){
            UpdateScreen(values)
        }
        composable(route = Screens.DeletePage.route){
            DeleteScreen(values)
        }
    }
}