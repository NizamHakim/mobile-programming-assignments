package com.example.tugas4_crud_kt

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
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
    object CreatePage : Screens(
        route = "create",
        title = "Create",
        icon = Icons.Outlined.AddCircle
    )
    object ReadPage : Screens(
        route = "read",
        title = "Read",
        icon = Icons.Default.Person
    )
    object UpdatePage : Screens(
        route = "update",
        title = "Update",
        icon = Icons.Default.Refresh
    )
    object DeletePage : Screens(
        route = "delete",
        title = "Delete",
        icon = Icons.Default.Delete
    )
}