package com.sadikahmetozdemir.notezz.data.local.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "folder")
data class FolderDataBase(
    var folder: String,

    ) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "folder_id")
    var folderId: Int? = null
}