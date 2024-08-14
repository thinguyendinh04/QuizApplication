package com.dinhthi2004.appquiz.presentation.screens.createQuiz

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.dinhthi2004.appquiz.data.model.Answer
import com.dinhthi2004.appquiz.data.model.Question
import com.dinhthi2004.appquiz.data.model.Quiz
import com.dinhthi2004.appquiz.presentation.screens.MainScreen.QuizViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuizCreationScreen(viewModel: QuizViewModel, navController: NavHostController) {
    var quizTitle by remember { mutableStateOf("") }
    var questionText by remember { mutableStateOf("") }
    var correctAnswer by remember { mutableStateOf("") }
    var incorrectAnswers by remember { mutableStateOf(listOf("", "", "")) }
    val questionList = remember { mutableStateListOf<Question>() }
    val answerList = remember { mutableStateListOf<Answer>() }
    var answerText by remember { mutableStateOf("") }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .align(Alignment.TopCenter)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Create your own quiz",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier
                        .padding(10.dp)
                        .align(Alignment.CenterVertically),
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Quiz Title", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = quizTitle,
                onValueChange = { quizTitle = it },
                placeholder = { Text("i.e. Science") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = "Quiz Question", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = questionText,
                onValueChange = { questionText = it },
                placeholder = { Text("i.e. Which Pokémon are you?") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Quiz Answers", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = answerText,
                onValueChange = { answerText = it },
                label = { Text("Answer 1") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    containerColor = Color(0xFFFFFFFF),
                    focusedLabelColor = Color(0xFF000000),
                    unfocusedLabelColor = Color(0xFF000000)
                )
            )

            incorrectAnswers.forEachIndexed { index, answer ->
                OutlinedTextField(
                    value = answer,
                    onValueChange = { newAnswer ->
                        incorrectAnswers =
                            incorrectAnswers
                                .toMutableList()
                                .apply { this[index] = newAnswer }
                    },
                    label = { Text("Answer ${index + 2}") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        containerColor = Color(0xFFFFFFFF),
                        focusedLabelColor = Color(0xFF000000),
                        unfocusedLabelColor = Color(0xFF000000)
                    )
                )
            }

            OutlinedTextField(
                value = correctAnswer,
                onValueChange = { correctAnswer = it },
                label = { Text("Correct Answer") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    containerColor = Color(0xFFFFFFFF),
                    focusedLabelColor = Color(0xFF000000),
                    unfocusedLabelColor = Color(0xFF000000)
                )
            )

            Button(
                onClick = {
                    val question = Question(
                        quizId = 0,
                        questionText = questionText,
                        correctAnswer = correctAnswer
                    )
                    questionList.add(question)

                    incorrectAnswers.forEach { answerText ->
                        val isCorrect = answerText == correctAnswer
                        val answer = Answer(
                            questionId = 0,
                            answerText = answerText,
                            isCorrect = isCorrect
                        )
                        answerList.add(answer)
                    }

                    questionText = ""
                    correctAnswer = ""
                    incorrectAnswers = listOf("", "", "")
                },
                modifier = Modifier.align(Alignment.CenterHorizontally),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFF5722)
                ),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 4.dp,
                    pressedElevation = 8.dp
                )
            ) {
                Text("Next Question")
            }

            Button(
                onClick = {
                    val quiz = Quiz(title = quizTitle)
                    viewModel.insertQuiz(quiz) { quizId ->
                        questionList.forEach { question ->
                            val updatedQuestion = question.copy(quizId = quizId)
                            viewModel.insertQuestion(updatedQuestion)
                            answerList
                                .filter { it.answerText == question.correctAnswer }
                                .forEach { answer ->
                                    viewModel.insertAnswer(answer.copy(questionId = updatedQuestion.questionId))
                                }
                            answerList
                                .filter { it.answerText != question.correctAnswer }
                                .forEach { answer ->
                                    viewModel.insertAnswer(answer.copy(questionId = updatedQuestion.questionId))
                                }
                        }
                        navController.popBackStack()
                    }
                },
                modifier = Modifier.align(Alignment.CenterHorizontally),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFF5722)
                ),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 4.dp,
                    pressedElevation = 8.dp
                )
            ) {
                Text("Save a Quiz")
            }
        }
    }
}

