package com.example.animemangatoon.screens.states

import com.example.animemangatoon.data.models.submodels.Chapter

data class ChapterState(
    val chapters: List<Chapter> = emptyList(),
    val isLoading: Boolean = false,
    val isFetched: Boolean = false,
)
