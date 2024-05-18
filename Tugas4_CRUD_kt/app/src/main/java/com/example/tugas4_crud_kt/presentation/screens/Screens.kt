package com.example.tugas4_crud_kt.presentation.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screens(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    data object CreatePage : Screens(
        route = "create",
        title = "Create",
        icon = Icons.Outlined.AddCircle
    )
    data object ReadPage : Screens(
        route = "read",
        title = "Read",
        icon = Icons.Default.Person
    )
    data object UpdatePage : Screens(
        route = "update",
        title = "Update",
        icon = Icons.Default.Refresh
    )
    data object DeletePage : Screens(
        route = "delete",
        title = "Delete",
        icon = Icons.Default.Delete
    )
}