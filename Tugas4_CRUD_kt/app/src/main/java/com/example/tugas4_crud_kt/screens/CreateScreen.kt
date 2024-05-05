package com.example.tugas4_crud_kt.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewModelScope
import com.example.tugas4_crud_kt.room.entity.Mhs
import com.example.tugas4_crud_kt.room.viewmodel.MhsViewModel
import com.example.tugas4_crud_kt.ui.theme.Blue_kt
import kotlinx.coroutines.launch

@Composable
fun CreateScreen(values: PaddingValues, viewModel: MhsViewModel) {
    var nrp by remember { mutableStateOf("") }
    var nama by remember { mutableStateOf("") }
    val context = LocalContext.current
    val focusManager = LocalFocusManager.current
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize().padding(values)
    ) {
        Text(
            text = "Add New Entry",
            color = Blue_kt,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier.padding(vertical = 15.dp)
        )
        OutlinedTextField(
            value = nrp,
            onValueChange = {nrp = it},
            label = { Text(text = "NRP")},
        )
        OutlinedTextField(
            value = nama,
            onValueChange = {nama = it},
            label = { Text(text = "Nama")},
        )
        Button(
            onClick = {
                if (nrp == "" || nama == ""){
                    Toast.makeText(context, "Fields may not be blank", Toast.LENGTH_SHORT).show()
                }else{
                    val mhs = Mhs(nrp, nama)
                    viewModel.viewModelScope.launch{
                        val status = viewModel.insertMhs(mhs)
                        if (status > 0){
                            Toast.makeText(context, "Inserted \"$nrp - $nama\"", Toast.LENGTH_SHORT).show()
                            nrp = ""
                            nama = ""
                            focusManager.clearFocus()
                        }else{
                            Toast.makeText(context, "Duplicate NRP is not allowed", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            },
            colors = ButtonDefaults.buttonColors(Blue_kt),
            shape = RoundedCornerShape(7.dp),
            modifier = Modifier.width(280.dp).padding(top = 15.dp)
        ) {
            Text(
                text = "Add",
                fontSize = 18.sp)
        }
    }
}