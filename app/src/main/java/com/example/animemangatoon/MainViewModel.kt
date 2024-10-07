package com.example.animemangatoon

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.animemangatoon.data.repository.Repository
import com.example.animemangatoon.screens.states.ChapterState
import com.example.animemangatoon.screens.states.MangaImagesState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private var repository: Repository
): ViewModel() {

    var pagingData = repository.fetchingMangaList().cachedIn(viewModelScope)

    // Use the new MangaScreenState to encapsulate the state
    private val _chapterState = MutableStateFlow(ChapterState())
    val chapterState = _chapterState.asStateFlow()

    // New state for manga images
    private val _mangaImagesState = MutableStateFlow(MangaImagesState())
    val mangaImagesState = _mangaImagesState.asStateFlow()

    // Fetch manga chapters and update state
    suspend fun fetchMangaChapters(mangaId: String) {
        _chapterState.value = _chapterState.value.copy(isLoading = true, isFetched = false)

        val chapters = repository.fetchMangaChapters(mangaId).reversed()

        _chapterState.value = _chapterState.value.copy(
            chapters = chapters,
            isLoading = false,
            isFetched = true
        )
    }

    // Fetch manga images and update state
    fun fetchMangaImages(chapterId: String) {
        viewModelScope.launch {
            _mangaImagesState.value = _mangaImagesState.value.copy(isLoading = true, isFetched = false)

            val images = repository.fetchMangaImages(chapterId)

            _mangaImagesState.value = _mangaImagesState.value.copy(
                images = images,
                isLoading = false,
                isFetched = true
            )
        }
    }
}

