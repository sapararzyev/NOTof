package com.example.notof.ui.fragment.contact

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.ContactsContract
import androidx.core.app.ActivityCompat
import com.example.notof.base.BaseFragment
import com.example.notof.databinding.FragmentContackBinding
import com.example.notof.model.ContactModel


class ContactFragment : BaseFragment<FragmentContackBinding>(FragmentContackBinding::inflate),
    ContactAdabter.ShareContactLis {

    private lateinit var adabter: ContactAdabter

    override fun setubIU() {
        adabter = ContactAdabter(this)
        binding.rvContact.adapter = adabter
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                android.Manifest.permission.READ_CONTACTS
            )
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(requireActivity(), Array(1) {
                android.Manifest.permission
                    .READ_CONTACTS
            }, 111)
        } else
            getContact()
    }

    @SuppressLint("Range")
    private fun getContact() {
        val list = arrayListOf<ContactModel>()

        val contentResolver = requireActivity().contentResolver
        val cursor = contentResolver.query(
            ContactsContract.Contacts.CONTENT_URI
            , null,
            null,
            null,
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)

        if (cursor?.count!!> 0){
            while (cursor.moveToNext())

                if (Integer.parseInt(
                        cursor.getString
                            (cursor.getColumnIndex(
                            ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0 ){
                    val id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID))
                    val name = cursor.getString(
                        cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
                    val phoneCurcor = contentResolver.query(
                        ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                        null,ContactsContract
                            .CommonDataKinds.Phone.CONTACT_ID + "=?",
                        arrayOf(id),null)

                    if (phoneCurcor?.moveToNext()!!){
                        val phoneNamber =
                            phoneCurcor.getString(phoneCurcor.getColumnIndex
                                (ContactsContract.CommonDataKinds.Phone.NUMBER))
                        phoneCurcor.close()
                        list.add(ContactModel(name,phoneNamber))
                    }
                    phoneCurcor.close()
                }
        }

        cursor.close()
        adabter.setlist(list)
    }

    override fun share(number: String, shareSwitch:Boolean) {
        if (shareSwitch){
            AlertDialog.Builder(requireContext())
                .setTitle("перейти на номер $number?")
                .setNegativeButton("net",null)
                .setPositiveButton("da"){_:DialogInterface, _: Int ->
                    val url = "https://api.whatsapp.com/send?phone=$number"
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.data = (Uri.parse(url))
                    requireActivity().startActivity(intent)
                }.show()
        }else{
            AlertDialog.Builder(requireContext())
                .setTitle("перейти на номер $number?")
                .setNegativeButton("net",null)
                .setPositiveButton("da"){
                    _: DialogInterface, _: Int ->
                    val intent =
                        Intent(
                            Intent.ACTION_DIAL,
                            Uri.fromParts("tel",number,null))
                    requireActivity().startActivity(intent)
                }.show()
        }
    }
}