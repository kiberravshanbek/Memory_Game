package com.example

import AlImages
import android.animation.ObjectAnimator
import android.graphics.Color
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.view.animation.DecelerateInterpolator
import android.widget.Toast
import androidx.core.view.children
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.adapter.ImageAdapter
import com.example.memorygame.R
import com.example.memorygame.databinding.FragmentGameBinding
import com.example.model.ImageModel
import com.example.model.Level
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class GameFragment : Fragment(R.layout.fragment_game) {

    private val binding by viewBinding(FragmentGameBinding::bind)
    private var a = Level.Easy
    private val allImage = AlImages()
    private var list = ArrayList<ImageModel>(allImage.addWords())
    private var adapterlist = ArrayList<ImageModel>()



    private var i: Int = 0
    private var bool1=false
    private var bool2=false
    private var resId1=-1
    private var resId2=-1
    private var index1=-1
    private var index2=-1



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


        val animator = ObjectAnimator.ofInt(binding.horizontalProgressBar, "progress", 100, 0)
        animator.duration = 10000
        animator.repeatCount = 1
        animator.repeatMode = ObjectAnimator.REVERSE
        animator.start()





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
        val anim=AnimationUtils.loadAnimation(requireContext(),R.anim.animation)
        binding.gridView.animation=anim
        anim.start()
        val item_anim=AnimationUtils.loadAnimation(requireContext(),R.anim.fade_in)



        binding.gridView.setOnItemClickListener { _, view, i, id ->
              open()
            val item=binding.gridView.getChildAt(i)
            item.animate()
                .setDuration(300)
                .rotationY(89f)
                .withEndAction {
                   adapterlist[i] = ImageModel(R.drawable.img_4)
                    val imageAdapter = ImageAdapter(requireContext(), adapterlist, a)
                    //imageAdapter.notifyDataSetChanged()

                    Toast.makeText(requireContext(), "1", Toast.LENGTH_SHORT).show()
                    view.rotationY = -89f
                     view.animate()
                        .setDuration(300)
                        .rotationY(0f)
                        .withEndAction {
                            Toast.makeText(requireContext(), "2", Toast.LENGTH_SHORT).show()
                        }
                        .start()
                }
                .start()

            if (!bool1){
                bool1=true
                index1=i
                resId1=adapterlist[i].resId
                return@setOnItemClickListener
            }
            if (bool1){
                bool1=false
                if (resId1==adapterlist[i].resId){
                    Toast.makeText(requireActivity(), "sucsses", Toast.LENGTH_SHORT).show()
                    win()
                }else{
                    Toast.makeText(requireActivity(), "faild", Toast.LENGTH_SHORT).show()
                    close()
                }
            }

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
    fun open(){
        val open = MediaPlayer.create(context, R.raw.open)
        open.start()
    }
    fun win(){
        val open = MediaPlayer.create(context, R.raw.removecard)
        open.start()
    }
    fun close(){
        val open = MediaPlayer.create(context, R.raw.close)
        open.start()
    }

}