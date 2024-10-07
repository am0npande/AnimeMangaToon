package com.example.animemangatoon.data.models

import com.example.animemangatoon.data.models.submodels.MangaDTO

data class Main(
    val code: Int,
    val data: List<MangaDTO>
)