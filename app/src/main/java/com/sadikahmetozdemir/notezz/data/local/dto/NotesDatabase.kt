package com.sadikahmetozdemir.notezz.data.local.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "notes")
data class NotesDatabase(
    var data: String?,
    var date: String?,
    var characters: Long
) : Serializable {

    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}
