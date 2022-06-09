package com.example.momomotus.ui.ui.pages.profile


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mpwd2.momomotus.R

@Composable
fun Profile() {
    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "Le pouler",
            fontSize = 30.sp,
            modifier = Modifier.padding(bottom = 20.dp)
            )
        Card(
            modifier = Modifier.size(120.dp),
            shape = CircleShape,
            elevation = 12.dp
        )
        {
            Image(
                modifier = Modifier.clip(CircleShape),
                painter = painterResource(id = R.drawable.account),
                contentDescription = "test",
                contentScale = ContentScale.Crop,
            )
        }

        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.Start) {
            Card(
                modifier = Modifier.fillMaxWidth().padding(top = 20.dp),
                elevation = 12.dp
            ){
                Text(
                    text = "Le Pouler",
                    fontSize = 30.sp,
                )
            }

            Text(
                text = "Pseudo",
                fontSize = 30.sp,
                modifier = Modifier.padding(top = 20.dp)
            )
            Text(
                text = "18/01/1999",
                fontSize = 30.sp,
                modifier = Modifier.padding(top = 20.dp)
            )
        }


    }


}