package com.example.animemangatoon.screens.states

import com.example.animemangatoon.data.models.submodels.Mangas

data class MangaImagesState(
    val images: List<Mangas> = emptyList(),
    val isLoading: Boolean = false,
    val isFetched: Boolean = false
)