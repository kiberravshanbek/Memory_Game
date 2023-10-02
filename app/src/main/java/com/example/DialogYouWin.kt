package com.example

import android.os.Bundle
import androidx.fragment.app.DialogFragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.memorygame.R
import com.example.memorygame.databinding.DialogYouWinBinding

class DialogYouWin:DialogFragment(R.layout.dialog_you_win) {
    private val binding by viewBinding(DialogYouWinBinding::bind)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


}