package com.sadikahmetozdemir.notezz.data.local.dto

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.util.*

@Entity(
    tableName = "notes",
    foreignKeys = [
        ForeignKey(
            entity = FolderDataBase::class,
            parentColumns = ["folder_id"],
            childColumns = ["folder_id"],
            onDelete = CASCADE
        )
    ]
)
@Parcelize
data class NotesDatabase(
    var data: String?,
    var date: Date?,
    var characters: Long?,
    var image: String? = null,
    @ColumnInfo(name = "folder_id")
    var folderId: Int
) : Parcelable {

    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}
