package com.example

import AlImages
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.media.MediaPlayer
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.animation.AnimationUtils
import android.view.animation.DecelerateInterpolator
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.adapter.ImageAdapter
import com.example.adapter.NoteAdapter
import com.example.memorygame.R
import com.example.memorygame.databinding.FragmentGameBinding
import com.example.model.ImageModel
import com.example.model.Level
import com.example.model.Settings
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.Locale


class GameFragment : Fragment(R.layout.fragment_game) {
    private val settings by lazy { Settings.getSettings(requireContext()) }
    private val binding by viewBinding(FragmentGameBinding::bind)
    private lateinit var job: Job
    private lateinit var job2: Job
    private lateinit var job3: Job
    private var a = Level.Easy
    private val allImage = AlImages()
    private var list = ArrayList<ImageModel>(allImage.addWords())
    private var adapterlist = ArrayList<ImageModel>()
    private var emptylist = ArrayList<ImageModel>()
    private lateinit var imageAdapter: NoteAdapter
    private lateinit var countd1: CountDownTimer


    private var i: Int = 0
    private lateinit var easy: Any
    private lateinit var medium: Any
    private lateinit var hard: Any
    private var finish = -1
    private var bool1 = false
    private var bool2 = false
    private var resId1 = -1
    private var resId2 = -1
    private var index1 = -1
    private var index2 = -1
    private var countMYCards = 0
    private var dontClick = 0

    //set ster
    var minutes=0L
    var seconds=0L


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        var list = ArrayList<ImageModel>(allImage.addWords())

        val args = this.arguments
        easy = args?.get("easy")!!
        medium = args?.get("medium")!!
        hard = args?.get("hard")!!

        restartGame()


