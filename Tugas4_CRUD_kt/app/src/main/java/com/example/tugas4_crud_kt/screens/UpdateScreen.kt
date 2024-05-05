package com.example.tugas4_crud_kt.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewModelScope
import com.example.tugas4_crud_kt.room.viewmodel.MhsViewModel
import com.example.tugas4_crud_kt.ui.theme.Orange_kt
import kotlinx.coroutines.launch

@Composable
fun UpdateScreen(values: PaddingValues, viewModel: MhsViewModel) {
    var oldNrp by remember { mutableStateOf("") }
    var newNrp by remember { mutableStateOf("") }
    var newNama by remember { mutableStateOf("") }
    val context = LocalContext.current
    val focusManager = LocalFocusManager.current
    var found by remember { mutableIntStateOf(0) }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(values)
    ) {
        Text(
            text = "Update Entry by NRP",
            color = Orange_kt,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier.padding(vertical = 15.dp)
        )
        OutlinedTextField(
            value = oldNrp,
            onValueChange = {oldNrp = it},
            label = { Text(text = "NRP") },
        )
        Button(
            onClick = {
                if (oldNrp == ""){
                    Toast.makeText(context, "Fields may not be blank", Toast.LENGTH_SHORT).show()
                }else{
                    viewModel.viewModelScope.launch{
                        viewModel.getMhs(oldNrp).collect{
                            if (it == null){
                                found = -1
                            }else{
                                newNrp = it.nrp
                                newNama = it.nama
                                found = 1
                                focusManager.clearFocus()
                            }
                        }
                    }
                }
            },
            colors = ButtonDefaults.buttonColors(Orange_kt),
            shape = RoundedCornerShape(7.dp),
            modifier = Modifier
                .width(280.dp)
                .padding(top = 15.dp)
        ) {
            Text(
                text = "Find",
                fontSize = 18.sp
            )
        }
        if (found == -1){
            Text(
                text = "Result:",
                fontSize = 20.sp,
                modifier = Modifier.padding(top = 15.dp)
            )
            Text(
                text = "Entry Not Found",
                fontSize = 18.sp,
                fontStyle = FontStyle.Italic,
                modifier = Modifier.padding(top = 10.dp),
                color = Color.Gray
            )
        }else if (found == 1){
            Dialog(onDismissRequest = { found = 0 }) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    shape = RoundedCornerShape(16.dp),
                ) {
                    Column(
                        modifier = Modifier
                            .wrapContentHeight()
                            .padding(30.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Text(
                            text = "UPDATE",
                            color = Orange_kt,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            modifier = Modifier.padding(vertical = 15.dp)
                        )
                        OutlinedTextField(
                            value = newNrp,
                            onValueChange = {newNrp = it},
                            label = { Text(text = "NRP")},
                        )
                        OutlinedTextField(
                            value = newNama,
                            onValueChange = {newNama = it},
                            label = { Text(text = "Nama")},
                            modifier = Modifier.padding(top = 7.dp)
                        )
                        Button(
                            onClick = {
                                if (newNrp == "" || newNama == ""){
                                    Toast.makeText(context, "Fields may not be blank", Toast.LENGTH_SHORT).show()
                                }else{
                                    viewModel.viewModelScope.launch{
                                        val status = viewModel.updateMhs(oldNrp, newNrp, newNama)
                                        if (status == -1){
                                            Toast.makeText(context, "Duplicate NRP is not allowed", Toast.LENGTH_SHORT).show()
                                        }else if (status >= 0){
                                            Toast.makeText(context, "Updated", Toast.LENGTH_SHORT).show()
                                            focusManager.clearFocus()
                                            newNrp = ""
                                            newNama = ""
                                            found = 0
                                        }
                                    }
                                }
                            },
                            colors = ButtonDefaults.buttonColors(Orange_kt),
                            shape = RoundedCornerShape(7.dp),
                            modifier = Modifier
                                .width(280.dp)
                                .padding(top = 15.dp)
                        ) {
                            Text(
                                text = "Update",
                                fontSize = 18.sp)
                        }
                        OutlinedButton(
                            onClick = { found = 0 },
                            shape = RoundedCornerShape(7.dp),
                            border = BorderStroke(1.dp, Color.Gray),
                            modifier = Modifier
                                .width(280.dp)
                        ) {
                            Text(
                                text = "Cancel",
                                fontSize = 18.sp,
                                color = Color.Gray
                            )
                        }
                    }
                }
            }
        }
    }
}