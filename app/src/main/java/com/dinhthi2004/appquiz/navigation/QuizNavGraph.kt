package com.dinhthi2004.appquiz.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dinhthi2004.appquiz.presentation.screens.QuizTaking.QuizTakingScreen
import com.dinhthi2004.appquiz.presentation.screens.createQuiz.QuizCreationScreen
import com.dinhthi2004.appquiz.presentation.screens.listQuiz.QuizListScreen
import com.dinhthi2004.appquiz.presentation.screens.quiz.QuizViewModel


@Composable
fun QuizNavGraph(navController: NavHostController, viewModel: QuizViewModel) {
    NavHost(navController = navController, startDestination = "quizList") {
        composable("quizList") {
            QuizListScreen(viewModel = viewModel, navController = navController)
        }
        composable("createQuiz") {
            QuizCreationScreen(viewModel = viewModel, navController = navController)
        }
        composable("takeQuiz/{quizId}") { backStackEntry ->
            val quizId = backStackEntry.arguments?.getString("quizId")?.toLong() ?: 0
            QuizTakingScreen(viewModel = viewModel, quizId = quizId, navController = navController)
        }
    }
}
