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
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.mpwd2.momomotus.R
import com.mpwd2.momomotus.ui.pages.home.ProfileViewModel

@Composable
fun Profile() {
    var vmProfile : ProfileViewModel = hiltViewModel()

    var user =  vmProfile.getUser()

    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        user.pseudo?.let {
            Text(
                text = it,
                fontSize = 30.sp,
                modifier = Modifier.padding(bottom = 20.dp)
            )
        }
        Card(
            modifier = Modifier.size(120.dp),
            shape = CircleShape,
            elevation = 12.dp
        )
        {
            AsyncImage(
                model = user.image,
                modifier = Modifier.clip(CircleShape),
                contentDescription = "test",
                contentScale = ContentScale.Crop,
            )
        }

        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.Start) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                elevation = 12.dp
            ){
                user.pseudo?.let {
                    Text(
                        text = it,
                        fontSize = 30.sp,
                    )
                }
            }

            user.email?.let {
                Text(
                    text = it,
                    fontSize = 30.sp,
                    modifier = Modifier.padding(top = 20.dp)
                )
            }
            Text(
                text = "18/01/1999",
                fontSize = 30.sp,
                modifier = Modifier.padding(top = 20.dp)
            )
        }


    }


}