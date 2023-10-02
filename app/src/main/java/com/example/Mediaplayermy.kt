package com.example

import android.content.Context
import android.media.MediaPlayer
import com.example.memorygame.R

class Mediaplayermy(context: Context) {
    val open = MediaPlayer.create(context,R.raw.open)
    fun mediapler(){
        open.start()
    }
}