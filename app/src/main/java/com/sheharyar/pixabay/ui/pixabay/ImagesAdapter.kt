package com.sheharyar.pixabay.ui.pixabay

import android.annotation.SuppressLint
import android.icu.text.Transliterator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.sheharyar.pixabay.data.entities.HitsList
import com.sheharyar.pixabay.databinding.ItemImageBinding

class ImagesAdapter(private val listener: ImageItemListener) :
    RecyclerView.Adapter<ImagesAdapter.ImageViewHolder>() {
    interface ImageItemListener {
        fun onClickedImage(hitsList: HitsList, position: Int)
    }

    private val items = ArrayList<HitsList>()

    fun setItems(items: ArrayList<HitsList>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val binding: ItemImageBinding =
            ItemImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ImageViewHolder(binding, listener)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        return holder.bind(items[position])
    }

    class ImageViewHolder(
        private val itemImageBinding: ItemImageBinding,
        private val listener: ImageItemListener
    ) : RecyclerView.ViewHolder(itemImageBinding.root),
        View.OnClickListener {
        private lateinit var hitsList: HitsList

        init {
            itemImageBinding.root.setOnClickListener(this)
        }

        @SuppressLint("SetTextI18n")
        fun bind(item: HitsList) {
            this.hitsList = item
            Glide.with(itemImageBinding.root)
                .load(item.largeImageURL)
                .transform(CircleCrop())
                .into(itemImageBinding.image)
        }

        override fun onClick(v: View?) {
            listener.onClickedImage(hitsList, position)
        }
    }
}