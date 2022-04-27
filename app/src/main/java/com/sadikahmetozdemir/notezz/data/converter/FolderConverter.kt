package com.sadikahmetozdemir.notezz.data.converter

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.sadikahmetozdemir.notezz.data.local.dto.FolderDataBase
import javax.inject.Inject

@ProvidedTypeConverter
class FolderConverter @Inject constructor() {

    @TypeConverter
    fun folderToJson(folderDataBase: FolderDataBase): String {

        return toJson<FolderDataBase>(folderDataBase)
    }

    @TypeConverter
    fun jsonToFolder(folderDataBase: String): FolderDataBase {
        return fromJson<FolderDataBase>(folderDataBase)
    }


}