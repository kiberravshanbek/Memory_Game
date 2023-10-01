package com.example

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.adapter.ImageAdapter
import com.example.memorygame.R
import com.example.memorygame.databinding.FragmentGameBinding
import com.example.model.ImageModel
import com.example.model.Level


class GameFragment : Fragment(R.layout.fragment_game) {

    private lateinit var list: List<ImageModel>
    private val binding by viewBinding(FragmentGameBinding::bind)
    private var a = Level.Easy


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val args = this.arguments
        var easy = args?.get("easy")
        var medium = args?.get("medium")
        var hard = args?.get("hard")

        if (easy == "easy") {
            Toast.makeText(requireContext(), "1", Toast.LENGTH_SHORT).show()
            a = Level.Easy
            binding.gridView.numColumns=6
        }

        if (medium == "medium") {
            Toast.makeText(requireContext(), "2", Toast.LENGTH_SHORT).show()
            a = Level.Medium
        }

        if (hard == "hard") {
            Toast.makeText(requireContext(), "3", Toast.LENGTH_SHORT).show()
            a = Level.Hard
        }


        var list = ArrayList<ImageModel>()
        list.add(ImageModel(R.drawable.bg_img))
        list.add(ImageModel(R.drawable.bg_img))
        list.add(ImageModel(R.drawable.bg_img))
        list.add(ImageModel(R.drawable.bg_img))
        list.add(ImageModel(R.drawable.bg_img))
        list.add(ImageModel(R.drawable.bg_img))
        list.add(ImageModel(R.drawable.bg_img))
        list.add(ImageModel(R.drawable.bg_img))
        list.add(ImageModel(R.drawable.bg_img))
        list.add(ImageModel(R.drawable.bg_img))
        list.add(ImageModel(R.drawable.bg_img))
        list.add(ImageModel(R.drawable.bg_img))

      //  list.add(ImageModel(R.drawable.bg_img))
      //  list.add(ImageModel(R.drawable.bg_img))
      //  list.add(ImageModel(R.drawable.bg_img))
      //  list.add(ImageModel(R.drawable.bg_img))
      //  list.add(ImageModel(R.drawable.bg_img))
      //  list.add(ImageModel(R.drawable.bg_img))
      //  list.add(ImageModel(R.drawable.bg_img))
      //  list.add(ImageModel(R.drawable.bg_img))
      //  list.add(ImageModel(R.drawable.bg_img))
      //  list.add(ImageModel(R.drawable.bg_img))
      //  list.add(ImageModel(R.drawable.bg_img))
      //  list.add(ImageModel(R.drawable.bg_img))

      // list.add(ImageModel(R.drawable.bg_img))
      // list.add(ImageModel(R.drawable.bg_img))
      // list.add(ImageModel(R.drawable.bg_img))
      // list.add(ImageModel(R.drawable.bg_img))
      // list.add(ImageModel(R.drawable.bg_img))
      // list.add(ImageModel(R.drawable.bg_img))
      // list.add(ImageModel(R.drawable.bg_img))
      // list.add(ImageModel(R.drawable.bg_img))
      // list.add(ImageModel(R.drawable.bg_img))
      // list.add(ImageModel(R.drawable.bg_img))
      // list.add(ImageModel(R.drawable.bg_img))
      // list.add(ImageModel(R.drawable.bg_img))
      // list.add(ImageModel(R.drawable.bg_img))
      // list.add(ImageModel(R.drawable.bg_img))
      // list.add(ImageModel(R.drawable.bg_img))
      // list.add(ImageModel(R.drawable.bg_img))
      // list.add(ImageModel(R.drawable.bg_img))
      // list.add(ImageModel(R.drawable.bg_img))
      // list.add(ImageModel(R.drawable.bg_img))
      // list.add(ImageModel(R.drawable.bg_img))
      // list.add(ImageModel(R.drawable.bg_img))
      // list.add(ImageModel(R.drawable.bg_img))
      // list.add(ImageModel(R.drawable.bg_img))
      // list.add(ImageModel(R.drawable.bg_img))


        val imageAdapter = ImageAdapter(requireContext(), list, a)
        binding.gridView.adapter = imageAdapter


    }

}