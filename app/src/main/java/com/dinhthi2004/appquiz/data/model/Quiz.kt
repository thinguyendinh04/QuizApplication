package com.dinhthi2004.appquiz.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "quiz_table")
data class Quiz(
    @PrimaryKey(autoGenerate = true) val quizId: Long = 0,
    val title: String
)