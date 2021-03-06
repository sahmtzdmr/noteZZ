package com.sadikahmetozdemir.notezz.ui.addnote

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import com.sadikahmetozdemir.notezz.R
import com.sadikahmetozdemir.notezz.base.BaseFragment
import com.sadikahmetozdemir.notezz.databinding.FragmentAddNoteBinding
import com.sadikahmetozdemir.notezz.utils.toDateString
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class AddNoteFragment :
    BaseFragment<FragmentAddNoteBinding, AddNoteViewModel>(R.layout.fragment_add_note) {
    private val rqSpeechRec = RQ_SPEECH_REC
    var selectedBitmap: Bitmap? = null
    private lateinit var activityResultLauncher: ActivityResultLauncher<Intent>
    private lateinit var permissionLauncher: ActivityResultLauncher<String>
    private var folderId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        folderId = arguments?.getInt("folderID") ?: -1
        viewModel.folderId = folderId
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            btAdd.setOnClickListener {
                viewModel.addNote()
            }
            ivImage.setOnClickListener {
                selectImage()
            }
            fabVoice.setOnClickListener {
                askSpeechInput()
            }
            currentDate.text = Calendar.getInstance().time.toDateString()
        }
        registerLauncher()
    }

    fun selectImage() {
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    requireActivity(),
                    Manifest.permission.READ_EXTERNAL_STORAGE
                )
            ) {
                view?.let {
                    Snackbar.make(it, getString(R.string.permission), Snackbar.LENGTH_INDEFINITE)
                        .setAction(
                            getString(R.string.give_permission),
                            {
                                permissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
                            }
                        ).show()
                }
            } else {
                permissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
            }
        } else {
            val intentToGallery =
                Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            activityResultLauncher.launch(intentToGallery)
        }
    }

    private fun registerLauncher() {

        activityResultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    val intentFromResult = result.data
                    if (intentFromResult != null) {
                        val imageData = intentFromResult.data

                        try {
                            if (Build.VERSION.SDK_INT >= 28) {
                                val source = ImageDecoder.createSource(
                                    requireActivity().contentResolver,
                                    imageData!!
                                )
                                selectedBitmap = ImageDecoder.decodeBitmap(source)
                                val file = createTempFile()
                                selectedBitmap?.let {
                                    convertBitmapToFile(file, it)
                                    compressImage(file)
                                    viewModel.image = file.absolutePath
                                }

                                binding.ivImage.setImageBitmap(selectedBitmap)
                            } else {
                                selectedBitmap = MediaStore.Images.Media.getBitmap(
                                    requireActivity().contentResolver,
                                    imageData
                                )
                                binding.ivImage.setImageBitmap(selectedBitmap)
                            }
                        } catch (e: IOException) {
                            e.printStackTrace()
                        }
                    }
                }
            }
        permissionLauncher =
            registerForActivityResult(ActivityResultContracts.RequestPermission()) { result ->
                if (result) {
                    // permission granted
                    val intentToGallery =
                        Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                    activityResultLauncher.launch(intentToGallery)
                } else {
                    // permission denied
                    Toast.makeText(
                        requireContext(),
                        "??zin vermeniz gereklidir.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

    private fun convertBitmapToFile(destination: File, bitmap: Bitmap) {
        lifecycleScope.launch(Dispatchers.IO) {
            destination.createNewFile()
            val bos = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 50, bos)
            val bitmapData = bos.toByteArray()
            try {
                val fos = FileOutputStream(destination)
                fos.write(bitmapData)
                fos.flush()
                fos.close()
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun createTempFile(): File {
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val storageDir: File? = requireContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(
            "JPEG_${timeStamp}_", // prefix
            ".jpg", // suffix
            storageDir // directory
        )
    }

    private fun compressImage(filePath: File, targetMb: Double = 1.0) {
        lifecycleScope.launch(Dispatchers.IO) {
            try {
                val image: Bitmap = BitmapFactory.decodeFile(filePath.absolutePath)
                val length = filePath.length()
                val fileSizeInKB = (length / 1024).toString().toDouble()
                val fileSizeInMB = (fileSizeInKB / 1024).toString().toDouble()
                var quality = 100
                if (fileSizeInMB > targetMb) {
                    quality = ((targetMb / fileSizeInMB) * 100).toInt()
                }
                val fileOutputStream = FileOutputStream(filePath)
                image.compress(Bitmap.CompressFormat.JPEG, quality, fileOutputStream)
                fileOutputStream.close()
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }
    }

    private fun askSpeechInput() {
        if (!SpeechRecognizer.isRecognitionAvailable(requireActivity())) {
            Toast.makeText(
                requireActivity(),
                "Speech recognition is not available",
                Toast.LENGTH_SHORT
            ).show()
        } else {
            val i = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
            i.apply {
                putExtra(
                    RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                    RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
                )
                putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
                putExtra(RecognizerIntent.EXTRA_PROMPT, RECOGNIZER_MESSAGE)
            }

            startActivityForResult(i, rqSpeechRec)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == rqSpeechRec && resultCode == Activity.RESULT_OK) {
            val result = data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS).let { text ->
                text?.get(0)
            }
            binding.note.setText(result)
        }
    }

    companion object {
        const val RQ_SPEECH_REC = 102
        const val RECOGNIZER_MESSAGE = "Bir ??eyler S??yleyin."
    }
}
