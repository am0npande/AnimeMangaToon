package com.example.animemangatoon.data.models

import com.example.animemangatoon.data.models.submodels.Mangas

data class ImagesModel(
    val code: Int,
    val data: List<Mangas>
)