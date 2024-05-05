package com.example.tugas4_crud_kt

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.example.tugas4_crud_kt.room.database.MhsDB
import com.example.tugas4_crud_kt.room.viewmodel.MhsViewModel
import com.example.tugas4_crud_kt.ui.theme.Tugas4_CRUD_ktTheme

@Suppress("UNCHECKED_CAST")
class MainActivity : ComponentActivity() {
    private val db by lazy {
        MhsDB.getDatabase(applicationContext)
    }
    private val viewModel by viewModels<MhsViewModel>(
        factoryProducer = {
            object : ViewModelProvider.Factory{
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return MhsViewModel(db.mhsDao) as T
                }
            }
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Tugas4_CRUD_ktTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainApp(viewModel)
                }
            }
        }
    }
}

@Composable
fun MainApp(viewModel: MhsViewModel) {
    val navHostController = rememberNavController()
    Scaffold(
        topBar = { AppBar()},
        bottomBar = { NavigationBar(navHostController = navHostController)}
    ) {values ->
        NavigationRoute(navHostController = navHostController, values = values, viewModel = viewModel)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .height(80.dp)
            .fillMaxWidth()
    ){
        Image(
            painter = painterResource(id = R.drawable.toolbar_background_kt),
            contentDescription = "toolbar_bg",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
        )
        TopAppBar(
            title = {
                Text(
                    text = "Tugas4_CRUD_kt",
                    fontWeight = FontWeight.Bold,
                )
            },
            colors = TopAppBarDefaults.topAppBarColors(Color.Transparent),
        )
    }
}