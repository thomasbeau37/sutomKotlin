package com.mpwd2.momomotus.ui.pages.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mpwd2.momomotus.R

@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.purple_500))
            .wrapContentSize(Alignment.Center)
    )
    {
        Text(text = "Home View",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 25.sp,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun ProfileScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.purple_500))
            .wrapContentSize(Alignment.Center)
    )
    {
        Text(text = "Profile View",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 25.sp,
            textAlign = TextAlign.Center
        )
    }
}

var list = listOf("Ajay","Vijay","Prakash", "test");

@Composable
fun LeaderboardScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.purple_500))
            .wrapContentSize(Alignment.Center)
    )
    {
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(10.dp)
        ) {
            items(list.size) { item ->
                LeaderboardRow("#1","name")
            }
        }
    }
}

@Composable
fun LeaderboardRow(rang:String, name: String) {
    Card(
        modifier = Modifier
            .height(100.dp)
            .padding(5.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        shape = MaterialTheme.shapes.medium,
        elevation = 5.dp,
        backgroundColor = MaterialTheme.colors.surface
    ) {
        Row(

        ) {
            Box(
                modifier = Modifier.fillMaxHeight(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = rang,
                )
            }
            Box(
                modifier = Modifier.fillMaxHeight(),
                contentAlignment = Alignment.Center
            ){
                Row {
                    Image(
                        modifier = Modifier.size(48.dp),
                        painter = painterResource(R.drawable.account),
                        contentDescription = "",
                        contentScale = ContentScale.Crop,
                    )
                }
            }
            Box(
                modifier = Modifier.fillMaxHeight(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = name,
                )
            }
        }
    }
}
