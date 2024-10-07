package com.example.animemangatoon.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.animemangatoon.data.models.submodels.Chapter
import com.example.animemangatoon.data.models.submodels.MangaDTO
import com.example.animemangatoon.data.models.submodels.Mangas
import com.example.animemangatoon.data.pagination.MyPagingSource
import com.example.animemangatoon.data.remote.NetworkApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Repository @Inject constructor(
    private val api: NetworkApi
) {
    fun fetchingMangaList(): Flow<PagingData<MangaDTO>> {
        return Pager(
            config = PagingConfig(
                pageSize = 5,
                prefetchDistance = 2,
                enablePlaceholders = false,
                initialLoadSize = 10,
            ),
            pagingSourceFactory = { MyPagingSource(api) }
        ).flow
    }


    suspend fun fetchMangaChapters(mangaId: String): List<Chapter> {return api.fetchChapters(mangaId).data}
    suspend fun fetchMangaImages(chapterId: String): List<Mangas> {return api.fetchMangaImages(chapterId).data}
}