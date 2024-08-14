package com.dinhthi2004.appquiz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.dinhthi2004.appquiz.data.database.QuizDatabase
import com.dinhthi2004.appquiz.navigation.AppNavigation
import com.dinhthi2004.appquiz.presentation.screens.MainScreen.QuizViewModel
import com.dinhthi2004.appquiz.presentation.screens.MainScreen.QuizViewModelFactory
import com.dinhthi2004.appquiz.ui.theme.AppQuizTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            // Get an instance of QuizDatabase
            val database = QuizDatabase.getDatabase(applicationContext)

            // Create an instance of QuizViewModel using the factory
            val quizViewModel = ViewModelProvider(this, QuizViewModelFactory(database))
                .get(QuizViewModel::class.java)

            // Create a NavHostController
            val navController = rememberNavController()

            setContent {
                AppQuizTheme {
                    AppNavigation(navController, quizViewModel)
                }
            }
        }
    }
}