        val homeButton = view.findViewById<ImageView>(R.id.play_home)
        lifecycleScope.launch {
            delay(2000)
            homeButton.setOnClickListener {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.mainActivity, HomeScreenFragment()).commit()
            }
        }


        //  showGameOverDialog()


    }

    fun restartGame() {
        emptylist.clear()
        adapterlist.clear()

        if (easy == "easy") {
            // Toast.makeText(requireContext(), "1", Toast.LENGTH_SHORT).show()
            a = Level.Easy
            val level = settings.getEasyLevel()
            binding.levelCount.text="Level $level"
            binding.gridView.layoutManager = GridLayoutManager(requireContext(), 6)

            val singlelist = getEasyShuflle()
            adapterlist.addAll(singlelist)
            adapterlist.addAll(singlelist)
            emptylist.addAll(getEasyEmpty())
            adapterlist.shuffle()
            countMYCards = 11


        }

        if (medium =="medium") {
            // Toast.makeText(requireContext(), "2", Toast.LENGTH_SHORT).show()
            a = Level.Medium
            val level = settings.getMediumLevel()
            binding.levelCount.text="Level $level"
            binding.gridView.layoutManager = GridLayoutManager(requireContext(), 6)
            val singlelist = getMediumShuflle()
            adapterlist.clear()
            adapterlist.addAll(singlelist)
            adapterlist.addAll(singlelist)

            emptylist.addAll(getMediumEmpty())
            adapterlist.shuffle()
            countMYCards = 23
        }

        if (hard =="hard") {
            // Toast.makeText(requireContext(), "3", Toast.LENGTH_SHORT).show()
            a = Level.Hard
            val level = settings.getHardLevel()
            binding.levelCount.text="Level $level"
            binding.gridView.layoutManager = GridLayoutManager(requireContext(), 12)
            val singlelist = getHardShuflle()
            adapterlist.clear()
            adapterlist.addAll(singlelist)
            adapterlist.addAll(singlelist)
            emptylist.addAll(getHardEmpty())
            adapterlist.shuffle()
            countMYCards = 47
        }

        Progresbar()


        imageAdapter = NoteAdapter(emptylist, a)
        binding.gridView.adapter = imageAdapter

        if (finish == -1) finish = emptylist.size
        openAllCardsFirstTIME()






        imageAdapter.setOnClickListener { index ->
            // Toast.makeText(requireContext(), "$dontClick", Toast.LENGTH_SHORT).show()
            var empty = ImageModel(R.drawable.empty1)
            if (emptylist.get(index).resId == empty.resId) {
                return@setOnClickListener
            }
            if (dontClick > 1) {
                return@setOnClickListener
            }

            var p = binding.gridView.getChildAt(index) as View
            p.animate().setDuration(300).rotationY(89f).withEndAction {
                emptylist[index] = ImageModel(adapterlist.get(index).resId)
                imageAdapter.notifyDataSetChanged()

                p.rotationY = -89f
                p.animate().setDuration(300).rotationY(0f).withEndAction {

                }.start()
            }.start()
            if (!bool1) {
                ++dontClick
                index1 = index
                resId1 = adapterlist[index].resId
                bool1 = true
                // Toast.makeText(requireContext(), "bos 1", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (bool1 && index != index1) {

                if (resId1 == adapterlist[index].resId) {
                    //Toast.makeText(requireContext(), "bos 2", Toast.LENGTH_SHORT).show()
                    ++dontClick


                    val countDownTimer = object : CountDownTimer(1000, 50) {
                        override fun onTick(p0: Long) {
                        }

                        override fun onFinish() {
                            dontClick = 0

                            emptylist[index] = ImageModel(R.drawable.empty1)
                            emptylist[index1] = ImageModel(R.drawable.empty1)
                            imageAdapter.notifyDataSetChanged()
                            win()
                        }

                    }
                    countDownTimer.start()
                    finish -= 2
                    // Toast.makeText(requireContext(), "sucsess", Toast.LENGTH_SHORT).show()

                    if (finish == 0) {
                   //  showGameOverDialog(setStar())
                     val bundle=Bundle()
//
                     if (easy == "easy") {
                         bundle.putString("kod","easy")
                     }
                     if (medium == "medium") {
                         bundle.putString("kod","medium")
                     }
                     if (hard == "hard") {
                         bundle.putString("kod","hard")
                     }
                     val fragment =WinnerFragment()
                     fragment.arguments=bundle
                     parentFragmentManager.beginTransaction().replace(R.id.mainActivity,fragment).commit()


                    }

                    bool1 = false
                } else {
                    //Toast.makeText(requireContext(), "bos 3", Toast.LENGTH_SHORT).show()
                    ++dontClick


                    val countDownTimer = object : CountDownTimer(1000, 50) {
                        override fun onTick(p0: Long) {

                        }

                        override fun onFinish() {
                            bool1 = false
                            // Toast.makeText(requireContext(), "faild", Toast.LENGTH_SHORT).show()
                            closemp()


                            var p = binding.gridView.getChildAt(index) as View
                            p.animate().setDuration(300).rotationY(-89f).withEndAction {
                                emptylist[index] = ImageModel(R.drawable.hayvonlar_3)
                                imageAdapter.notifyDataSetChanged()

                                p.rotationY = 89f
                                p.animate().setDuration(300).rotationY(0f).withEndAction {
                                    dontClick = 0

                                }.start()
                            }


                            var b0 = binding.gridView.getChildAt(index1) as View
                            b0.animate().setDuration(300).rotationY(-89f).withEndAction {
                                emptylist[index1] = ImageModel(R.drawable.hayvonlar_3)
                                imageAdapter.notifyDataSetChanged()

                                b0.rotationY = 89f
                                b0.animate().setDuration(300).rotationY(0f).withEndAction {

                                }.start()
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

    }

    override fun onDestroyView() {
        if (::countd1.isInitialized)
            countd1.cancel()
        if (::job.isInitialized)
            job
        // if (::open2.isInitialized)
        //    open2.cancel()
        super.onDestroyView()
    }

    fun getEasyShuflle(): List<ImageModel> {
        val easyList = list.shuffled().take(6)
        return easyList
    }

    fun getEasyEmpty(): List<ImageModel> {
        val easyListEmpty = ArrayList<ImageModel>()
        easyListEmpty.clear()
        for (i in 0..11) {
            easyListEmpty.add(ImageModel(R.drawable.hayvonlar_3))
        }
        return easyListEmpty
    }

    fun getMediumEmpty(): List<ImageModel> {
        val mediumListEmpty = ArrayList<ImageModel>()
        mediumListEmpty.clear()
        for (i in 0..23) {
            mediumListEmpty.add(ImageModel(R.drawable.hayvonlar_3))
        }
        return mediumListEmpty
    }

    fun getHardEmpty(): List<ImageModel> {
        val hardListEmpty = ArrayList<ImageModel>()
        hardListEmpty.clear()
        for (i in 0..47) {
            hardListEmpty.add(ImageModel(R.drawable.hayvonlar_3))
        }
        return hardListEmpty
    }

    fun getMediumShuflle(): List<ImageModel> {

        val mediumList = list.shuffled().take(12)
        return mediumList
    }

    fun getHardShuflle(): List<ImageModel> {
        val hardList = list.shuffled().take(24)
        return hardList

    }

    fun open() {
        val open = MediaPlayer.create(context, R.raw.open)
        open.start()
    }

    fun win() {
        val open = MediaPlayer.create(context, R.raw.removecard)
        open.start()
    }

    fun closemp() {
        val open = MediaPlayer.create(context, R.raw.close)
        open.start()
    }

    fun openAllCardsFirstTIME() {
        var time = 1L
        if (easy == "easy") {
            time = 2000L
        }
        if (medium == "medium") {
            time = 4000L
        }
        if (hard == "hard") {
            time = 6000L
        }


        if (isAdded) {
            job2 = lifecycleScope.launch {
                delay(1000)
                for (i in 0..countMYCards) {
                    var p = binding.gridView.getChildAt(i) as View
                    p.animate().setDuration(300).rotationY(89f).withEndAction {
                        emptylist[i] = ImageModel(adapterlist.get(i).resId)
                        imageAdapter.notifyDataSetChanged()

                        p.rotationY = -89f
                        p.animate().setDuration(300).rotationY(0f).withEndAction {


                        }.start()
                    }.start()
                    //
                    job3 = lifecycleScope.launch {
                        delay(time)
                        for (i in 0..countMYCards) {
                            var p = binding.gridView.getChildAt(i)
                            p.animate().setDuration(300).rotationY(-89f).withEndAction {
                                emptylist[i] = ImageModel(R.drawable.hayvonlar_3)
                                imageAdapter.notifyDataSetChanged()

                                p.rotationY = 89f
                                p.animate().setDuration(300).rotationY(0f).withEndAction {

                                }.start()
                            }

                        }
                    }

                }

            }
        }


    }

    private fun showGameOverDialog(a: Int) {
        val dialog = AlertDialog.Builder(requireContext())

        val inflate = LayoutInflater.from(requireContext())
        val dialogView = inflate.inflate(R.layout.dialog_you_win, null)
        dialog.setView(dialogView)
        val dialogD = dialog.create()
        val star1 = dialogView.findViewById<ImageView>(R.id.star1)
        val star2 = dialogView.findViewById<ImageView>(R.id.star2)
        val star3 = dialogView.findViewById<ImageView>(R.id.star3)
        if (a == 1) {
            star1.setImageResource(R.drawable.str)
        }
        if (a == 2) {
            star1.setImageResource(R.drawable.str)
            star2.setImageResource(R.drawable.str)

        }
        if (a == 3) {
            star1.setImageResource(R.drawable.str)
            star2.setImageResource(R.drawable.str)
            star3.setImageResource(R.drawable.str)
        }


        val home = dialogView.findViewById<ImageView>(R.id.home_button)
        home.setOnClickListener {
            dialogD.dismiss()
            parentFragmentManager.beginTransaction().remove(GameFragment()).commit()
            parentFragmentManager.beginTransaction()
                .replace(R.id.mainActivity, HomeScreenFragment()).commit()

        }
        val next = dialogView.findViewById<ImageView>(R.id.win_next)
        next.setOnClickListener {
            dialogD.dismiss()
            countd1.cancel()
             minutes=0L
             seconds=0L
            restartGame()
            Progresbar()
        }
        dialogD.getWindow()?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialogD.show()
    }

    private fun Progresbar() {
        var minut = 0
        if (a == Level.Easy) {
            minut = 2
        }
        if (a == Level.Medium) {
            minut = 4
        }
        if (a == Level.Hard) {
            minut = 7
        }
        job = lifecycleScope.launch {
            binding.time.text = String.format(Locale.getDefault(), "%02d:%02d", minut, 0)
            delay(2500)
            countd1 = object : CountDownTimer((minut * 60 * 1000).toLong(), 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    minutes = millisUntilFinished / (1000 * 60)
                     seconds = (millisUntilFinished / 1000) % 60
                    val timeLeftFormatted =
                        String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds)
                    binding.time.text = timeLeftFormatted

                }

                override fun onFinish() {
                    binding.time.text = "00:00"
                    YouLost()
                }
            }

            countd1.start()

            val process = binding.horizontalProgressBar
            val animation = ObjectAnimator.ofInt(process, "progress", 100, 0)
            animation.duration = (minut * 60 * 1000).toLong()
            animation.repeatCount = 0
            animation.interpolator = DecelerateInterpolator()
            animation.start()

            val progressBar = binding.horizontalProgressBar
            progressBar.max = 100
        }

    }

    @SuppressLint("MissingInflatedId")
    private fun YouLost() {
        val dialog = AlertDialog.Builder(requireContext())

        val inflate = LayoutInflater.from(requireContext())
        val dialogView = inflate.inflate(R.layout.dialog_you_win, null)
        dialog.setView(dialogView)
        val dialogD = dialog.create()
        val home = dialogView.findViewById<ImageView>(R.id.home_button)
        home.setOnClickListener {
            dialogD.dismiss()
            parentFragmentManager.beginTransaction().remove(GameFragment()).commit()
            parentFragmentManager.beginTransaction()
                .replace(R.id.mainActivity, HomeScreenFragment()).commit()

        }
        val text = dialogView.findViewById<TextView>(R.id.youWin)
        text.text = "You Lost!"

        val next = dialogView.findViewById<ImageView>(R.id.win_next)
        next.setImageBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.restart));
        next.setOnClickListener {
            dialogD.dismiss()
            restartGame()
        }
        dialogD.getWindow()?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialogD.show()
    }

    private fun setStar():Int{
        if (easy == "easy") {
            if (minutes>=1&& seconds>20){
                return 3
            }
            if (minutes>=1&& seconds<20){
                return 2
            }
            if (minutes==0L && seconds<50){
                return 1
            }

        }
        if (medium == "medium") {
            if (minutes>=3 && seconds>20){
                return 3
            }
            if (minutes>=2&& seconds<20&& minutes<=3){
                return 2
            }
            if (minutes<=2L && seconds<20){
                return 1
            }

        }
        if (hard == "hard") {
            if (minutes>=5 && seconds>20){
                return 3
            }
            if (minutes>=3&& seconds<20&& minutes<=5){
                return 2
            }
            if (minutes<=2L && seconds<20){
                return 1
            }

        }
        return 0

    }

}