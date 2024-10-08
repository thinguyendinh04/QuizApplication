package com.dinhthi2004.appquiz.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dinhthi2004.appquiz.data.model.Answer
import com.dinhthi2004.appquiz.data.model.Question
import com.dinhthi2004.appquiz.data.model.Quiz


@Dao
interface QuizDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuiz(quiz: Quiz): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuestion(question: Question)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAnswer(answer: Answer)

    @Query("SELECT * FROM quiz_table")
    fun getAllQuizzes(): LiveData<List<Quiz>>

    @Query("SELECT * FROM question_table WHERE quizId = :quizId")
    fun getQuestionsForQuiz(quizId: Long): LiveData<List<Question>>

    @Query("SELECT * FROM answer_table WHERE questionId = :questionId")
    fun getAnswersForQuestion(questionId: Long): LiveData<List<Answer>>
}