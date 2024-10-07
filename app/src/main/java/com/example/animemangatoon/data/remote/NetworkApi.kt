package com.example.animemangatoon.data.remote

import com.example.animemangatoon.data.models.ChapterModel
import com.example.animemangatoon.data.models.ImagesModel
import com.example.animemangatoon.data.models.Main
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkApi {

    @GET("manga/fetch")
    suspend fun fetchManga(
        @Query("page")page:Int
    ): Main

    @GET("manga/chapter")
    suspend fun fetchChapters(
        @Query("id") mangaId: String
    ): ChapterModel

    @GET("manga/image")
    suspend fun fetchMangaImages(
        @Query("id")chapterId:String
    ): ImagesModel
}