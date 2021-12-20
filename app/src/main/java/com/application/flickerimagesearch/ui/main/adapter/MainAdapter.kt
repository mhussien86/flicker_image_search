package com.application.flickerimagesearch.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.application.flickerimagesearch.R
import com.application.flickerimagesearch.data.model.PhotoResponse
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_layout.view.*

class MainAdapter(
    private val photos: ArrayList<PhotoResponse>
) : RecyclerView.Adapter<MainAdapter.DataViewHolder>() {

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(flickerPhoto: PhotoResponse) {
            itemView.textViewPhotoTitle.text = flickerPhoto.title
            Glide.with(itemView.imageViewPhoto.context)
                .load("https://farm${flickerPhoto.farm}.staticflickr.com/${flickerPhoto.server}/${flickerPhoto.id}_${flickerPhoto.secret}.jpg")
                .into(itemView.imageViewPhoto)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_layout, parent,
                false
            )
        )

    override fun getItemCount(): Int = photos.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(photos[position])

    fun addData(list: List<PhotoResponse>) {
        photos.addAll(list)
    }
}