package com.example.marathimatrimony.bottomnavigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.marathimatrimony.MatchesRecyclerAdapter
import com.example.marathimatrimony.R


class MatchesFragment : Fragment() {

    lateinit var recyclerViewMatches:RecyclerView
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<MatchesRecyclerAdapter.ViewHolder>? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View{
        // Inflate the layout for this fragment
        val view:View =inflater.inflate(R.layout.fragment_matches, container, false)
        recyclerViewMatches=view.findViewById(R.id.recyclerViewMatches)
        setupRecyclerView()

        return view
    }
    private fun setupRecyclerView() {
        val layoutManager = LinearLayoutManager(this.context)
        layoutManager.orientation= LinearLayoutManager.HORIZONTAL
        recyclerViewMatches.layoutManager = layoutManager

        adapter = MatchesRecyclerAdapter()
        recyclerViewMatches.adapter = adapter
    }


}