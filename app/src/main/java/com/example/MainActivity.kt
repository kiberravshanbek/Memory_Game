package com.example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.memorygame.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().replace(R.id.mainActivity,HomeScreenFragment()).commit()
    }
}