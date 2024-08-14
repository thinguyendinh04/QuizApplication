package com.dinhthi2004.appquiz.presentation.screens.listQuiz

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.dinhthi2004.appquiz.R
import com.dinhthi2004.appquiz.presentation.screens.quiz.QuizViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuizListScreen(viewModel: QuizViewModel, navController: NavHostController) {
    val quizzes by viewModel.getAllQuizzes().observeAsState(emptyList())

    Column(Modifier.padding()) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF5F5F5))
                .padding(16.dp)
        ) {
            Header()
            Spacer(modifier = Modifier.height(20.dp))
            QuizOptions(navController = navController)
            Spacer(modifier = Modifier.height(20.dp))
            QuizCategories()
            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "List Quiz Created",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 10.dp)
            )

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


@Composable
fun QuizOptions(
    navController: NavHostController
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        OptionCard(
            "Create Quiz",
            R.drawable.baseline_add_24,
            backgroundColor = Color(0xFF45A249), navController

        )
        OptionCard(
            "Single",
            R.drawable.baseline_person_24,
            backgroundColor = Color(0xFF03A9F4), navController

        )
        OptionCard(
            "Multiplayer", R.drawable.baseline_supervisor_account_24, backgroundColor = Color(
                0xEDE95E32
            ), navController
        )
    }
}

@Composable
fun OptionCard(
    title: String,
    iconRes: Int,
    backgroundColor: Color,
    navController: NavHostController
) {
    Card(
        modifier = Modifier
            .height(150.dp)
            .width(100.dp)
            .clickable {
                navController.navigate("createQuiz")
            },
        colors = CardDefaults.cardColors(containerColor = backgroundColor), // Sử dụng màu nền đã được truyền vào
        elevation = CardDefaults.cardElevation(8.dp),

        ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            Image(
                painter = painterResource(id = iconRes),
                contentDescription = title,
                modifier = Modifier.size(40.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = title,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center,
                color = Color.White
            )
        }
    }
}


@Composable
fun QuizCategories() {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Quiz Categories",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
            TextButton(onClick = { /* TODO */ }) {
                Text(text = "See all", fontSize = 14.sp, color = Color.Blue)
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            CategoryCard("Science", R.drawable.baseline_science_24)
            CategoryCard("History", R.drawable.baseline_history_edu_24)
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            CategoryCard("Sport", R.drawable.baseline_sports_soccer_24)
            CategoryCard("Art", R.drawable.baseline_article_24)
        }
    }
}


@Composable
fun CategoryCard(title: String, iconRes: Int) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .height(50.dp)
            .width(150.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier
                .fillMaxSize()
                .height(100.dp)
        ) {
            Image(
                painter = painterResource(id = iconRes),
                contentDescription = title,
                modifier = Modifier
                    .size(40.dp)
                    .align(Alignment.CenterVertically)
                    .padding(start = 8.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                modifier = Modifier.align(Alignment.CenterVertically),
                text = title,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center,
            )
        }
    }
}


@Composable
fun Header() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.quiz),
            contentDescription = "Profile Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = "Hi, Thi Nguyen", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.weight(1f))
        Card(
            modifier = Modifier.width(70.dp),
        ) {
            Row {
                Text(text = "2400", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.width(4.dp))
                Image(
                    painter = painterResource(id = R.drawable.baseline_currency_bitcoin_24),
                    contentDescription = "Coin Icon",
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}
