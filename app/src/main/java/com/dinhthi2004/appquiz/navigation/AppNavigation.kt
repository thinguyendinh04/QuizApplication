package com.dinhthi2004.appquiz.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.dinhthi2004.appquiz.presentation.screens.DoingQuiz.QuizDoingScreen
import com.dinhthi2004.appquiz.presentation.screens.createQuiz.QuizCreationScreen
import com.dinhthi2004.appquiz.presentation.screens.MainScreen.QuizListScreen
import com.dinhthi2004.appquiz.presentation.screens.MainScreen.QuizViewModel


@Composable
fun AppNavigation(navController: NavHostController, viewModel: QuizViewModel) {
    NavHost(navController = navController, startDestination = "quizList") {
        composable("quizList") {
            QuizListScreen(viewModel = viewModel, navController = navController)
        }
        composable("createQuiz") {
            QuizCreationScreen(viewModel = viewModel, navController = navController)
        }
        composable("takeQuiz/{quizId}") { backStackEntry ->
            val quizId = backStackEntry.arguments?.getString("quizId")?.toLong() ?: 0
            QuizDoingScreen(viewModel = viewModel, quizId = quizId, navController = navController)
        }
    }
}
