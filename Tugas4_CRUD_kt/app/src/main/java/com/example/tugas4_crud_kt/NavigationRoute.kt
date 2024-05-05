package com.example.tugas4_crud_kt

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.tugas4_crud_kt.room.viewmodel.MhsViewModel
import com.example.tugas4_crud_kt.screens.CreateScreen
import com.example.tugas4_crud_kt.screens.DeleteScreen
import com.example.tugas4_crud_kt.screens.ReadScreen
import com.example.tugas4_crud_kt.screens.UpdateScreen

@Composable
fun NavigationRoute(navHostController: NavHostController, values: PaddingValues, viewModel: MhsViewModel) {
    NavHost(
        navController = navHostController,
        startDestination = Screens.CreatePage.route
    ) {
        composable(route = Screens.CreatePage.route){
            CreateScreen(values, viewModel)
        }
        composable(route = Screens.ReadPage.route){
            ReadScreen(values, viewModel)
        }
        composable(route = Screens.UpdatePage.route){
            UpdateScreen(values, viewModel)
        }
        composable(route = Screens.DeletePage.route){
            DeleteScreen(values)
        }
    }
}