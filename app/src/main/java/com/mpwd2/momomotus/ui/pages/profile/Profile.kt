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
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
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
        Text(
            text = "Profil",
            fontSize = 30.sp,
            modifier = Modifier.padding(bottom = 20.dp)
        )
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

        Column(modifier = Modifier.fillMaxWidth().padding(5.dp), horizontalAlignment = Alignment.Start) {
            user.pseudo?.let {
                Text(
                    text = "Name:",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = it,
                    fontSize = 18.sp,
                )
            }
            user.email?.let {
                Text(
                    modifier = Modifier.padding(top = 20.dp),
                    text = "Email:",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = it,
                    fontSize = 18.sp,
                )
            }
            Text(
                modifier = Modifier.padding(top = 20.dp),
                text = "Birthdate:",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "18/01/1999",
                fontSize = 18.sp,
            )
        }


    }


}