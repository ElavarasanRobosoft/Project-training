package com.robosoft.weatherapp.ui.home


import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.robosoft.weatherapp.R

@Preview
@Composable
fun HomeContent() {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (data) = createRefs()
        val (bottomSheet) = createRefs()

        Column(verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(data) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    height = Dimension.fillToConstraints
                }) {
            Text(
                text = "Wed, 28 Nov 2018    11:35 AM",
                fontSize = 13.sp,
                modifier = Modifier
                    .padding(top = 54.dp),
                color = White.copy(alpha = 0.6f),
            )
            Text(
                text = "Udupi, Karnataka",
                fontSize = 18.sp,
                modifier = Modifier
                    .padding(top = 10.dp),
                color = White
            )
            Row(
                modifier = Modifier
                    .padding(top = 23.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.icon_favourite),
                    contentDescription = "favourite icon"
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(text = "Add to favourite", fontSize = 13.sp, color = White)
            }
            Spacer(modifier = Modifier.height(81.dp))
            Image(
                painter = painterResource(id = R.drawable.ic_baseline_wb_sunny_24),
                contentDescription = "sunny icon",
                modifier = Modifier.size(100.dp)
            )
            Row(
                modifier = Modifier
                    .padding(top = 23.dp)
            ) {
                Text(text = "31", fontSize = 32.sp, color = White)
                Spacer(modifier = Modifier.width(1.dp))
                Text(text = "\u2103", fontSize = 32.sp, color = White)
            }
            Text(
                text = "Mostly Sunny",
                fontSize = 18.sp,
                color = White,
                modifier = Modifier.padding(11.dp)
            )
        }
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .constrainAs(bottomSheet) {
                start.linkTo(parent.start)
                bottom.linkTo(parent.bottom)
            }) {
            Surface(
                color = Gray.copy(alpha = 0.1f),
                modifier = Modifier.fillMaxSize()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .horizontalScroll(rememberScrollState()),
                    verticalAlignment = Alignment.CenterVertically,

                ) {
                    Row(modifier = Modifier.padding(start = 18.dp)) {
                        Image(
                            painter = painterResource(id = R.drawable.icon_temperature_info),
                            contentDescription = "Temperature info"
                        )
                        Column(modifier = Modifier.padding(start = 18.dp)) {
                            Text(
                                text = "Min - Max",
                                fontSize = 13.sp,
                                color = White,
                            )
                            Text(
                                text = "22 ℃ - 34 ℃",
                                fontSize = 18.sp,
                                color = White,
                            )
                        }
                    }
                    Row(modifier = Modifier.padding(start = 33.dp)) {
                        Image(
                            painter = painterResource(id = R.drawable.icon_precipitation_info),
                            contentDescription = "Temperature info"
                        )
                        Column(modifier = Modifier.padding(start = 18.dp)) {
                            Text(
                                text = "Precipitation",
                                fontSize = 13.sp,
                                color = White,
                            )
                            Text(
                                text = "0%",
                                fontSize = 18.sp,
                                color = White,
                            )
                        }
                    }
                    Row(modifier = Modifier.padding(start = 32.dp, end = 18.dp)) {
                        Image(
                            painter = painterResource(id = R.drawable.icon_humidity_info),
                            contentDescription = "Temperature info"
                        )
                        Column(modifier = Modifier.padding(start = 18.dp)) {
                            Text(
                                text = "Humidity",
                                fontSize = 13.sp,
                                color = White,
                            )
                            Text(
                                text = "47%",
                                fontSize = 18.sp,
                                color = White,
                            )
                        }
                    }
                }
            }
        }
    }
}