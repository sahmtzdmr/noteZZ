package com.sadikahmetozdemir.notezz.data.local.dto

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@Entity(tableName = "folder")
@Parcelize
data class FolderDataBase(
    var folder: String,

    ) : Parcelable {
    @IgnoredOnParcel
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "folder_id")
    var folderId: Int? = null
}