package com.sadikahmetozdemir.notezz.ui.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.sadikahmetozdemir.notezz.base.BaseViewModel
import com.sadikahmetozdemir.notezz.data.local.dto.NotesDatabase
import com.sadikahmetozdemir.notezz.data.repository.DefaultRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NotesDetailViewModel @Inject constructor(
    private val defaultRepository: DefaultRepository,
    private val savedStateHandle: SavedStateHandle
) :
    BaseViewModel() {
    val noteID = savedStateHandle.get<Int>(NOTES_ID) ?: 0
    val notes = savedStateHandle.get<NotesDatabase>(NOTES)
    val editableNote = MutableLiveData<String>(notes?.data)


    fun saveOnClick() {
        sendRequest(request = {
            notes?.let {
                defaultRepository.saveNote(it)
            }
        },
            success = { backTo() })
    }


    companion object {
        val NOTES_ID = "noteID"
        val NOTES = "note"

    }


}