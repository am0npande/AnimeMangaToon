package com.example.animemangatoon.data.models.submodels

data class MangaDTO(

    val id: String,
    val summary: String,
    val thumb: String,
    val title: String,
    val total_chapter: Int,
    val nsfw: Boolean,
    val type: String,
    val status: String,
)
