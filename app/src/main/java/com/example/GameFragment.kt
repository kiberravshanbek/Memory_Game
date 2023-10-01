package com.example

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.memorygame.R
import com.example.memorygame.databinding.FragmentGameBinding


class GameFragment : Fragment(R.layout.fragment_game) {

    private lateinit var list:List<Image>



    private val binding by viewBinding(FragmentGameBinding::bind)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


    }

}