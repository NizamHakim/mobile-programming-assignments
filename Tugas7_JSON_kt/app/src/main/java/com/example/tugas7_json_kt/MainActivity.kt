package com.example.tugas7_json_kt

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tugas7_json_kt.data.ContactsRepositoryImpl
import com.example.tugas7_json_kt.data.model.Contact
import com.example.tugas7_json_kt.presentation.ContactsViewModel
import com.example.tugas7_json_kt.ui.theme.Orange_kt
import com.example.tugas7_json_kt.ui.theme.Tugas7_JSON_ktTheme
import kotlinx.coroutines.flow.collectLatest

@Suppress("UNCHECKED_CAST")
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<ContactsViewModel>(factoryProducer = {
        object : ViewModelProvider.Factory{
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return ContactsViewModel(ContactsRepositoryImpl(RetrofitInstance.api))
                as T
            }
        }
    })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Tugas7_JSON_ktTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val contactList = viewModel.contacts.collectAsState().value
                    val context = LocalContext.current

                    LaunchedEffect(key1 = viewModel.showErrorToastChannel) {
                        viewModel.showErrorToastChannel.collectLatest {show ->
                            if (show){
                                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }

                    if(contactList.isEmpty()){
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }else{
                        LazyColumn(
                            modifier = Modifier.fillMaxSize(),
                            contentPadding = PaddingValues(16.dp)
                        ) {
                            items(contactList.size){index ->  
                                AddContact(contactList[index])
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun AddContact(contact: Contact) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 10.dp),
        colors = CardDefaults.cardColors(Orange_kt)
    ) {
        Column(
            modifier = Modifier.padding(15.dp)
        ) {
            Text(text = contact.id, fontSize = 16.sp)
            Text(text = contact.name, fontSize = 22.sp)
            Text(text = contact.email, fontSize = 18.sp)
            Text(text = contact.phone.mobile, fontSize = 18.sp)
        }
    }
}