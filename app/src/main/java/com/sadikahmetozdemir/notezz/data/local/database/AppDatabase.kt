package com.sadikahmetozdemir.notezz.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sadikahmetozdemir.notezz.data.local.dto.NotesDatabase
import com.sadikahmetozdemir.notezz.service.dao.NotesDao

@Database(
    entities = [
        NotesDatabase::class
    ],
    version = 7

)
abstract class AppDatabase : RoomDatabase() {
    abstract fun notesDao(): NotesDao
}
