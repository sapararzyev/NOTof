package com.example.notof.ui.fragment.addNote

import com.example.notof.base.BaseFragment
import com.example.notof.databinding.FragmentAddNoteBinding
import com.example.notof.ui.utils.App
import com.example.notof.model.NoteModel
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.*


class addNoteFragment() : BaseFragment<FragmentAddNoteBinding>(FragmentAddNoteBinding::inflate) {
    override fun setubIU() {
        if (arguments != null) {
            val title = arguments?.getString("title")
            val desc = arguments?.getString("desc")
            val id = arguments?.getInt("id")
            val date = getCurrentDataTime()
            val dateInString = date.toString("yyyy/mm/dd HH:mm:ss")

            binding.etTitle.setText(title.toString())
            binding.etDes.setText(desc.toString())
            binding.btnSaveNote.setOnClickListener {
            App.db.getDao().upNote(NoteModel
            (id, binding.etTitle.text.toString(), binding.etDes.text.toString(), data = dateInString))
            container.navigateUp()
            }
        }else{
            binding.btnSaveNote.setOnClickListener{
                val title = binding.etTitle.text.toString()
                val description = binding.etDes.text.toString()
                val date = getCurrentDataTime()
                val dateInString = date.toString("yyyy/mm/dd HH:mm:ss")
                App.db.getDao().addNote(NoteModel(title = title, description = description, data = dateInString))
                container.navigateUp()
                DateTimeFormatter.ISO_DATE_TIME
            }
        }
    }
    private fun Date.toString(format: String, locale:Locale = Locale.getDefault()): String{
        val formatter = SimpleDateFormat(format,locale)
        return formatter.format(this)
    }
    private fun getCurrentDataTime():Date{
        return Calendar.getInstance().time
    }
}
