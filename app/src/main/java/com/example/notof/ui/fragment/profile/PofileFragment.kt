package com.example.notof.ui.fragment.profile

import android.app.Activity.RESULT_OK
import android.content.Intent
import com.example.notof.base.BaseFragment
import com.example.notof.databinding.FragmentPofileBinding

const val IMAGE_REQUEST_CODE = 100

@Suppress("DEPRECATION")
class PofileFragment : BaseFragment<FragmentPofileBinding>(FragmentPofileBinding::inflate) {

    override fun setubIU() {
        binding.imgProfile.setOnClickListener {
                imageGallery()
        }
    }
    private fun imageGallery(){
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_REQUEST_CODE)
    }
    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (    requestCode == IMAGE_REQUEST_CODE && resultCode == RESULT_OK){
            binding.imgProfile.setImageURI(data?.data)
        }
    }
}