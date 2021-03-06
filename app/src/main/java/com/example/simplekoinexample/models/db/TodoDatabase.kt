package com.example.simplekoinexample.models.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.simplekoinexample.models.data.TodoData

@Database(entities = [TodoData::class], version = 2)
abstract class TodoDatabase: RoomDatabase() {

    abstract fun todoDao() : TodoDao

    companion object {
        private var instance: TodoDatabase ?= null

        fun getInstance(context: Context): TodoDatabase? {
            if(instance == null) {
                synchronized(TodoDatabase::class) {
                    instance = Room.databaseBuilder(context.applicationContext,
                        TodoDatabase::class.java, "todo_db")
                        .fallbackToDestructiveMigration() // 데이터베이스가 수정되면 기존 데이터베이스를 삭제한다.
                        .build()
                }
            }

            return instance
        }
    }
}