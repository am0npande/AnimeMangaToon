package com.example.animemangatoon.navigation

sealed class Screens(val routess : String) {
    object Home:Screens("home")
    object Chapter:Screens("chapter")
    object Mangas:Screens("mangas")
}