package com.example.tugas4_crud_kt.presentation.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tugas4_crud_kt.presentation.theme.Pink_kt

@Composable
fun ReadScreen(values: PaddingValues) {
    var nrp by remember { mutableStateOf("") }
    var nrpFound by remember { mutableStateOf("") }
    var namaFound by remember { mutableStateOf("") }
    var found by remember { mutableIntStateOf(0) }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize().padding(values)
    ) {
        Text(
            text = "Find Entry by NRP",
            color = Pink_kt,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier.padding(vertical = 15.dp)
        )
        OutlinedTextField(
            value = nrp,
            onValueChange = {nrp = it},
            label = { Text(text = "NRP") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Button(
            onClick = { },
            colors = ButtonDefaults.buttonColors(Pink_kt),
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
        if (found == 1){
            Text(
                text = "Result:",
                fontSize = 20.sp,
                modifier = Modifier.padding(top = 15.dp)
            )
            Row(
                modifier = Modifier.padding(horizontal = 40.dp, vertical = 20.dp)
            ) {
                Column(
                    modifier = Modifier.weight(.3f)
                ) {
                    Text(
                        text = "NRP:",
                        Modifier
                            .border(1.dp, Color.Black)
                            .padding(8.dp)
                            .fillMaxWidth()
                    )
                    Text(
                        text = "Nama:",
                        Modifier
                            .border(1.dp, Color.Black)
                            .padding(8.dp)
                            .fillMaxWidth()
                    )
                }
                Column(
                    modifier = Modifier.weight(.7f)
                ) {
                    Text(
                        text = nrpFound,
                        Modifier
                            .border(1.dp, Color.Black)
                            .padding(8.dp)
                            .fillMaxWidth()
                    )
                    Text(
                        text = namaFound,
                        Modifier
                            .border(1.dp, Color.Black)
                            .padding(8.dp)
                            .fillMaxWidth()
                    )
                }
            }
        }else if (found == -1){
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
        }
    }
}