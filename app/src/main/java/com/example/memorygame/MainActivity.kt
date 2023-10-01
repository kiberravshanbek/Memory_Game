package com.example.memorygame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Toast.makeText(this, "salom", Toast.LENGTH_SHORT).show()

        Toast.makeText(this, "salom dunyo", Toast.LENGTH_SHORT).show()
    }
}