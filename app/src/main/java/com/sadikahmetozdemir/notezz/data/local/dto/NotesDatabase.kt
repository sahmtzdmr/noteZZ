package com.sadikahmetozdemir.notezz.data.local.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(
    tableName = "notes",
    foreignKeys = [ForeignKey(
        entity = FolderDataBase::class,
        parentColumns = ["folder_id"],
        childColumns = ["id"],
        onDelete = CASCADE
    )]
)
data class NotesDatabase(
    var data: String?,
    var date: String?,
    var characters: Long?,
    var image: String? = null,
    @ColumnInfo(name = "folder_id")
    var folderId: Int
) : Serializable {

    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}
