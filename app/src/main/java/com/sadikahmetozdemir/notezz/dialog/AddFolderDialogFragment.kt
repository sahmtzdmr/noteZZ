package com.sadikahmetozdemir.notezz.dialog

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.sadikahmetozdemir.notezz.R
import com.sadikahmetozdemir.notezz.databinding.FragmentAddFolderDialogBinding

class AddFolderDialogFragment : BottomSheetDialogFragment() {
    private var _binding: FragmentAddFolderDialogBinding? = null
    val binding: FragmentAddFolderDialogBinding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding =
            FragmentAddFolderDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        BottomSheetDialog(requireContext(), R.style.TransparentBottomSheetDialog)
}

