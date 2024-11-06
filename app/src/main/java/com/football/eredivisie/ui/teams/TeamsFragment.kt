package com.football.eredivisie.ui.teams

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.football.eredivisie.R
import com.football.eredivisie.model.Team
import com.football.eredivisie.network.RetrofitInstance
import kotlinx.coroutines.launch

class TeamsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_teams, container, false)
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = GridLayoutManager(context, 3) // Ensure 3 columns

        lifecycleScope.launch {
            try {
                val teamsResponse = RetrofitInstance.api.getTeams()
                val adapter = TeamsAdapter(teamsResponse.teams)
                recyclerView.adapter = adapter
            } catch (e: Exception) {
                Log.e("TeamsFragment", "Error loading data", e)
            }
        }

        return view
    }
}