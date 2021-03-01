package com.example.marathimatrimony

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter :  RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    private val images = intArrayOf(
        R.drawable.ic_add_photo,
        R.drawable.ic_add_the_organization_work_in,
        R.drawable.ic_horoscope,
        R.drawable.ic_family_details1 )

    private val titles = arrayOf(
        "Put a Face To Your Profile",
        "Add the organization you work in",
        "Add Star and Rassi -Let Matches discover you",
        "Provide your sibling datails")

    private val details = arrayOf(
        "UPLOAD PHOTO",
        "ADD DETAIL",
        "ADD DETAIL",
        "ADD DETAIL")

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var itemImage: ImageView = itemView.findViewById(R.id.item_image)
        var itemTitle: TextView = itemView.findViewById(R.id.item_title)
        var itemDetail: TextView = itemView.findViewById(R.id.item_detail)

    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.card_layout, viewGroup, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.itemImage.setImageResource(images[i])
        viewHolder.itemTitle.text = titles[i]
        viewHolder.itemDetail.text = details[i]
    }

    override fun getItemCount(): Int {
        return titles.size
    }
}