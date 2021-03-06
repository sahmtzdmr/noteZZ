package com.sadikahmetozdemir.notezz.data.repository

import androidx.lifecycle.LiveData
import com.sadikahmetozdemir.notezz.data.local.dto.FolderDataBase
import com.sadikahmetozdemir.notezz.data.local.dto.NotesDatabase
import com.sadikahmetozdemir.notezz.service.dao.FolderDao
import com.sadikahmetozdemir.notezz.service.dao.NotesDao
import javax.inject.Inject

class DefaultRepository @Inject constructor(
    private val notesDao: NotesDao,
    private val folderDao: FolderDao
) : BaseRepository() {


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

    suspend fun deleteNote(notesDatabase: NotesDatabase) {
        return execute {
            notesDao.delete(notesDatabase)
        }
    }

    fun search(data: String): LiveData<List<NotesDatabase>> {
        return notesDao.search(data)
    }

    suspend fun addFolder(folderDataBase: FolderDataBase) {
        return execute {
            folderDao.insert(folderDataBase)
        }
    }

    suspend fun getNotesByFolder(folderId: Int): List<NotesDatabase> {
        return execute {
            folderId
            notesDao.getNotesByFolder(folderId)
        }
    }

    suspend fun getFolder(): List<FolderDataBase> {
        return execute { folderDao.getFolders() }
    }

    suspend fun deleteFolder(folderDataBase: FolderDataBase) {
        return execute {
            folderDao.delete(folderDataBase)
        }
    }
}
