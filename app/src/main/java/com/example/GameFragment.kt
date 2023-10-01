package com.example

import AlImages
import android.graphics.Color
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

    private val binding by viewBinding(FragmentGameBinding::bind)
    private var a = Level.Easy
    private val allImage = AlImages()
    private var list = ArrayList<ImageModel>(allImage.addWords())
    private var adapterlist = ArrayList<ImageModel>()



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        var list = ArrayList<ImageModel>(allImage.addWords())

        val args = this.arguments
        var easy = args?.get("easy")
        var medium = args?.get("medium")
        var hard = args?.get("hard")

        if (easy == "easy") {
            Toast.makeText(requireContext(), "1", Toast.LENGTH_SHORT).show()
            a = Level.Easy
            binding.gridView.numColumns = 6
            val singlelist= getEasyShuflle()
            adapterlist.addAll(singlelist)
            adapterlist.addAll(singlelist)
            adapterlist.shuffle()

        }

        if (medium == "medium") {
            Toast.makeText(requireContext(), "2", Toast.LENGTH_SHORT).show()
            a = Level.Medium
            binding.gridView.numColumns = 6
            val singlelist=getMediumShuflle()
            adapterlist.addAll(singlelist)
            adapterlist.addAll(singlelist)
            adapterlist.shuffle()
        }

        if (hard == "hard") {
            Toast.makeText(requireContext(), "3", Toast.LENGTH_SHORT).show()
            a = Level.Hard
            binding.gridView.numColumns =12
            val singlelist=getHardShuflle()
            adapterlist.addAll(singlelist)
            adapterlist.addAll(singlelist)
            adapterlist.shuffle()
        }


       // var list = ArrayList<ImageModel>()
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



        val imageAdapter = ImageAdapter(requireContext(), adapterlist, a)
        binding.gridView.adapter = imageAdapter
        
        binding.gridView.setOnItemClickListener { _, view, i, id ->
            Toast.makeText(requireContext(), "$i", Toast.LENGTH_SHORT).show()

          //view.animate()
          //    .setDuration(300)
          //    .rotationY(89f)
          //    .withEndAction {
          //        //adapterlist.set(i,ImageModel(R.drawable.img_10))
          //        adapterlist.removeAt(i)
          //        imageAdapter.notifyDataSetChanged()

          //       // view.setBackgroundResource(R.drawable.img_4)
          //        view.rotationY = -89f
          //         view.animate()
          //            .setDuration(300)
          //            .rotationY(0f)
          //            .withEndAction {
          //            }
          //            .start()
          //    }
          //    .start()
        }


    }

    fun getEasyShuflle():List<ImageModel>{
        val easyList =list.shuffled().take(6)
        return easyList
    }
    fun getMediumShuflle():List<ImageModel>{
        val mediumList =list.shuffled().take(12)
        return mediumList
    }

    fun getHardShuflle():List<ImageModel>{
        val hardList =list.shuffled().take(24)
        return hardList

    }

}