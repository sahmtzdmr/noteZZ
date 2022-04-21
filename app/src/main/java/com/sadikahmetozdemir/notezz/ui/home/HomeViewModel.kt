package com.sadikahmetozdemir.notezz.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.sadikahmetozdemir.notezz.base.BaseViewModel
import com.sadikahmetozdemir.notezz.data.local.dto.FolderDataBase
import com.sadikahmetozdemir.notezz.data.local.dto.NotesDatabase
import com.sadikahmetozdemir.notezz.data.repository.DefaultRepository
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
    var deletedNote: NotesDatabase? = null
    var searchResult: LiveData<List<NotesDatabase>> = MutableLiveData()


    fun getNotes() {
        sendRequest(
            request = { defaultRepository.getNotes() },
            success = {
                viewModelScope.launch {
                    _notes.value = it
                }
            }
        )
    }

    fun goAddNote() {
        navigate(HomeFragmentDirections.actionHomeFragmentToAddNoteFragment())
    }

    fun goDetail(note: NotesDatabase) {
        navigate(HomeFragmentDirections.actionHomeFragmentToNotesDetailFragment(note))
    }

    fun search(data: String) {
        sendRequest(request = { defaultRepository.search(data) }, success = {
            searchResult = it
        })
    }

    fun deleteNote() {
        sendRequest(request = {
            deletedNote?.let { defaultRepository.deleteNote(it) }
        }, success = {
        })
    }



    fun toDialog() {
        navigate(HomeFragmentDirections.actionHomeFragmentToNoteDialogFragment())
    }

    companion object {
        val NOTES_ID = "noteID"
        val NOTES = "note"
    }
}
