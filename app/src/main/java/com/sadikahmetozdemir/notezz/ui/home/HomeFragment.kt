package com.sadikahmetozdemir.notezz.ui.home

import android.os.Bundle
import android.view.View
import com.sadikahmetozdemir.notezz.R
import com.sadikahmetozdemir.notezz.base.BaseFragment
import com.sadikahmetozdemir.notezz.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(R.layout.fragment_home) {

    var notesAdapter: NotesAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getNotes()
        notesAdapter = NotesAdapter()
        binding.recyclerView.apply {
            setHasFixedSize(true)
            adapter = notesAdapter
        }
        initObserve()

    }

    fun initObserve() {
        viewModel.notes.observe(viewLifecycleOwner) {
            notesAdapter?.setData(it)

        }
    }


}
