package com.dinhthi2004.appquiz.presentation.screens.DoingQuiz

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.dinhthi2004.appquiz.data.model.Answer
import com.dinhthi2004.appquiz.data.model.Question
import com.dinhthi2004.appquiz.presentation.screens.MainScreen.QuizViewModel


// Dummy data for testing
val dummyQuestions = listOf(
    Question(
        questionId = 1,
        quizId = 1,
        questionText = "What is the capital of France?",
        correctAnswer = "Paris"
    ),
    Question(
        questionId = 2,
        quizId = 1,
        questionText = "What is the largest planet in our solar system?",
        correctAnswer = "Jupiter"
    ),
    Question(
        questionId = 3,
        quizId = 1,
        questionText = "The shoes are comfortable and stylish?",
        correctAnswer = "Status"
    ),
    Question(
        questionId = 4,
        quizId = 1,
        questionText = "The book is a must-read for any Harry Potter?",
        correctAnswer = "Shipped"
    )
)

val dummyAnswers = listOf(
    Answer(questionId = 1, answerText = "Paris", isCorrect = true),
    Answer(questionId = 1, answerText = "London", isCorrect = false),
    Answer(questionId = 1, answerText = "Berlin", isCorrect = false),
    Answer(questionId = 1, answerText = "Madrid", isCorrect = false),
    Answer(questionId = 2, answerText = "Jupiter", isCorrect = true),
    Answer(questionId = 2, answerText = "Saturn", isCorrect = false),
    Answer(questionId = 2, answerText = "Earth", isCorrect = false),
    Answer(questionId = 2, answerText = "Mars", isCorrect = false),
    Answer(questionId = 3, answerText = "Status", isCorrect = true),
    Answer(questionId = 3, answerText = "Basic", isCorrect = false),
    Answer(questionId = 3, answerText = "Open", isCorrect = false),
    Answer(questionId = 3, answerText = "Close", isCorrect = false),
    Answer(questionId = 4, answerText = "Shipped", isCorrect = true),
    Answer(questionId = 4, answerText = "Static", isCorrect = false),
    Answer(questionId = 4, answerText = "North", isCorrect = false),
    Answer(questionId = 4, answerText = "Next", isCorrect = false)
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuizDoingScreen(viewModel: QuizViewModel, quizId: Long, navController: NavHostController) {
    var questions by remember { mutableStateOf(dummyQuestions) }
    var answers by remember { mutableStateOf(emptyList<Answer>()) }
    var currentQuestionIndex by remember { mutableStateOf(0) }
    var correctAnswers by remember { mutableStateOf(0) }
    var isQuizCompleted by remember { mutableStateOf(false) }

    LaunchedEffect(quizId) {

    }

    Column(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (isQuizCompleted) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFE3F2FD) // Light blue background
                ),
                elevation = CardDefaults.cardElevation(8.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Quiz Completed! Correct: $correctAnswers/${questions.size}")
                    Button(
                        onClick = { navController.popBackStack() },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp)
                            .background(
                                brush = Brush.horizontalGradient(
                                    colors = listOf(Color(0xFF1976D2), Color(0xFF42A5F5))
                                ),
                                shape = RoundedCornerShape(16.dp)
                            ),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent // Transparent to show gradient
                        )
                    ) {
                        Text("Back to Quiz List", color = Color.White)
                    }
                }
            }
        } else if (currentQuestionIndex < questions.size) {
            val question = questions[currentQuestionIndex]
            val answers = dummyAnswers.filter { it.questionId == question.questionId }
            val context = LocalContext.current

            Toast.makeText(context, "Question: ${question.questionText}", Toast.LENGTH_SHORT).show()

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFE3F2FD) // Light blue background
                ),
                elevation = CardDefaults.cardElevation(8.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(question.questionText, modifier = Modifier.padding(bottom = 16.dp))
                    answers.shuffled().take(4).forEach { answer ->
                        Button(
                            onClick = {
                                if (answer.isCorrect) {
                                    correctAnswers++
                                }

                                if (currentQuestionIndex + 1 < questions.size) {
                                    currentQuestionIndex++
                                } else {
                                    isQuizCompleted = true
                                }
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp)
                                .background(
                                    brush = Brush.horizontalGradient(
                                        colors = listOf(Color(0xFF1976D2), Color(0xFF42A5F5))
                                    ),
                                    shape = RoundedCornerShape(16.dp)
                                ),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.Transparent // Transparent to show gradient
                            )
                        ) {
                            Text(answer.answerText, color = Color.White)
                        }
                    }
                }
            }
        }
    }
}

