package com.sadikahmetozdemir.notezz.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sadikahmetozdemir.notezz.data.local.dto.FolderDataBase
import com.sadikahmetozdemir.notezz.data.local.dto.NotesDatabase
import com.sadikahmetozdemir.notezz.service.dao.FolderDao
import com.sadikahmetozdemir.notezz.service.dao.NotesDao

@Database(
    entities = [
        NotesDatabase::class,
        FolderDataBase::class
    ],
    version = 8

)
abstract class AppDatabase : RoomDatabase() {
    abstract fun notesDao(): NotesDao
    abstract fun folderDao(): FolderDao
}
