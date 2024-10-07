package com.example.animemangatoon.screens.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.animemangatoon.MainViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun ListContent(navcontroller: NavHostController, viewModel: MainViewModel = hiltViewModel(), modifier: Modifier = Modifier) {
    val pagingData = viewModel.pagingData.collectAsLazyPagingItems()
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = false)

    SwipeRefresh(
        state = swipeRefreshState,
        onRefresh = {
            pagingData.refresh()
            Thread.sleep(1000)
            swipeRefreshState.isRefreshing = true
        }
    ) {
        LazyColumn(modifier = modifier) {

            if (pagingData.loadState.refresh is LoadState.Loading) {
                item {
                    Box(modifier = Modifier.fillParentMaxSize()) {
                        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                    }
                }
            }
            if (pagingData.loadState.refresh is LoadState.NotLoading) {
                items(count = pagingData.itemCount) { index ->
                    swipeRefreshState.isRefreshing = false
                    pagingData[index]?.let {
                        if(it.total_chapter != 0) MangaItem(navController = navcontroller, manga = it)
                    }
                }
            }
            if(pagingData.loadState.refresh is LoadState.Error){
                item {
                    Box(modifier = Modifier.fillParentMaxSize(), contentAlignment = Alignment.Center) {
                        Text( text = "Error! Click to Retry",modifier = Modifier.clickable { pagingData.refresh() })
                    }
                }

            }


            if(pagingData.loadState.append is LoadState.Loading){
                item {
                    Box(modifier = Modifier.fillMaxWidth().padding(12.dp)) {
                        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                    }
                }
            }
            if(pagingData.loadState.append is LoadState.Error){
                item {
                    ErrorFooter(pagingData::retry)
                }
            }

            if(pagingData.loadState.prepend is LoadState.Loading){
                item {
                    Box(modifier = Modifier.fillMaxWidth().padding(12.dp)) {
                        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                    }
                }
            }
            if(pagingData.loadState.prepend is LoadState.Error){
                item {
                    ErrorHeader(pagingData::retry)
                }
            }
        }
    }



}

@Composable
fun ErrorHeader(retry: () -> Unit = {}) {
    Box(Modifier.fillMaxWidth().padding(12.dp)) {
        Text(
            "Tap to Retry",
            modifier = Modifier.clickable { retry.invoke()}
                .align(Alignment.Center),
            style = MaterialTheme.typography.labelSmall
        )

    }
}



@Composable
fun ErrorFooter(retry: () -> Unit = {}){
    Box(Modifier.fillMaxWidth().padding(12.dp)) {
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(imageVector = Icons.Default.Warning, contentDescription = "Error Icon")
            Text(
                "Error Occurred",
                style = MaterialTheme.typography.labelSmall,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
            Box(modifier = Modifier.fillMaxWidth()){
                Text(
                    text = "Retry",
                    style = MaterialTheme.typography.labelSmall,
                    modifier = Modifier.align(Alignment.CenterEnd).clickable { retry.invoke() },
                )
            }

        }
    }
}