package com.example.animemangatoon.screens

import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.animemangatoon.MainViewModel
import com.example.animemangatoon.R
import com.example.animemangatoon.data.models.submodels.Mangas

@Composable
fun MangaScreen(
    viewModel: MainViewModel = hiltViewModel(),
    chapterId: String,
) {
    val state by viewModel.mangaImagesState.collectAsState()

    LaunchedEffect(chapterId) {
        viewModel.fetchMangaImages(chapterId)
    }

    if (state.isLoading) {
        // Show a loading indicator
        Box(modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    } else {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 8.dp)
                .systemBarsPadding(), // slight padding on the sides
            verticalArrangement = Arrangement.spacedBy(12.dp) // space between each image
        ) {
            items(state.images.size) { index ->
                val mangaList = state.images
                MangaItemImage(mangaList[index])
            }
        }
    }
}


@Composable
fun MangaItemWebView(manga: Mangas) {
    // Using AndroidView to create a WebView for the manga image
    AndroidView(
        factory = { context ->
            WebView(context).apply {
                webViewClient = WebViewClient() // Ensures links open in the WebView
                settings.javaScriptEnabled = true // Enable JavaScript if needed
                settings.cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK // Use cache if available
            }
        },
        update = { webView ->
            // Create HTML content with a style to fit the image
            val htmlContent = """
                <!DOCTYPE html>
                <html>
                <head>
                    <style>
                        body {
                            margin: 0;
                            padding: 0;
                            display: flex;
                            justify-content: center;
                            align-items: center;
                        }
                        img {
                            max-width: 100%;
                            height: auto; // Maintain aspect ratio
                            display: block; // Ensure no extra space around the image
                        }
                    </style>
                </head>
                <body>
                    <img src="${manga.link}" alt="Manga Image" />
                </body>
                </html>
            """.trimIndent()

            // Load the HTML content instead of a direct URL
            // Use loadDataWithBaseURL to enable caching
            webView.loadDataWithBaseURL(null, htmlContent, "text/html", "UTF-8", null)
        },
        modifier = Modifier
            .fillMaxWidth() // Makes the image span the entire width
            .height(500.dp) // Adjust height as needed
    )
}

@Composable
fun MangaItemImage(manga: Mangas) {
    // Use AsyncImage to load the image from the URL directly
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(manga.link)
            .crossfade(true)
            .crossfade(200)
            .placeholder(R.drawable.ic_placeholder)
            .placeholder(R.drawable.ic_placeholder)
            .build(),
        contentDescription = "Manga Image",
        modifier = Modifier.fillMaxSize().fillMaxHeight()
            .fillMaxWidth() // Makes the image span the entire width
            .padding(bottom = 1.dp) // Optional bottom padding
    )
}
