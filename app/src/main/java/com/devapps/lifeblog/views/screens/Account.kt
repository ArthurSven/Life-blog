package com.devapps.lifeblog.views.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devapps.lifeblog.ui.theme.Azure

@Composable
fun AccountScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        Text(
            text = "User Section",
            color = Azure,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(20.dp, 30.dp)
        )
        Spacer(modifier = Modifier.height(160.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Column() {
                Button(
                    onClick = { /*TODO*/ },
                    Modifier
                        .background(color = Azure)
                        .height(60.dp)
                        .width(200.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Azure)
                ) {
                    Text(text = "SIGNUP", color = Color.White, fontWeight = FontWeight.Bold)
                }
                Spacer(modifier = Modifier.height(20.dp))
                Button(
                    onClick = { /*TODO*/ },
                    Modifier
                        .background(color = Azure)
                        .height(60.dp)
                        .width(200.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Azure)
                ) {
                    Text(text = "LOGIN", color = Color.White, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}