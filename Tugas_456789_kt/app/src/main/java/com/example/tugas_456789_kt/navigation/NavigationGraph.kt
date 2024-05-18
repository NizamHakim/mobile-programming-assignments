package com.example.tugas_456789_kt.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.tugas_456789_kt.presentation.allmhs.AllMhsScreen
import com.example.tugas_456789_kt.presentation.createmhs.CreateScreen
import com.example.tugas_456789_kt.presentation.deletemhs.DeleteScreen
import com.example.tugas_456789_kt.presentation.readmhs.ReadScreen
import com.example.tugas_456789_kt.presentation.updatescreen.UpdateScreen

@Composable
fun NavigationGraph( navHostController: NavHostController, paddingValues: PaddingValues, snackbarHostState: SnackbarHostState ) {
    NavHost(navController = navHostController, startDestination = Screens.Create.route) {
        composable(route = Screens.Create.route){
            CreateScreen(paddingValues = paddingValues, snackbarHostState = snackbarHostState)
        }
        composable(route = Screens.Read.route){
            ReadScreen(paddingValues = paddingValues, snackbarHostState = snackbarHostState)
        }
        composable(route = Screens.AllMhs.route){
            AllMhsScreen(paddingValues = paddingValues, snackbarHostState = snackbarHostState)
        }
        composable(route = Screens.Update.route){
            UpdateScreen(paddingValues = paddingValues, snackbarHostState = snackbarHostState)
        }
        composable(route = Screens.Delete.route){
            DeleteScreen(paddingValues = paddingValues, snackbarHostState = snackbarHostState)
        }
    }
}