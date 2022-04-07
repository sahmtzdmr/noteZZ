package com.sadikahmetozdemir.notezz.ui.addnote

import android.os.Bundle
import android.view.View
import com.sadikahmetozdemir.notezz.R
import com.sadikahmetozdemir.notezz.base.BaseFragment
import com.sadikahmetozdemir.notezz.databinding.FragmentAddNoteBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AddNoteFragment :
    BaseFragment<FragmentAddNoteBinding, AddNoteViewModel>(R.layout.fragment_add_note) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btAdd.setOnClickListener {
            viewModel.addNote()
        }
    }

}