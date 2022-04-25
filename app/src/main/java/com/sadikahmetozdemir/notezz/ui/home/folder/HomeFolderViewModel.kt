package com.sadikahmetozdemir.notezz.ui.home.folder

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sadikahmetozdemir.notezz.base.BaseViewModel
import com.sadikahmetozdemir.notezz.data.local.dto.FolderDataBase
import com.sadikahmetozdemir.notezz.data.repository.DefaultRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeFolderViewModel @Inject constructor(private val defaultRepository: DefaultRepository) :
    BaseViewModel() {
    private val _folder: MutableLiveData<List<FolderDataBase>> = MutableLiveData()
    val folder: LiveData<List<FolderDataBase>> get() = _folder
    var folderTitle: String? = null

    fun goToFolder() {
        navigate(HomeFolderFragmentDirections.actionHomeFolderFragmentToAddFolderDialogFragment())
    }

    fun addFolder(folderDataBase: FolderDataBase) {
        sendRequest(request = {
            defaultRepository.addFolder(folderDataBase)
        },
            error = {
                it
            })
    }

    fun fetchFolder() {
        sendRequest(request = { defaultRepository.getFolder() }, success = {
            viewModelScope.launch {
                _folder.value = it
            }
        })
    }
}