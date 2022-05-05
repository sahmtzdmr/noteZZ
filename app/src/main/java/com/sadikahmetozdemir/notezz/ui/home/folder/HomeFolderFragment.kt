package com.sadikahmetozdemir.notezz.ui.home.folder

import android.os.Bundle
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
    private lateinit var foldersAdapter: FoldersAdapter
    private var folderList = ArrayList<FolderDataBase>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchFolder()
        foldersAdapter = FoldersAdapter()
//        folderList=ArrayList()
        binding.recyclerView.apply {
            setHasFixedSize(true)
            adapter = foldersAdapter
        }
        foldersAdapter.itemClicked = {
            it.id?.let { it1 ->
                viewModel.folder.value?.get(it1-1).let { it1 ->
                    it1?.let { it2 ->
                        viewModel.toNotes(
                            it2
                        )
                    }
                }
            }
        }

        initObserve()
        renderHome()


    }

    fun initObserve() {
        viewModel.folder.observe(viewLifecycleOwner) {
            foldersAdapter.setData(ArrayList(it))
            folderList = ArrayList(it)

        }
    }

    fun renderHome() {
        binding.apply {
            setFragmentResultListener("request_add") { _, bundle ->
                val folderName = bundle.getString("add")
                if (bundle.getString("add")?.isNotEmpty() == true) {
                    folderName?.let { it1 ->
                        FolderDataBase(it1)
                    }?.let {
                        viewModel.addFolder(it)
                        folderList.add(0, it)
                    }
                    foldersAdapter.setData(folderList)
//                    viewModel.fetchFolder()
                }
            }
        }
    }

}








