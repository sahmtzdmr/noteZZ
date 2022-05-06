package com.sadikahmetozdemir.notezz.service.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.sadikahmetozdemir.notezz.data.local.dto.NotesDatabase

@Dao
interface NotesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(notesDatabase: NotesDatabase)

    @Query("SELECT * FROM notes ORDER BY id DESC")
    suspend fun getCardsData(): List<NotesDatabase>

    @Update
    suspend fun update(note: NotesDatabase)

    @Query("SELECT * FROM notes WHERE data LIKE :data")
    fun search(data: String): LiveData<List<NotesDatabase>>

    @Query("select * from notes where folder_id=:folderId")
    suspend fun getNotesByFolder(folderId: Int): List<NotesDatabase>

    @Delete
    suspend fun delete(note: NotesDatabase)
}
