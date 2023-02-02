package com.robosoft.weatherapp.ui.favourite

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.robosoft.weatherapp.R


@Composable

fun Favourite(navController: NavController) {
    Box {
        Image(
            modifier = Modifier
                .fillMaxSize(),
            painter = painterResource(id = R.drawable.background_android),
            contentScale = ContentScale.FillBounds,
            contentDescription = "Background"
        )
        FavouriteContent(navController)
    }
}


@Composable
fun FavouriteContent(navController: NavController) {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (toolbar) = createRefs()
        val (content) = createRefs()

        Box(modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .constrainAs(toolbar) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { navController.navigate("Home_Screen") }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.Black
                    )
                }
                Text(
                    text = "Favourite", textAlign = TextAlign.Center,
                    modifier = Modifier.weight(1f), fontSize = 20.sp, color = Color.Black
                )
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "search",
                        tint = Color.Black
                    )
                }
            }
        }
        Box(modifier = Modifier
            .fillMaxWidth()
            .constrainAs(content) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(toolbar.bottom)
            }) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 22.dp)
            ) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "7 City added as favourite",
                        fontSize = 13.sp,
                        color = Color.White,
                        modifier = Modifier
                            .padding(start = 16.dp, top = 15.dp)
                            .weight(1f)
                    )
                    Text(
                        text = "Remove All",
                        fontSize = 13.sp,
                        color = Color.White,
                        modifier = Modifier.padding(end = 16.dp, top = 15.dp)
                    )
                }

                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(5.dp),
                    modifier = Modifier.padding(
                        top = 23.dp,
                        start = 16.dp,
                        end = 16.dp,
                        bottom = 23.dp
                    )
                ) {
                    items(count = 7) {
                        ItemView()
                    }
                }
            }
        }
    }
}

@Composable
fun ItemView() {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .background(color = Color.Gray.copy(alpha = 0.3f))
    ) {
        val (place) = createRefs()
        val (icon) = createRefs()
        val (temperature) = createRefs()
        val (degree) = createRefs()
        val (description) = createRefs()
        val (like) = createRefs()

        Text(
            text = "Udupi, Karnataka",
            fontSize = 15.sp,
            color = Color.Yellow,
            modifier = Modifier
                .constrainAs(place) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                }
                .padding(start = 15.dp, top = 15.dp)
        )

        Image(
            painter = painterResource(id = R.drawable.ic_baseline_wb_sunny_24),
            contentDescription = "weather",
            modifier = Modifier
                .constrainAs(icon) {
                    start.linkTo(parent.start)
                    top.linkTo(place.bottom)
                }
                .padding(start = 15.dp, top = 10.dp)
        )

        Text(text = "31", fontSize = 18.sp, color = Color.White, modifier = Modifier
            .constrainAs(temperature) {
                start.linkTo(icon.end)
                top.linkTo(place.bottom)
            }
            .padding(start = 9.dp, top = 11.dp)
        )

        Text(text = "℃", fontSize = 12.sp, color = Color.White, modifier = Modifier
            .constrainAs(degree) {
                start.linkTo(temperature.end)
                top.linkTo(place.bottom)
            }
            .padding(start = 1.dp, top = 16.dp)
        )

        Text(text = "Mostly Sunny", fontSize = 14.sp, color = Color.White, modifier = Modifier
            .constrainAs(description) {
                start.linkTo(degree.end)
                top.linkTo(place.bottom)
            }
            .padding(start = 17.dp, top = 14.dp))

        Image(
            painter = painterResource(id = R.drawable.icon_favourite_active),
            contentDescription = "favourite icon", modifier = Modifier
                .constrainAs(like) {
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                }
                .padding(end = 20.dp, top = 32.dp))
    }
}
