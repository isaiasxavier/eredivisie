package com.football.eredivisie.ui.teams

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.football.eredivisie.R
import com.football.eredivisie.model.Player
import com.football.eredivisie.model.Team

class TeamSquadFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_team_squad, container, false)
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerViewSquad)
        recyclerView.layoutManager = LinearLayoutManager(context)

        val team = arguments?.getParcelable<Team>("team")
        team?.let {
            Log.d("TeamSquadFragment", "Team received: ${it.name}, Squad size: ${it.squad.size}")
            val adapter = TeamSquadAdapter(it.squad)
            recyclerView.adapter = adapter
        }

        return view
    }
}