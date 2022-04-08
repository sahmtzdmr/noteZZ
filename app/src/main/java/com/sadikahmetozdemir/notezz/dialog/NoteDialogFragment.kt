package com.sadikahmetozdemir.notezz.dialog

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.sadikahmetozdemir.notezz.R
import com.sadikahmetozdemir.notezz.databinding.FragmentNoteDialogBinding


class NoteDialogFragment : BottomSheetDialogFragment() {
    private var _binding: FragmentNoteDialogBinding? = null
    val binding: FragmentNoteDialogBinding get() = _binding!!

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        BottomSheetDialog(requireContext(), R.style.TransparentBottomSheetDialog)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding =
            FragmentNoteDialogBinding.inflate(inflater,container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btDelete.setOnClickListener {
            onUnfollowClicked()
        }
        binding.btCancel.setOnClickListener {
            dismiss()
        }
    }

    private fun onUnfollowClicked() {
        setFragmentResult("request_delete", bundleOf("delete" to true))
        findNavController().popBackStack()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
