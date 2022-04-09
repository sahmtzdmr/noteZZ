package com.sadikahmetozdemir.notezz.di

import android.content.Context
import androidx.room.Room
import com.sadikahmetozdemir.notezz.data.converter.ImageConverter
import com.sadikahmetozdemir.notezz.data.local.database.AppDatabase
import com.sadikahmetozdemir.notezz.service.dao.NotesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    @Singleton
    fun provideNotesDao(appDatabase: AppDatabase): NotesDao {
        return appDatabase.notesDao()
    }

    @Provides
    @Singleton
    fun provideImageConverter(): ImageConverter {
        return ImageConverter()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(
        @ApplicationContext context: Context,
        imageConverter: ImageConverter
    ): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java, "notes_test"
        ).fallbackToDestructiveMigration()
            .addTypeConverter(imageConverter)
            .build()
    }
}