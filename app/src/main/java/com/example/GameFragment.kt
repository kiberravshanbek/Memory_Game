package com.example

import AlImages
import android.animation.ObjectAnimator
import android.media.MediaPlayer
import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.adapter.ImageAdapter
import com.example.adapter.NoteAdapter
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
    private var emptylist = ArrayList<ImageModel>()
    private lateinit var imageAdapter:NoteAdapter
    private lateinit var back:ImageView  //orqaga qaytish
    private lateinit var voice:ImageView  //ovoz o'chirib yoqish



    private var i: Int = 0
    private var bool1=false
    private var bool2=false
    private var resId1=-1
    private var resId2=-1
    private var index1=-1
    private var index2=-1
    private var countMYCards=0



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        var list = ArrayList<ImageModel>(allImage.addWords())
        back=view.findViewById<ImageView?>(R.id.home_button)
        voice=view.findViewById(R.id.level)

        back.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .setReorderingAllowed(true)
                .addToBackStack("HomeScreenFragment")
                .replace(R.id.mainActivity,HomeScreenFragment()).commit()
        }

        var b=true

        voice.setOnClickListener {
            if (b){
                voice.setImageResource(R.drawable.sound_off)
                b=false
            }else {
                voice.setImageResource(R.drawable.sound_on)
                b =true
            }

        }

        val args = this.arguments
        var easy = args?.get("easy")
        var medium = args?.get("medium")
        var hard = args?.get("hard")

        if (easy == "easy") {
            Toast.makeText(requireContext(), "1", Toast.LENGTH_SHORT).show()
            a = Level.Easy
            binding.gridView.layoutManager=GridLayoutManager(requireContext(),6)
            val singlelist= getEasyShuflle()
            adapterlist.addAll(singlelist)
            adapterlist.addAll(singlelist)
            emptylist.addAll(getEasyEmpty())
            adapterlist.shuffle()
            countMYCards=11


        }

        if (medium == "medium") {
            Toast.makeText(requireContext(), "2", Toast.LENGTH_SHORT).show()
            a = Level.Medium

            binding.gridView.layoutManager=GridLayoutManager(requireContext(),6)
            val singlelist=getMediumShuflle()
            adapterlist.addAll(singlelist)
            adapterlist.addAll(singlelist)
            emptylist.addAll(getMediumEmpty())
            adapterlist.shuffle()
            countMYCards=23
        }

        if (hard == "hard") {
            Toast.makeText(requireContext(), "3", Toast.LENGTH_SHORT).show()
            a = Level.Hard

            binding.gridView.layoutManager=GridLayoutManager(requireContext(),12)
            val singlelist=getHardShuflle()
            adapterlist.addAll(singlelist)
            adapterlist.addAll(singlelist)
            emptylist.addAll(getHardEmpty())
            adapterlist.shuffle()
            countMYCards=47
        }


        val animator = ObjectAnimator.ofInt(binding.horizontalProgressBar, "progress", 100, 0)
        animator.duration = 10000
        animator.repeatCount = 1
        animator.repeatMode = ObjectAnimator.REVERSE
        animator.start()





        imageAdapter = NoteAdapter(emptylist, a)
        binding.gridView.adapter = imageAdapter


        //
        openAllCardsFirstTIME()





        imageAdapter.setOnClickListener { index->
          var  p= binding.gridView.getChildAt(index) as View
            p.animate()
                .setDuration(300)
                .rotationY(89f)
                .withEndAction {
                    emptylist[index]= ImageModel(adapterlist.get(index).resId)
                    imageAdapter.notifyDataSetChanged()

                    p.rotationY = -89f
                    p.animate()
                        .setDuration(300)
                        .rotationY(0f)
                        .withEndAction {

                        }
                        .start()
                }
                .start()
            if (!bool1){
                index1=index
                resId1=adapterlist[index].resId
                bool1=true
                return@setOnClickListener
            }
            if (bool1&&index!=index1){
                if (resId1==adapterlist[index].resId){
                    emptylist[index]= ImageModel(R.drawable.empty1)
                    emptylist[index1]= ImageModel(R.drawable.empty1)
                    imageAdapter.notifyItemChanged(index)
                    imageAdapter.notifyItemChanged(index1)
                    Toast.makeText(requireContext(), "success", Toast.LENGTH_SHORT).show()
                    bool1=false
                }else{
                    val countDownTimer = object : CountDownTimer(1000, 500) {
                        override fun onTick(p0: Long) {

                        }

                        override fun onFinish() {
                            bool1=false
                            Toast.makeText(requireContext(), "failed", Toast.LENGTH_SHORT).show()

                            var  p= binding.gridView.getChildAt(index) as View
                            p.animate()
                                .setDuration(300)
                                .rotationY(-89f)
                                .withEndAction {
                                    emptylist[index]= ImageModel(R.drawable.hayvonlar_3)
                                    imageAdapter.notifyDataSetChanged()

                                    p.rotationY = 89f
                                    p.animate()
                                        .setDuration(300)
                                        .rotationY(0f)
                                        .withEndAction {

                                        }
                                        .start()
                                }


                            var  b0= binding.gridView.getChildAt(index1) as View
                            b0.animate()
                                .setDuration(300)
                                .rotationY(-89f)
                                .withEndAction {
                                    emptylist[index1]= ImageModel(R.drawable.hayvonlar_3)
                                    imageAdapter.notifyDataSetChanged()

                                    b0.rotationY = 89f
                                    b0.animate()
                                        .setDuration(300)
                                        .rotationY(0f)
                                        .withEndAction {

                                        }
                                        .start()
                                }

                        //    emptylist[index]= ImageModel(R.drawable.hayvonlar_3)
                        //    emptylist[index1]= ImageModel(R.drawable.hayvonlar_3)
                        //    imageAdapter.notifyItemChanged(index)
                        //    imageAdapter.notifyItemChanged(index1)
                        }

                    }
                    countDownTimer.start()

                }
            }


        }


