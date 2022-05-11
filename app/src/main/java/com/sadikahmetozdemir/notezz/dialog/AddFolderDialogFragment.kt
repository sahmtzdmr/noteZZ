package com.sadikahmetozdemir.notezz.dialog

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            btAdd.setOnClickListener {
                if (etFolderName.text?.trim().isNullOrEmpty()) {
                    Toast.makeText(
                        requireActivity(),
                        getString(R.string.folder_name_requested),
                        Toast.LENGTH_SHORT
                    ).show()
                } else
                    onAddClicked(etTextLayout.editText?.text.toString())
            }
            btCancel.setOnClickListener {
                dismiss()
            }
        }
    }

    private fun onAddClicked(folderName: String) {
        setFragmentResult("request_add", bundleOf("add" to folderName))
        findNavController().popBackStack()
    }
}
