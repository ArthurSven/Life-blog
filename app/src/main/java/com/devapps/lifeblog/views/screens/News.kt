package com.devapps.lifeblog.views.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.devapps.lifeblog.data.remote.models.Article
import com.devapps.lifeblog.ui.theme.Azure
import com.devapps.lifeblog.ui.theme.LifeblogTheme
import com.devapps.lifeblog.viewmodels.NewsViewModel

@Composable
fun NewsScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        val newsViewModel = viewModel(modelClass = NewsViewModel::class.java)
        val state by newsViewModel.state.collectAsState()
        Text(
            text = "Today's News",
            color = Azure,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(20.dp, 30.dp)
        )
        Spacer(
            modifier = Modifier
                .height(20.dp)
        )
        LazyColumn {
            if (state.isEmpty()) {
                item {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .fillMaxSize()
                            .wrapContentSize(align = Alignment.Center)
                    )
                }
            }

            items(state) {article: Article ->

            }
        }
    }
}

@Composable
fun NewsCard() {
    Card(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .height(80.dp)
            .background(Color.White)
    ) {
        Row {

        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LifeblogTheme {
    NewsCard()
    }
}