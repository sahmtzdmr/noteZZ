package com.sadikahmetozdemir.notezz.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.setFragmentResultListener
import com.sadikahmetozdemir.notezz.R
import com.sadikahmetozdemir.notezz.base.BaseFragment
import com.sadikahmetozdemir.notezz.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(R.layout.fragment_home) {

    private lateinit var notesAdapter: NotesAdapter

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getNotes()
        notesAdapter = NotesAdapter()
        binding.recyclerView.apply {
            setHasFixedSize(true)
            adapter = notesAdapter
        }
        notesAdapter.itemClicked = {
            viewModel.goDetail(it)
        }
        notesAdapter.DeleteItemClicked = {
            viewModel.toDialog()
            viewModel.deletedNote = it
        }
        initObserve()
        renderHome()

    }


    fun initObserve() {
        viewModel.notes.observe(viewLifecycleOwner) {
            notesAdapter.setData(it)

        }
    }


    @SuppressLint("NotifyDataSetChanged")
    fun renderHome() {
        binding.apply {
            setFragmentResultListener("request_delete") { _, bundle ->
                if (bundle.getBoolean("delete", false)) {
                    viewModel.deleteNote()
                    viewModel.getNotes()


                }
            }


        }

    }


}
