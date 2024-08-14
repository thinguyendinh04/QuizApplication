package com.dinhthi2004.appquiz.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dinhthi2004.appquiz.data.dao.QuizDao
import com.dinhthi2004.appquiz.data.model.Answer
import com.dinhthi2004.appquiz.data.model.Question
import com.dinhthi2004.appquiz.data.model.Quiz


@Database(entities = [Quiz::class, Question::class, Answer::class], version = 3)
abstract class QuizDatabase : RoomDatabase() {
    abstract fun quizDao(): QuizDao

    companion object {
        @Volatile
        private var INSTANCE: QuizDatabase? = null

        fun getDatabase(context: Context): QuizDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    QuizDatabase::class.java,
                    "quiz_database"
                )
                .fallbackToDestructiveMigration()
                .build()
                INSTANCE = instance
                instance
            }
        }
    }
}