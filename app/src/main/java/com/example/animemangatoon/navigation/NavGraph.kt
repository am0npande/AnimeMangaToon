package com.example.animemangatoon.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.animemangatoon.screens.ChapterScreen
import com.example.animemangatoon.screens.HomeScreen
import com.example.animemangatoon.screens.MangaScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screens.Home.routess
    ) {
        composable(route = Screens.Home.routess) {
            HomeScreen(navController = navController)
        }
        composable(route =  Screens.Chapter.routess + "/{id}/{title}",
            arguments = listOf(
                navArgument("id") { type = NavType.StringType },
                navArgument("title") { type = NavType.StringType }
            )) {
            val mangaID = it.arguments?.getString("id") ?: ""
            val mangaTitle = it.arguments?.getString("title") ?: ""
            ChapterScreen(mangaTitle = mangaTitle, mangaId = mangaID, navController = navController)
        }
        composable(route = Screens.Mangas.routess + "/{chapterId}") {
            val chapterID = it.arguments?.getString("chapterId") ?: ""
            MangaScreen(chapterId = chapterID)
        }
    }
}