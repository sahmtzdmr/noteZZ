package com.sadikahmetozdemir.notezz.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.sadikahmetozdemir.notezz.data.converter.ImageConverter
import com.sadikahmetozdemir.notezz.data.local.dto.NotesDatabase
import com.sadikahmetozdemir.notezz.service.dao.NotesDao

@Database(
    entities = [
        NotesDatabase::class
    ],
    version = 6

)
@TypeConverters(ImageConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun notesDao(): NotesDao
}