package com.robosoft.tablayout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import com.google.accompanist.pager.*
import com.robosoft.tablayout.ui.theme.NewCanaryProjectTheme
import com.robosoft.tablayout.ui.theme.greenColor
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalPagerApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewCanaryProjectTheme {
                TabLayout()
            }
        }
    }
}


@OptIn(ExperimentalUnitApi::class)
@ExperimentalPagerApi
@Composable
@Preview

fun TabLayout() {

    val pagerState = rememberPagerState(pageCount = 3)
    Column(
        modifier = Modifier.background(Color.White)
    ) {
        TopAppBar(backgroundColor = greenColor) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Tab Layout",
                    style = TextStyle(color = Color.White),
                    fontWeight = FontWeight.Bold,
                    fontSize = TextUnit(
                        18F,
                        TextUnitType.Sp
                    ),
                    modifier = Modifier.padding(all = Dp(5F)),
                    textAlign = TextAlign.Center
                )
            }
        }
        Tabs(pagerState = pagerState)
        TabsContent(pagerState = pagerState)
    }
}

@ExperimentalPagerApi
@Composable
fun Tabs(pagerState: PagerState) {

    val list = listOf(
        "Home" to Icons.Default.Home,
        "Shopping" to Icons.Default.ShoppingCart,
        "Settings" to Icons.Default.Settings
    )
    val scope = rememberCoroutineScope()
    TabRow(
        selectedTabIndex = pagerState.currentPage,
        backgroundColor = greenColor,
        contentColor = Color.White,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier.pagerTabIndicatorOffset(pagerState, tabPositions),
                height = 2.dp,
                color = Color.White
            )
        }
    ) {
        list.forEachIndexed { index, _ ->
            Tab(
                icon = {
                    Icon(imageVector = list[index].second, contentDescription = null)
                },
                text = {
                    Text(
                        list[index].first,
                        color = if (pagerState.currentPage == index) Color.White else Color.LightGray
                    )
                },
                selected = pagerState.currentPage == index,
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                }
            )
        }
    }
}


@ExperimentalPagerApi
@Composable
fun TabsContent(pagerState: PagerState) {
    HorizontalPager(state = pagerState) {
            page ->
        when (page) {
            0 -> TabContentScreen(data = "Welcome to Home Screen")
            1 -> TabContentScreen(data = "Welcome to Shopping Screen")
            2 -> TabContentScreen(data = "Welcome to Settings Screen")
        }
    }
}


@Composable
fun TabContentScreen(data: String) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = data,
            style = MaterialTheme.typography.h5,
            color = greenColor,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
    }
}
