package com.assignment.tavaga.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.assignment.tavaga.R
import com.assignment.tavaga.model.Image
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.adapter_image.view.*

class ImageAdapter(val context: Context, var images: ArrayList<Image>) :
    RecyclerView.Adapter<ImageAdapter.ViewHolder>() {

    lateinit var onItemClick: ((Image) -> Unit)
    var checked = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.adapter_image, parent, false)


        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return images.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(images[position])

    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        init {
            itemView.setOnClickListener {
                onItemClick.invoke(images[adapterPosition])
            }
        }

        fun setData(image: Image) {

            itemView.textViewTitle.text = image.title

            Picasso.get().load(image.thumbnailUrl).placeholder(R.drawable.progress_animation).into(itemView.imageView)

        }

    }
}