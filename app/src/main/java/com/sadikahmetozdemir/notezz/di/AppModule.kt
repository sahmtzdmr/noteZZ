package com.sadikahmetozdemir.notezz.di

import com.sadikahmetozdemir.notezz.data.repository.DefaultRepository
import com.sadikahmetozdemir.notezz.service.dao.FolderDao
import com.sadikahmetozdemir.notezz.service.dao.NotesDao
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    fun provideDefaultRepository(
        notesDao: NotesDao,
        folderDao: FolderDao
    ): DefaultRepository {
        return DefaultRepository(notesDao, folderDao)
    }
}
