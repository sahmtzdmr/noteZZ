package com.sadikahmetozdemir.notezz.ui.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sadikahmetozdemir.notezz.data.local.dto.NotesDatabase
import com.sadikahmetozdemir.notezz.databinding.CustomNoteRawBinding

class NotesAdapter() :
    RecyclerView.Adapter<NotesAdapter.ViewHolder>() {

    private var noteList: List<NotesDatabase>? = null
    var itemClicked: ((NotesDatabase) -> Unit)? = null
    var DeleteItemClicked: ((NotesDatabase) -> Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            CustomNoteRawBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder((binding))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val note = noteList?.get(position)
        note?.let {
            holder.bind(it)
        }
    }

    override fun getItemCount(): Int {
        return noteList?.size ?: 0
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(noteList: List<NotesDatabase>) {
        this.noteList = noteList
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: CustomNoteRawBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.data.setOnClickListener {
                if (bindingAdapterPosition != RecyclerView.NO_POSITION) {
                    val currentItem = noteList?.get(bindingAdapterPosition)
                    currentItem?.let {
                        itemClicked?.invoke(it)
                    }
                }
            }
            binding.ivDelete.setOnClickListener {
                if (bindingAdapterPosition != RecyclerView.NO_POSITION) {
                    val currentItem = noteList?.get(bindingAdapterPosition)
                    currentItem?.let {
                        DeleteItemClicked?.invoke(it)
                    }
                }
            }
        }

        fun bind(item: NotesDatabase) {
            binding.apply {
                data.text = item.data
                date.text = item.date
            }
        }
    }
}
