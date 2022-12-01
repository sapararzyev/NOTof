package com.example.notof.ui.fragment.note

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.notof.databinding.ItemNoteBinding
import com.example.notof.ui.utils.App
import com.example.notof.model.NoteModel

class NoteAdabter(private val noteClikInterfase : NoteClikInterfase) : RecyclerView.Adapter<NoteAdabter.NoteViewHolder>() {

    private var list: ArrayList<NoteModel> = arrayListOf()

    @SuppressLint("NotifyDataSetChanged")
    fun addNote(list: ArrayList<NoteModel>) {
        this.list = list
        notifyDataSetChanged()
    }

    fun deleteNote(position: Int) {
        App.db.getDao().DeleteNote(list.removeAt(position))
        notifyItemRemoved(position)
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val binding = ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.onBind(list[position])
        holder.itemView.setOnClickListener {
            noteClikInterfase.onNoteClik(list[position])
        }
    }

    override fun getItemCount(): Int = list.size

    inner class NoteViewHolder(private val binding: ItemNoteBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(model: NoteModel) {
            binding.itemTvTiyle.text = model.title
            binding.itemTvDes.text = model.description
            binding.itemTvData.text = model.data
        }
    }
    interface NoteClikInterfase {
        fun onNoteClik(model: NoteModel)
    }
}
