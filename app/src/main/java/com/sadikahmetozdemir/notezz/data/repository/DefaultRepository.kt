package com.sadikahmetozdemir.notezz.data.repository

import com.sadikahmetozdemir.notezz.data.local.dto.NotesDatabase
import com.sadikahmetozdemir.notezz.service.dao.NotesDao
import javax.inject.Inject

class DefaultRepository @Inject constructor(
    private val notesDao: NotesDao
) : BaseRepository() {

    suspend fun getNotes(): List<NotesDatabase> {
        return execute {
            notesDao.getCardsData()
        }
    }

    suspend fun addNote(notesDatabase: NotesDatabase) {
        return execute {
            notesDao.insert(notesDatabase)
        }
    }

    suspend fun saveNote(notesDatabase: NotesDatabase) {
        return execute {
            notesDao.update(notesDatabase)
        }
    }

}