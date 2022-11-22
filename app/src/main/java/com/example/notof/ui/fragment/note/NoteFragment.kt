package com.example.notof.ui.fragment.note

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ItemTouchHelper.SimpleCallback
import androidx.recyclerview.widget.RecyclerView
import com.example.notof.R
import com.example.notof.base.BaseFragment
import com.example.notof.databinding.FragmentNoteBinding
import com.example.notof.app.App
import com.example.notof.model.NoteModel


class NoteFragment : BaseFragment<FragmentNoteBinding>(FragmentNoteBinding::inflate)
    ,NoteAdabter.NoteClikInterfase {
    private lateinit var adabter: NoteAdabter

    override fun setubIU() {
        adabter = NoteAdabter(this)
        binding.rvNote.adapter = adabter
        adabter.addNote(App.db.getDao().getAllNote() as ArrayList<NoteModel>)
    }


    override fun setupObserver() {
        super.setupObserver()
        deleteNote()
       binding.btnaddNote.setOnClickListener{
           container.navigate(R.id.addNoteFragment)
       }
        binding.btnNote.setOnClickListener {
            container.navigate(R.id.pofileFragment)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun deleteNote(){
        val simplleColbeck
        = object : SimpleCallback(0,ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                AlertDialog.Builder(requireContext())
                    .setTitle("удалить")
                    .setNegativeButton("нет"){ _:DialogInterface?, _:Int ->
                        adabter.notifyItemChanged(viewHolder.adapterPosition)
                    }
                    .setPositiveButton("да"){_:DialogInterface,_:Int ->
                        adabter.deleteNote(viewHolder.adapterPosition)
                        adabter.notifyItemChanged(viewHolder.adapterPosition)
                    }
                    .show()
            }
        }
        val itemTouchHelper = ItemTouchHelper(simplleColbeck)
        itemTouchHelper.attachToRecyclerView(binding.rvNote)
        adabter.notifyDataSetChanged()
    }

    override fun onNoteClik(model: NoteModel) {
        val bundle  = Bundle()
        bundle.putString("title", model.title)
        bundle.putString("desc", model.description)
        model.id?.let { bundle.putInt("id", it) }
        container.navigate(R.id.addNoteFragment, bundle)

    }
}
