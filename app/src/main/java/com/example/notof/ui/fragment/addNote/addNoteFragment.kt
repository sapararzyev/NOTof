package com.example.notof.ui.fragment.addNote

import com.example.notof.base.BaseFragment
import com.example.notof.databinding.FragmentAddNoteBinding
import com.example.notof.app.App
import com.example.notof.model.NoteModel


class addNoteFragment : BaseFragment<FragmentAddNoteBinding>(FragmentAddNoteBinding::inflate) {
    override fun setubIU() {
        if (arguments != null) {
            val title = arguments?.getString("title")
            val desc = arguments?.getString("desc")
            val id = arguments?.getInt("id")

            binding.etTitle.setText(title.toString())
            binding.etDes.setText(desc.toString())
            binding.btnSaveNote.setOnClickListener {
            App.db.getDao().upNote(NoteModel
            (id, binding.etTitle.text.toString(), binding.etDes.text.toString()))
            container.navigateUp()
} }else{
            binding.btnSaveNote.setOnClickListener{
                val title = binding.etTitle.text.toString()
                val description = binding.etDes.text.toString()
                App.db.getDao().addNote(NoteModel(title = title, description = description))
                container.navigateUp()

            }
        }

    }
}
