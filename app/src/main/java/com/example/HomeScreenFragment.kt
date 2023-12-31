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
import com.example.model.Settings


class HomeScreenFragment : Fragment(R.layout.fragment_home_screen) {
    private val binding by viewBinding(FragmentHomeScreenBinding::bind)
    private val settings by lazy { Settings.getSettings(requireContext()) }
    private val bundle=Bundle()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.easy.setOnClickListener {
            settings.setCurrentLevel("easy")
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
                            bundle.putString("easy","easy")
                            bundle.putString("medium","null")
                            bundle.putString("hard","null")

                            val fragment = GameFragment()
                            fragment.arguments = bundle
                        parentFragmentManager.beginTransaction().replace(R.id.mainActivity,fragment).addToBackStack("toGame")                            .commit()
                        }
                        .start()
                }
                .start()

        }

        binding.medium.setOnClickListener {

            settings.setCurrentLevel("medium")
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
                            bundle.putString("medium","medium")
                            bundle.putString("easy","null")
                            bundle.putString("hard","null")
                            val fragment = GameFragment()
                            fragment.arguments = bundle
                            parentFragmentManager.beginTransaction().replace(R.id.mainActivity,fragment).addToBackStack("toGame")                            .commit()
                        }
                        .start()
                }
                .start()


        }

        binding.hard.setOnClickListener {

            settings.setCurrentLevel("hard")
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
                            bundle.putString("hard","hard")
                            bundle.putString("medium","null")
                            bundle.putString("easy","null")
                            val fragment = GameFragment()
                            fragment.arguments = bundle
                            parentFragmentManager.beginTransaction().replace(R.id.mainActivity,fragment).addToBackStack("toGame")                            .commit()
                        }
                        .start()
                }
                .start()

        }
    }

}