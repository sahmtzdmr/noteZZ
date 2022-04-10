package com.sadikahmetozdemir.notezz.ui.addnote

import android.graphics.Bitmap
import androidx.lifecycle.MutableLiveData
import com.sadikahmetozdemir.notezz.base.BaseViewModel
import com.sadikahmetozdemir.notezz.data.local.dto.NotesDatabase
import com.sadikahmetozdemir.notezz.data.repository.DefaultRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.*
import javax.inject.Inject

@HiltViewModel
class AddNoteViewModel @Inject constructor(private val defaultRepository: DefaultRepository) :
    BaseViewModel() {
    private val notes = MutableLiveData<NotesDatabase?>()
    val noteData = MutableLiveData("")
    var note: String? = null
    var noteDate = Date()
    private var getCharacter: Long? = null
    var image: Bitmap? = null

    private fun saveData() {
        note = noteData.value.toString()
        noteDate = Calendar.getInstance().time
        getCharacter = noteData.value?.trim()?.length?.toLong()!!

    }

    fun addNote() {

        if (noteData.value.isNullOrEmpty()) {
            showToast(ADD_TEXT)
        } else {
            saveData()
            sendRequest(request =
            {
                defaultRepository.addNote(
                    NotesDatabase(
                        note,
                        noteDate.toString(),
                        getCharacter!!,
                        null
                    )
                )
            }, success = {
                showMessage(SUCCESS_ADD)
                backTo()
            }, error = {
                it
            })
        }
    }

    fun checkImage() {
        if (image == null){
            image
        }
    }


    companion object {
        val ADD_TEXT = "Bir not giriniz."
        val SUCCESS_ADD = "Not başarıyla eklendi."
    }


}