package com.sadikahmetozdemir.notezz.ui.home.folder

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.setFragmentResultListener
import com.sadikahmetozdemir.notezz.R
import com.sadikahmetozdemir.notezz.base.BaseFragment
import com.sadikahmetozdemir.notezz.data.local.dto.FolderDataBase
import com.sadikahmetozdemir.notezz.databinding.FragmentHomeFolderBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFolderFragment :
    BaseFragment<FragmentHomeFolderBinding, HomeFolderViewModel>(R.layout.fragment_home_folder) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        renderHome()
    }

    fun renderHome() {
        binding.apply {
            setFragmentResultListener("request_add") { _, bundle ->
                val folderName = bundle.getString("add")
                if (bundle.getString("add")?.isNotEmpty() == true) {
                    folderName?.let { FolderDataBase(it) }?.let { viewModel.addFolder(it) }
                    Log.d("tanım", "renderHome: $folderName ")
                }
            }
        }
    }

}







