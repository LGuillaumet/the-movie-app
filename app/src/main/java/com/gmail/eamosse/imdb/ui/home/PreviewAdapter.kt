package com.gmail.eamosse.imdb.ui.home

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.gmail.eamosse.idbdata.data.Preview
import com.gmail.eamosse.imdb.databinding.PreviewItemListBinding
import com.gmail.eamosse.imdb.glide.BidingAdapters

class PreviewAdapter(private val items: MutableList <Preview>, val handler: (preview: Preview) -> Unit) :
    ListAdapter<Preview, PreviewAdapter.PreviewViewHolder>(PreviewDiffCallBack()) {

    inner class PreviewViewHolder(private val binding: PreviewItemListBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Preview) {
        binding.item = item
        BidingAdapters.changeImage(binding.categoryImg, item.poster_path);

        binding.root.setOnClickListener {
            Log.i("IMDB", "Get previews")
            handler(item)
        }
    }
}
    private class PreviewDiffCallBack : DiffUtil.ItemCallback<Preview>() {
        override fun areItemsTheSame(oldItem: Preview, newItem: Preview): Boolean =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: Preview, newItem: Preview): Boolean =
            oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PreviewViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return PreviewViewHolder(PreviewItemListBinding.inflate(inflater, parent, false))
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: PreviewViewHolder, position: Int) {
        holder.bind(items[position])
    }
}