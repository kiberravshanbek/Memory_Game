package com.example.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.customview.extensions.dp
import com.example.memorygame.R
import com.example.model.ImageModel
import com.example.model.Level

class NoteAdapter(private val data: ArrayList<ImageModel>,private val level: Level) : RecyclerView.Adapter<NoteViewHolder>() {
    private var onClickListener: ((Int) -> Unit)? = null
    private var onLongClickListener: ((Int) -> Unit)? = null

    fun setOnClickListener(clickListener: (Int) -> Unit) {
        onClickListener = clickListener
    }

    fun setOnLongClickListener(clickListener: (Int) -> Unit) {
        onLongClickListener = clickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_img, parent, false)
        return NoteViewHolder(view, onClickListener, onLongClickListener)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) =
        holder.bind(data[position],level)
}

class NoteViewHolder(
    itemView: View,
    private val onClickListener: ((Int) -> Unit)?,
    private val onLongClickListener: ((Int) -> Unit)?
) : RecyclerView.ViewHolder(itemView) {
    private val layout: LinearLayout = itemView.findViewById(R.id.parent)
    private val img: ImageView = itemView.findViewById(R.id.img)

    fun bind(note: ImageModel,level: Level) {
        when(level){
            Level.Easy-> {
                layout.layoutParams= ViewGroup.LayoutParams(110.dp,110.dp).apply {
                }
            }
            Level.Medium-> {
                layout.layoutParams= ViewGroup.LayoutParams(70.dp,70.dp)

            }
            Level.Hard->{
                layout.layoutParams= ViewGroup.LayoutParams(65.dp,65.dp)
            }
        }
        img.setImageResource(note.resId)
        layout.setOnClickListener {
            onClickListener?.invoke(bindingAdapterPosition)
        }
        layout.setOnLongClickListener {
            onLongClickListener?.invoke(bindingAdapterPosition)
            return@setOnLongClickListener true
        }
    }
}