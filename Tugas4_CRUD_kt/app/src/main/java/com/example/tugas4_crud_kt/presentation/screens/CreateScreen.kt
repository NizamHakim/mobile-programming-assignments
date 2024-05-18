package com.example.tugas4_crud_kt.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tugas4_crud_kt.presentation.theme.Blue_kt

@Composable
fun CreateScreen(values: PaddingValues) {
    var nrp by remember { mutableStateOf("") }
    var nama by remember { mutableStateOf("") }
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
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        OutlinedTextField(
            value = nama,
            onValueChange = {nama = it},
            label = { Text(text = "Nama")},
        )
        Button(
            onClick = { },
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