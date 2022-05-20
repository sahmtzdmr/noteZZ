package com.sadikahmetozdemir.notezz.ui.details

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.view.View
import android.widget.Toast
import com.sadikahmetozdemir.notezz.R
import com.sadikahmetozdemir.notezz.base.BaseFragment
import com.sadikahmetozdemir.notezz.databinding.FragmentNotesDetailBinding
import com.sadikahmetozdemir.notezz.ui.addnote.AddNoteFragment
import com.sadikahmetozdemir.notezz.utils.load
import com.sadikahmetozdemir.notezz.utils.toDateString
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class NotesDetailFragment :
    BaseFragment<FragmentNotesDetailBinding, NotesDetailViewModel>(R.layout.fragment_notes_detail) {
    private val rqSpeechRec = AddNoteFragment.RQ_SPEECH_REC
    private lateinit var date: Date

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imageString = viewModel.notes?.image
        binding.ivImage.load(url = imageString)
        date = Calendar.getInstance().time
        binding.currentDate.text = date.toDateString()
        binding.fabVoice.setOnClickListener {
            askSpeechInput()
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
                putExtra(RecognizerIntent.EXTRA_PROMPT, AddNoteFragment.RECOGNIZER_MESSAGE)
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
        const val RECOGNIZER_MESSAGE = "Bir Şeyler Söyleyin."
    }
}
