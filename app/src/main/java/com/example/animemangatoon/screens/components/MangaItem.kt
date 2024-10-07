package com.example.animemangatoon.screens.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import com.example.animemangatoon.MainViewModel
import com.example.animemangatoon.R
import com.example.animemangatoon.data.models.submodels.MangaDTO
import com.example.animemangatoon.navigation.Screens
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun MangaItem(manga: MangaDTO, navController: NavHostController, modifier: Modifier = Modifier) {
    val painter = rememberAsyncImagePainter(
        model = manga.thumb,
        imageLoader = ImageLoader.Builder(LocalContext.current)
            .crossfade(true)
            .crossfade(1000)
            .error(R.drawable.ic_placeholder)
            .placeholder(R.drawable.ic_placeholder)
            .build()
    )

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                navController.navigate("${Screens.Chapter.routess}/${manga.id}/${manga.title}")
            },
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Manga Thumbnail with optimized size
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp) // Increased height for better visibility
                    .background(Color.Gray, RoundedCornerShape(8.dp)), // Background to optimize image display
                painter = painter,
                contentDescription = "Manga Thumbnail"
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = manga.title,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = manga.summary,
                fontSize = 14.sp,
                maxLines = 5,
                overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Additional Information
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                InfoChip(text = "Chapters: ${manga.total_chapter}")
                InfoChip(text = if (manga.nsfw) "NSFW" else "SFW")
                InfoChip(text = manga.type)
                InfoChip(text = manga.status)
            }
        }
    }
}

@Composable
fun InfoChip(text: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .padding(4.dp)
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(Color(0xFFE0E0E0), Color(0xFFF5F5F5)),
                    start = Offset(0f, 0f),
                    end = Offset(100f, 100f)
                ),
                shape = RoundedCornerShape(16.dp)
            )
            .shadow(4.dp, RoundedCornerShape(16.dp)) // Adds shadow for depth
            .padding(horizontal = 12.dp, vertical = 6.dp)
    ) {
        Text(
            text = text,
            fontSize = 14.sp, // Increased font size for better readability
            color = Color(0xFF424242), // Darker color for contrast
            style = MaterialTheme.typography.bodyMedium // Use Material typography styles
        )
    }
}
