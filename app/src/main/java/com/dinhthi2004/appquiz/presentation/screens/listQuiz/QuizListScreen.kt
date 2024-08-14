package com.dinhthi2004.appquiz.presentation.screens.listQuiz

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import com.dinhthi2004.appquiz.presentation.screens.quiz.QuizViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuizListScreen(viewModel: QuizViewModel, navController: NavHostController) {
    val quizzes by viewModel.getAllQuizzes().observeAsState(emptyList())

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("D Quiz") },
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = Color(0xFF1976D2)
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate("createQuiz")
            }) {
                Icon(Icons.Default.Add, contentDescription = "Add Quiz")
            }
        }
    ) { padding ->
        Column(Modifier.padding(padding)) {
            LazyColumn {
                items(quizzes) { quiz ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                            .clickable { navController.navigate("takeQuiz/${quiz.quizId}") },
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFFE3F2FD)
                        ),
                        elevation = CardDefaults.cardElevation(8.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column {
                                Text(
                                    text = quiz.title,
                                    style = MaterialTheme.typography.bodyLarge,
                                    color = Color(0xFF1976D2)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}