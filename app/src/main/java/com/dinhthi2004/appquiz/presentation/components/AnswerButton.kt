package com.dinhthi2004.appquiz.presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun AnswerButton(answerText: String, onClick: () -> Unit) {
    Button(onClick = onClick, Modifier.padding(vertical = 4.dp)) {
        Text(answerText)
    }
}

@Preview
@Composable
fun AnswerButtonPreview() {
    AnswerButton("Option A") {}
}