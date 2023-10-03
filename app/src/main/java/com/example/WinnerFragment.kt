package com.example

import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.memorygame.R
import com.example.model.Settings


class WinnerFragment : Fragment(R.layout.fragment_winner) {
    private lateinit var easy: Any
    private val settings by lazy { Settings.getSettings(requireContext()) }
    private lateinit var medium: Any
    private lateinit var hard: Any
    private lateinit var kod: Any
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args = this.arguments
        kod = args?.get("kod")!!



        showGameOverDialog(2)
    }
    private fun showGameOverDialog(a: Int) {
        val dialog = AlertDialog.Builder(requireContext())
        dialog.setCancelable(false)

        val inflate = LayoutInflater.from(requireContext())
        val dialogView = inflate.inflate(R.layout.dialog_you_win, null)
        dialog.setView(dialogView)
        dialog.setCancelable(false)
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
            val bundle=Bundle()
            if (kod=="easy"){
                settings.setEasyLevel(settings.getEasyLevel().inc())
                bundle.putString("easy","easy")
                bundle.putString("medium","null")
                bundle.putString("hard","null")
            }
            if (kod=="medium"){

                settings.setMediumLevel(settings.getMediumLevel().inc())
                bundle.putString("easy","null")
                bundle.putString("medium","medium")
                bundle.putString("hard","null")

            }
            if (kod=="hard"){

                settings.setHardLevel(settings.getHardLevel().inc())
                bundle.putString("easy","null")
                bundle.putString("medium","null")
                bundle.putString("hard","hard")

            }
            val fragment = GameFragment()
            fragment.arguments = bundle
            parentFragmentManager.beginTransaction().replace(R.id.mainActivity,fragment).commit()
        }
        dialogD.setCancelable(false)
        dialogD.getWindow()?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialogD.show()
    }

}

