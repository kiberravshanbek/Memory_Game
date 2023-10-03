package com.example

import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import androidx.fragment.app.Fragment
import com.example.memorygame.R

class SplashFragment: Fragment(R.layout.fragment_splash) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val countDownTimer = object : CountDownTimer(2000, 500) {
            override fun onTick(p0: Long) {
            }

            override fun onFinish() {
               parentFragmentManager.beginTransaction().replace(R.id.mainActivity,HomeScreenFragment())
            }

        }
        countDownTimer.start()
    }
}