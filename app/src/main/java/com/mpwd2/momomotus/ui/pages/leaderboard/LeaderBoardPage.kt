package com.mpwd2.momomotus.ui.pages.leaderboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.mpwd2.momomotus.R
import com.mpwd2.momomotus.data.entities.State
import com.mpwd2.momomotus.ui.pages.home.HomeViewModel

var list = listOf("Ajay","Vijay","Prakash", "test");
@Composable
fun LeaderboardScreen() {
    var vmLeaderboard : LeaderBoardViewModel = hiltViewModel()

    var stateLeaderboard = vmLeaderboard.state.collectAsState().value

    if(stateLeaderboard is State.Success){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(id = R.color.purple_500))
                .wrapContentSize(Alignment.Center)
        )
        {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentSize(Alignment.Center)
            ){
                Text(
                    text = "Leaderboard",
                    fontSize = 28.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth()
            ){
                Box(
                    modifier = Modifier.weight(1f).wrapContentSize(Alignment.Center)
                ){
                }
                Box(
                    modifier = Modifier.weight(2f).wrapContentSize(Alignment.Center)

                ){
                    Text(text = "Name", color = Color.White)
                }
                Box(
                    modifier = Modifier.weight(2f).wrapContentSize(Alignment.Center)
                ){
                    Text(text = "Score", color = Color.White)
                }
            }
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(10.dp)
            ) {
                items(stateLeaderboard.data) { item->
                    LeaderboardRow(item.score.toString(),item.pseudo.toString(), item.image.toString())
                }
            }
        }
    }



}

@Composable
fun LeaderboardRow(score:String, name: String, image: String) {
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
            modifier = Modifier.padding(5.dp),
            horizontalArrangement  =  Arrangement.SpaceEvenly
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f),
                contentAlignment = Alignment.Center
            ){
                Row {
                    AsyncImage(
                        model = image,
                        modifier = Modifier.clip(CircleShape),
                        contentDescription = "profil",
                        contentScale = ContentScale.Fit,
                    )
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(2f),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = name,
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(2f),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = score,
                )
            }
        }
    }
}