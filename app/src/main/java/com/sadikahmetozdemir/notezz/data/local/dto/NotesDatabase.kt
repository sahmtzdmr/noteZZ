package com.sadikahmetozdemir.notezz.data.local.dto

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.File
import java.io.Serializable

@Entity(tableName = "notes")
data class NotesDatabase(
    var data: String?,
    var date: String?,
    var characters: Long,
    var image: Bitmap? = null,
) : Serializable {

    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}
