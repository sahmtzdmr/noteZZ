package com.sadikahmetozdemir.notezz.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.sadikahmetozdemir.notezz.base.BaseViewModel
import com.sadikahmetozdemir.notezz.data.local.dto.NotesDatabase
import com.sadikahmetozdemir.notezz.data.repository.DefaultRepository
import com.sadikahmetozdemir.notezz.ui.details.NotesDetailViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val defaultRepository: DefaultRepository,
    var savedStateHandle: SavedStateHandle
) : BaseViewModel() {
    private val _notes: MutableLiveData<List<NotesDatabase>> = MutableLiveData()
    val notes: LiveData<List<NotesDatabase>> get() = _notes



    fun getNotes() {
        sendRequest(
            request = { defaultRepository.getNotes() },
            success = {
                viewModelScope.launch {
                    _notes.value = it
                    _notes.value
                }
            }
        )
    }

    fun goAddNote() {
        navigate(HomeFragmentDirections.actionHomeFragmentToAddNoteFragment())
    }

    fun goDetail(note:NotesDatabase) {
        navigate(HomeFragmentDirections.actionHomeFragmentToNotesDetailFragment(note))

    }
    companion object {
        val NOTES_ID = "noteID"
        val NOTES = "note"

    }


}