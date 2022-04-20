package com.sadikahmetozdemir.notezz.ui.home.folder

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sadikahmetozdemir.notezz.data.local.dto.FolderDataBase
import com.sadikahmetozdemir.notezz.databinding.CustomFolderViewBinding

class FoldersAdapter : RecyclerView.Adapter<FoldersAdapter.ViewHolder>() {

    private var folderList: ArrayList<FolderDataBase>? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            CustomFolderViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder((binding))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val note = folderList?.get(position)
        note?.let {
            holder.bind(it)
        }
    }

    override fun getItemCount(): Int {
        return folderList?.size ?: 0
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(noteList: ArrayList<FolderDataBase>) {
        this.folderList?.clear()
        this.folderList = noteList
        notifyDataSetChanged()
        // notifyItemRangeInserted(0,noteList.lastIndex)
    }

    inner class ViewHolder(val binding: CustomFolderViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {

        }

        fun bind(item: FolderDataBase) {
            binding.apply {
                tvFolderName.text = item.folder
            }
        }
    }
}