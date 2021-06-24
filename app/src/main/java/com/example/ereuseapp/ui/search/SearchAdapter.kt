package com.example.ereuseapp.ui.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ereuseapp.R
import com.example.ereuseapp.models.DevicePreview
import com.squareup.picasso.Picasso

class SearchAdapter(val onPreviewClick: (DevicePreview) -> Unit): RecyclerView.Adapter<SearchAdapter.SearchViewHolder>()  {

    private var dataset: List<DevicePreview> = emptyList()

    inner class SearchViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView = itemView.findViewById(R.id.item_image)
        private val titleTextView: TextView = itemView.findViewById(R.id.item_title)
        private val typeTextView: TextView = itemView.findViewById(R.id.item_type)
        private val scoreBar: ProgressBar = itemView.findViewById(R.id.scoreBar)

        fun renderDevicePreview(devicePreview: DevicePreview) {
            Picasso.get().load(devicePreview.image).into(imageView)
            titleTextView.text = devicePreview.title
            typeTextView.text = devicePreview.device_type
            scoreBar.progress = devicePreview.score

            itemView.setOnClickListener {
                onPreviewClick(devicePreview)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.record_item, parent, false)
        return SearchViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.renderDevicePreview(dataset[position])
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    fun setData(newDataset: List<DevicePreview>) {
        dataset = newDataset.sortedByDescending { it.score }
        notifyDataSetChanged()
    }
}