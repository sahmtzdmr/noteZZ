package com.sadikahmetozdemir.notezz.ui.details

import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.sadikahmetozdemir.notezz.R
import com.sadikahmetozdemir.notezz.base.BaseFragment
import com.sadikahmetozdemir.notezz.databinding.FragmentNotesDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.Date
import java.util.Calendar

@AndroidEntryPoint
class NotesDetailFragment :
    BaseFragment<FragmentNotesDetailBinding, NotesDetailViewModel>(R.layout.fragment_notes_detail) {
    private lateinit var date: Date

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fetchDate()
        val imageString = viewModel.notes?.image
        Glide
            .with(requireContext())
            .load(imageString)
            .placeholder(R.drawable.ic_baseline_image_24)
            .into(binding.ivImage)
    }

    private fun fetchDate() {
        date = Calendar.getInstance().time
        binding.currentDate.text = date.toString()
    }
}
