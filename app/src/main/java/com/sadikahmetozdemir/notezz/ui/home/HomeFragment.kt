package com.sadikahmetozdemir.notezz.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.setFragmentResultListener
import com.sadikahmetozdemir.notezz.R
import com.sadikahmetozdemir.notezz.base.BaseFragment
import com.sadikahmetozdemir.notezz.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment :
    BaseFragment<FragmentHomeBinding, HomeViewModel>(R.layout.fragment_home),
    SearchView.OnQueryTextListener {

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

    override fun onPause() {
        super.onPause()
        binding.searchView.setOnQueryTextListener(null)
    }

    override fun onResume() {
        super.onResume()
        binding.searchView.setOnQueryTextListener(this)
    }

    private fun getItemsFromDB(data: String) {
        var searchText = data
        searchText = "%$data%"
        viewModel.search(searchText)
        viewModel.searchResult.observe(this) {
            notesAdapter.setData(ArrayList(it))
        }
    }

    fun initObserve() {
        viewModel.notes.observe(viewLifecycleOwner) {
            notesAdapter.setData(ArrayList(it))
        }
    }
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

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (query != null) {
            getItemsFromDB(query)
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if (newText != null && newText.isNotEmpty()) {
            getItemsFromDB(newText)
        }
        return true
    }
}