//        val anim=AnimationUtils.loadAnimation(requireContext(),R.anim.animation)
//        binding.gridView.animation=anim
//        anim.start()
//        val item_anim=AnimationUtils.loadAnimation(requireContext(),R.anim.fade_in)


//        binding.gridView.setOnItemClickListener(AdapterView.OnItemClickListener(){ adapterView: AdapterView<*>, view2: View, i: Int, l: Long ->
//
//            val linear=view2 as LinearLayout
//            val imageView = linear.getChildAt(0) as ImageView
//            imageView.setImageResource(R.drawable.hayvonlar_3)
//            linear.animate()
//                .setDuration(300)
//                .rotationY(89f)
//                .withEndAction {
//                    adapterlist[i]= ImageModel(R.drawable.hayvonlar_3)
//
//                    Toast.makeText(requireContext(), "1", Toast.LENGTH_SHORT).show()
//                    view.rotationY = -89f
//                    view.animate()
//                        .setDuration(300)
//                        .rotationY(0f)
//                        .withEndAction {
//                            Toast.makeText(requireContext(), "2", Toast.LENGTH_SHORT).show()
//                        }
//                        .start()
//                }
//                .start()
//
//            linear.animate()
//                .setDuration(300)
//                .rotationY(89f)
//                .withEndAction {
//                    //adapterlist.set(i,ImageModel(R.drawable.img_10))
//                    adapterlist.removeAt(i)
//                    imageAdapter.notifyDataSetChanged()
//
//                    // view.setBackgroundResource(R.drawable.img_4)
//                    view.rotationY = -89f
//                    view.animate()
//                        .setDuration(300)
//                        .rotationY(0f)
//                        .withEndAction {
//                        }
//                        .start()
//                }
//                .start()
//
//
//        })
//        binding.gridView.setOnItemClickListener { _, view, i, id ->
//              //open()
//            val item=view as LinearLayout
//            val at = item.getChildAt(0) as ImageView
//


           // imageAdapter.notifyDataSetInvalidated()
//            if (!bool1){
//                bool1=true
//                index1=i
//                resId1=adapterlist[i].resId
//                return
//            }
//            if (bool1){
//                bool1=false
//                if (resId1==adapterlist[i].resId){
//                    Toast.makeText(requireActivity(), "sucsses", Toast.LENGTH_SHORT).show()
//                    win()
//                }else{
//                    Toast.makeText(requireActivity(), "faild", Toast.LENGTH_SHORT).show()
//                    close()
//                }
//            }



        //imageAdapter.notifyDataSetChanged()


    }

    fun getEasyShuflle():List<ImageModel>{
        val easyList =list.shuffled().take(6)
        return easyList
    }
    fun getEasyEmpty():List<ImageModel>{
        val easyListEmpty =ArrayList<ImageModel>()
        for (i in 0..11){
        easyListEmpty.add(ImageModel(R.drawable.hayvonlar_3))
        }
        return easyListEmpty
    }

    fun getMediumEmpty():List<ImageModel>{
        val mediumListEmpty =ArrayList<ImageModel>()
        for (i in 0..23){
            mediumListEmpty.add(ImageModel(R.drawable.hayvonlar_3))
        }
        return mediumListEmpty
    }

    fun getHardEmpty():List<ImageModel>{
        val hardListEmpty =ArrayList<ImageModel>()
        for (i in 0..47){
            hardListEmpty.add(ImageModel(R.drawable.hayvonlar_3))
        }
        return hardListEmpty
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
    fun openAllCardsFirstTIME(){
        val countDownTimer = object : CountDownTimer(1000, 500) {
            override fun onTick(p0: Long) {

            }

            override fun onFinish() {
                for (i in 0..countMYCards) {
                    var p = binding.gridView.getChildAt(i) as View
                    p.animate()
                        .setDuration(300)
                        .rotationY(89f)
                        .withEndAction {
                            emptylist[i] = ImageModel(adapterlist.get(i).resId)
                            imageAdapter.notifyDataSetChanged()

                            p.rotationY = -89f
                            p.animate()
                                .setDuration(300)
                                .rotationY(0f)
                                .withEndAction {

                                    val countDownTimer = object : CountDownTimer(1000, 500) {
                                        override fun onTick(p0: Long) {

                                        }

                                        override fun onFinish() {
                                            for (i in 0..countMYCards){
                                                var  p= binding.gridView.getChildAt(i) as View
                                                p.animate()
                                                    .setDuration(300)
                                                    .rotationY(-89f)
                                                    .withEndAction {
                                                        emptylist[i]= ImageModel(R.drawable.hayvonlar_3)
                                                        imageAdapter.notifyDataSetChanged()

                                                        p.rotationY = 89f
                                                        p.animate()
                                                            .setDuration(300)
                                                            .rotationY(0f)
                                                            .withEndAction {

                                                            }
                                                            .start()
                                                    }

                                            }
                                        }

                                    }
                                    countDownTimer.start()

                                }
                                .start()
                        }
                        .start()
                }

            }

        }
        countDownTimer.start()

        //

    }

}