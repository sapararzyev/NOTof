package com.example.notof.iu.fragment.board

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.notof.R
import com.example.notof.databinding.BoardItemBinding


class BoardAdapter(private val listener : StartListener ) : RecyclerView.Adapter<BoardAdapter.BoardViewHolder>() {

    val titleList = listOf("зометки","контакты","конец")
    val desList = listOf("Добавлять","звонить","Достып ко всем контактам","Это все что есть")
    val imgList = listOf(R.drawable.img,R.drawable.img_1,R.drawable.img_2)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BoardViewHolder {
        val binding = BoardItemBinding
            .inflate(LayoutInflater.from(parent.context),parent,false)
        return BoardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BoardViewHolder, position: Int) {
        holder.pnBind(position)
        holder.binding.btnBoardStart.setOnClickListener{
            listener.Start()
        }
    }
    override fun getItemCount(): Int = titleList.size

    inner class BoardViewHolder( val binding: BoardItemBinding)
        : RecyclerView.ViewHolder(binding.root) {
        fun pnBind(position: Int) {
            binding.imgBoardItem.setBackgroundResource(imgList[position])
            binding.tvBoardTitle.text = titleList[position]
            binding.tvBoardTDes.text = desList[position]

            if (position == 2) {
                binding.btnBoardStart.visibility = View.VISIBLE
            } else {
                binding.btnBoardStart.visibility = View.GONE
            }
        }
    }
    interface StartListener{
        fun Start()
    }
}