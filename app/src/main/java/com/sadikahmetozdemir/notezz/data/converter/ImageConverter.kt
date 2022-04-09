package com.sadikahmetozdemir.notezz.data.converter

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import java.io.ByteArrayOutputStream

@ProvidedTypeConverter
class ImageConverter {
    @TypeConverter
    fun fromBitmap(bitmap: Bitmap): ByteArray {
        val outputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
        return outputStream.toByteArray()
    }

    @TypeConverter
    fun toBitmap(byteArray: ByteArray): Bitmap {
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
    }
}