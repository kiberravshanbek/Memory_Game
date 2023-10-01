package com.example

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.memorygame.R
import com.example.memorygame.databinding.FragmentHomeScreenBinding


class HomeScreenFragment : Fragment(R.layout.fragment_home_screen) {
    private val binding by viewBinding(FragmentHomeScreenBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.easy.setOnClickListener {
            binding.easy.animate()
                .scaleX(0.7f)
                .setDuration(200)
                .scaleY(0.7f)
                .withEndAction {

                    binding.easy.animate()
                        .setDuration(90)
                        .scaleX(1f)
                        .scaleY(1f)
                        .withEndAction {
                        parentFragmentManager.beginTransaction().replace(R.id.mainActivity,GameFragment()).addToBackStack("toGame")                            .commit()
                        }
                        .start()
                }
                .start()

        }

        binding.medium.setOnClickListener {
            binding.medium.animate()
                .scaleX(0.7f)
                .setDuration(200)
                .scaleY(0.7f)
                .withEndAction {

                    binding.medium.animate()
                        .setDuration(90)
                        .scaleX(1f)
                        .scaleY(1f)
                        .withEndAction {
                            parentFragmentManager.beginTransaction().replace(R.id.mainActivity,GameFragment()).addToBackStack("toGame")                            .commit()
                        }
                        .start()
                }
                .start()


        }

        binding.hard.setOnClickListener {
            binding.hard.animate()
                .scaleX(0.7f)
                .setDuration(200)
                .scaleY(0.7f)
                .withEndAction {

                    binding.hard.animate()
                        .setDuration(90)
                        .scaleX(1f)
                        .scaleY(1f)
                        .withEndAction {
                            parentFragmentManager.beginTransaction().replace(R.id.mainActivity,GameFragment()).addToBackStack("toGame")                            .commit()
                        }
                        .start()
                }
                .start()

        }
    }

}