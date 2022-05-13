package com.sadikahmetozdemir.notezz.service.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.sadikahmetozdemir.notezz.data.local.dto.NotesDatabase

@Dao
interface NotesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(notesDatabase: NotesDatabase)

    @Update
    suspend fun update(note: NotesDatabase)

    @Query("SELECT * FROM notes WHERE data LIKE :data ORDER BY id DESC")
    fun search(data: String): LiveData<List<NotesDatabase>>

    @Query("select * from notes where folder_id=:folderId ORDER BY id DESC")
    suspend fun getNotesByFolder(folderId: Int): List<NotesDatabase>

    @Delete
    suspend fun delete(note: NotesDatabase)
}
