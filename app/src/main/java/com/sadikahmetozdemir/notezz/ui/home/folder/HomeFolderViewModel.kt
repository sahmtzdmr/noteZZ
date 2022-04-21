package com.sadikahmetozdemir.notezz.ui.home.folder

import com.sadikahmetozdemir.notezz.base.BaseViewModel

class HomeFolderViewModel : BaseViewModel() {

    fun addFolder() {
        navigate(HomeFolderFragmentDirections.actionHomeFolderFragmentToAddFolderDialogFragment())
    }
}