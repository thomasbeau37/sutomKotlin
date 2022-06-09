package com.mpwd2.momomotus.ui.pages.leaderboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
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
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(10.dp)
            ) {
                items(stateLeaderboard.data) { item->
                    LeaderboardRow(item.score.toString(),item.pseudo.toString())
                }
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