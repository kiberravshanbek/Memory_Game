package com.example.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.LinearLayout
import com.example.customview.extensions.dp
import com.example.memorygame.R
import com.example.model.ImageModel
import com.example.model.Level

class ImageAdapter(val context:Context,list:ArrayList<ImageModel>,val level:Level):BaseAdapter() {

    val imgList=ArrayList(list)
    override fun getCount(): Int {
        return when(level){
            Level.Easy->12
            Level.Medium->24
            Level.Hard->48
        }
    }

    override fun getItem(position: Int): ImageModel {
        return imgList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var viewImageView=LayoutInflater.from(context).inflate(R.layout.item_img,parent,false) as LinearLayout

        when(level){
            Level.Easy-> {
                viewImageView.layoutParams= ViewGroup.LayoutParams(120.dp,120.dp)
                val view = viewImageView.getChildAt(0) as ImageView
                view.setImageResource(imgList[position].resId)
            }
            Level.Medium-> {
                viewImageView.layoutParams= ViewGroup.LayoutParams(90.dp,90.dp)
                val view = viewImageView.getChildAt(0) as ImageView
                view.setImageResource(imgList[position].resId)

            }
            Level.Hard->{
                viewImageView.layoutParams= ViewGroup.LayoutParams(60.dp,60.dp)
                val view = viewImageView.getChildAt(0) as ImageView
                view.setImageResource(imgList[position].resId)
            }
        }

        return viewImageView
    }
}