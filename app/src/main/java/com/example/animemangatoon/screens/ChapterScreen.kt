package com.example.animemangatoon.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.animemangatoon.MainViewModel
import com.example.animemangatoon.data.models.submodels.Chapter
import com.example.animemangatoon.navigation.Screens

@Composable
fun ChapterScreen(
    viewModel: MainViewModel = hiltViewModel(),
    navController: NavHostController,
    mangaId: String,
    mangaTitle: String
) {
    val state by viewModel.chapterState.collectAsState()

    LaunchedEffect(mangaId) {
        viewModel.fetchMangaChapters(mangaId)
    }

    Column(modifier = Modifier
        .fillMaxSize().background(MaterialTheme.colorScheme.background)
        .systemBarsPadding()) {
        // Title bar for the manga
        Text(
            text = mangaTitle,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .padding(16.dp)
                .background(MaterialTheme.colorScheme.background, shape = RoundedCornerShape(8.dp))
                .padding(8.dp),
            color = MaterialTheme.colorScheme.onSurface // Ensuring text color contrasts
        )

        // Loading indicator or chapters list
        if (state.isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
            ) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.background)
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(state.chapters.size) { index ->
                    if (state.chapters.isNotEmpty()) {
                        val chapter = state.chapters[index]
                        ChapterItemBox(chapter = chapter, navController = navController)
                    } else {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text("Chapter List is Empty", style = MaterialTheme.typography.bodyMedium)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ChapterItemBox(navController: NavHostController, chapter: Chapter) {
    // Elevated card with chapter title and additional styling
    ElevatedCard(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = {
                navController.navigate("${Screens.Mangas.routess}/${chapter.id}")
            })
            .padding(8.dp), // Padding around the card
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp)
    ) {
        Text(
            text = chapter.title,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier
                .padding(16.dp)
                .background(MaterialTheme.colorScheme.surface.copy(alpha = 0.1f), shape = RoundedCornerShape(8.dp)) // Optional background for title
        )
    }
}
