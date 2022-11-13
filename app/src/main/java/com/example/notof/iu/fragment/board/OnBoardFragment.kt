package com.example.notof.iu.fragment.board


import com.example.notof.base.BaseFragment
import com.example.notof.databinding.FragmentOnBoardBinding
import com.example.notof.iu.App


class OnBoardFragment : BaseFragment<FragmentOnBoardBinding>(FragmentOnBoardBinding::inflate)
    ,BoardAdapter.StartListener {
    private lateinit var adapter: BoardAdapter
    override fun setubIU() {
        adapter = BoardAdapter(this)
        binding.onBoardPeger.adapter= adapter
        binding.boardTab.setViewPager2(binding.onBoardPeger)
    }

    override fun Start() {
        App.prefs.seveBoardStart()
        container.navigateUp()
    }
}