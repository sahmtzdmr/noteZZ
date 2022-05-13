package com.sadikahmetozdemir.notezz.service.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sadikahmetozdemir.notezz.data.local.dto.FolderDataBase
import com.sadikahmetozdemir.notezz.data.local.dto.NotesDatabase

@Dao
interface FolderDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(folderDataBase: FolderDataBase)

    @Query("SELECT * FROM folder ORDER BY folder_id DESC")
    suspend fun getFolders(): List<FolderDataBase>

    @Delete
    suspend fun delete(folderDataBase: FolderDataBase)
}
