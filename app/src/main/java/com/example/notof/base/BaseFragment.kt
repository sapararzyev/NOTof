package com.example.notof.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.viewbinding.ViewBinding
import com.example.notof.R
import com.example.notof.ui.fragment.note.NoteAdabter

typealias Inflate<T> = (LayoutInflater,ViewGroup?,Boolean)->T
abstract class BaseFragment<VB : ViewBinding>(private val inflate: Inflate<VB>) : Fragment() {

    private var _binding: VB? = null
        protected val binding get() = _binding!!

        private var _container: NavController? = null
        protected val container get() = _container!!

        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            _binding = inflate.invoke(inflater, container, false)
            val navHostFragment =  requireActivity().supportFragmentManager
                .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
            _container = navHostFragment.navController
            setubIU()
            return binding.root
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObserver()
    }
    abstract fun setubIU()
    open fun setupObserver(){}
}