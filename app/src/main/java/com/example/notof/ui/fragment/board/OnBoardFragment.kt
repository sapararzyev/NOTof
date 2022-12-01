package com.example.notof.ui.fragment.board


import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.example.notof.base.BaseFragment
import com.example.notof.databinding.FragmentOnBoardBinding
import com.example.notof.ui.utils.App
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


@Suppress("DEPRECATION")
class OnBoardFragment : BaseFragment<FragmentOnBoardBinding>(FragmentOnBoardBinding::inflate),
    onBoardAdapter.StartListener {
    private lateinit var adapter: onBoardAdapter
    private lateinit var auth: FirebaseAuth
    private lateinit var googleSingInClient: GoogleSignInClient

    override fun setubIU() {
        adapter = onBoardAdapter(this)
        binding.onBoardPager.adapter = adapter
        binding.boardTab.setViewPager2(binding.onBoardPager)
        initGoogleSingClient()
    }

    private fun initGoogleSingClient() {
        val gso =
            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("725975583607-jt0vi7ti1e4pa1acgrk4fiid5tpc7our.apps.googleusercontent.com")
                .requestEmail()
                .build()


        googleSingInClient = GoogleSignIn.getClient(requireActivity(), gso)
        auth = Firebase.auth
    }


    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val acount = task.getResult(ApiException::class.java)
                acount.idToken?.let { fireBaseAuthWithGoogle(it) }
            } catch (e: ApiException) {
                Log.e("--------", e.toString())
            }
        }
    }

    private fun fireBaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    container.navigateUp()
                } else {
                    Toast.makeText(requireContext(), task.exception.toString(), Toast.LENGTH_SHORT)
                        .show()
                }
            }
    }

    private fun singIn() {
        val intent = googleSingInClient.signInIntent
        startActivityForResult(intent, RC_SIGN_IN)
    }

    override fun start() {
        App.prefs.seveBoardStart()
        singIn()
    }
    companion object {
        private const val RC_SIGN_IN = 9001
    }

}