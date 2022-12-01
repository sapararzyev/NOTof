package com.example.notof.ui.fragment.contact

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.notof.databinding.ItemContactBinding
import com.example.notof.model.ContactModel

class ContactAdabter(private val listner : ShareContactLis) : RecyclerView.Adapter<ContactAdabter.ContaktViewHolder>() {

    private var list = listOf<ContactModel>()

    @SuppressLint("NotifyDataSetChanged")
    fun setlist(list: List<ContactModel>){
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContaktViewHolder {
      val binding = ItemContactBinding
          .inflate(LayoutInflater.from(parent.context),parent,false)
       return ContaktViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ContaktViewHolder, position: Int) {
    holder.onBind(list[position])
        holder.binding.btnWhatsapamg.setOnClickListener{
            listner.share(list[position].contact,true)
        }
        holder.binding.imgShareDialer.setOnClickListener {
            listner.share(list[position].contact,false)
        }
    }

    override fun getItemCount() = list.size

    class ContaktViewHolder( val binding: ItemContactBinding)
        : RecyclerView.ViewHolder(binding.root) {
        fun onBind(model: ContactModel) {
            binding.itemTvName.text = model.name
            binding.itemTvPhone.text = model.contact

        }
    }
    interface ShareContactLis{
        fun share(number: String, shareSwitch:Boolean)
    }
}