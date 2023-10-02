package com.example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.memorygame.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        window.decorView.systemUiVisibility= View.SYSTEM_UI_FLAG_FULLSCREEN

        supportFragmentManager.beginTransaction().replace(R.id.mainActivity,HomeScreenFragment()).commit()

        //salom salom git ishlashi tekshirildi

        //supportFragmentManager.beginTransaction().replace(R.id.mainActivity,SplashFragment()).commit()
    }
}