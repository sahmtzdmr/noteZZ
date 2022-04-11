package com.sadikahmetozdemir.notezz.ui.image

import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.sadikahmetozdemir.notezz.R
import com.sadikahmetozdemir.notezz.base.BaseFragment
import com.sadikahmetozdemir.notezz.databinding.FragmentImagePopUpBinding

class ImagePopUpFragment :
    BaseFragment<FragmentImagePopUpBinding, ImagePopUpViewModel>(R.layout.fragment_image_pop_up) {
    private var args: ImagePopUpFragmentArgs? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            args = ImagePopUpFragmentArgs.fromBundle(it)
            args?.notes.let {
                Glide
                    .with(requireContext())
                    .load(it?.image)
                    .placeholder(R.drawable.ic_baseline_image_24)
                    .into(binding.ivNote)
            }
        }
    }
}
