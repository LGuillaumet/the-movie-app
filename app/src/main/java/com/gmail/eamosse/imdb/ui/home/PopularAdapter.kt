package com.gmail.eamosse.imdb.ui.home

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.gmail.eamosse.idbdata.data.Popular
import com.gmail.eamosse.imdb.databinding.PopularItemListBinding
import com.gmail.eamosse.imdb.glide.BidingAdapters

class PopularAdapter(private val items: MutableList <Popular>, val handler: (popular: Popular) -> Unit) :
    ListAdapter<Popular, PopularAdapter.PopularViewHolder>(PopularDiffCallBack()) {

    inner class PopularViewHolder(private val binding: PopularItemListBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Popular) {
        binding.item = item
        BidingAdapters.changeImage(binding.categoryImg, item.poster_path);

        binding.root.setOnClickListener {
            Log.i("IMDB", "Get popular")
            handler(item)
        }
    }
}
    private class PopularDiffCallBack : DiffUtil.ItemCallback<Popular>() {
        override fun areItemsTheSame(oldItem: Popular, newItem: Popular): Boolean =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: Popular, newItem: Popular): Boolean =
            oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return PopularViewHolder(PopularItemListBinding.inflate(inflater, parent, false))
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: PopularViewHolder, position: Int) {
        holder.bind(items[position])
    }
}