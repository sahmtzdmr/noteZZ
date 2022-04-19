package com.sadikahmetozdemir.notezz.data.local.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "folder")
data class FolderDataBase(
    var folder: String
): Serializable {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}