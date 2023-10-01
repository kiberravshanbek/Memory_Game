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


    private val binding by viewBinding(FragmentGameBinding::bind)
    private var _height = 0
    private var _wight = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.space.post{
            _height=binding.container.height/3
            _wight=binding.container.width/4

            val count =3*4

            binding.container.removeView(binding.container.getChildAt(4))

        }
    }

}