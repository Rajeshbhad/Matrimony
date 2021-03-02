package com.example.marathimatrimony

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MatchesRecyclerAdapter :  RecyclerView.Adapter<MatchesRecyclerAdapter.ViewHolder>() {
    private val titles1 = arrayOf("All", "New", "Premium", "Mutual","Viewed","Shortlisted","Looking","Horoscope","Viewed","Shortlisted","Nearby")

    private val titles2 = arrayOf("Matches", "Matches", "Matches", "Matches","You","You","For You","Matches","By You","By You","Matches")

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var itemTitle1: TextView = itemView.findViewById(R.id.item_title1)
        var itemTitle2: TextView = itemView.findViewById(R.id.item_title2)

    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.card_layout_matches, viewGroup, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.itemTitle1.text = titles1[i]
        viewHolder.itemTitle2.text = titles2[i]

    }

    override fun getItemCount(): Int {
        return titles1.size
    }
}