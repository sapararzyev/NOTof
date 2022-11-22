package com.example.notof.ui.profile

import android.app.Activity.RESULT_OK
import android.content.Intent
import com.bumptech.glide.Glide
import com.example.notof.base.BaseFragment
import com.example.notof.databinding.FragmentPofileBinding

class PofileFragment : BaseFragment<FragmentPofileBinding>(FragmentPofileBinding::inflate) {


    companion object{
        const val IMAGE_REQUEST_CODE = 100
    }
    override fun setubIU() {
        Glide.with(this).load(
            "https://funik.ru/wp-content/uploads/2018/10/0a37dbac85e134cfb3a5-700x394.jpg"
        ).circleCrop()
            .into(binding.imgProfile)

        binding.imgProfile.setOnClickListener {
                imageGallery()
        }
    }

    private fun imageGallery(){
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (    requestCode == IMAGE_REQUEST_CODE && resultCode == RESULT_OK){
            binding.imgProfile.setImageURI(data?.data)

        }

    }
}