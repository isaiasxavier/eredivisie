package com.football.eredivisie.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.football.eredivisie.StandingAdapter
import com.football.eredivisie.databinding.FragmentHomeBinding
import com.football.eredivisie.model.Standing
import com.football.eredivisie.network.RetrofitInstance
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val recyclerView: RecyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)

        lifecycleScope.launch {
            try {
                val standingsResponse = RetrofitInstance.api.getStanding()
                val teamsResponse = RetrofitInstance.api.getTeams()

                val standings = standingsResponse.standings.flatMap { standingGroup ->
                    standingGroup.table.map { tableEntry ->
                        val team = tableEntry.team
                        val updatedTeam = teamsResponse.teams.find { it.id == team.id }
                        Standing(
                            position = tableEntry.position,
                            team = updatedTeam ?: team,
                            points = tableEntry.points
                        )
                    }
                }

                val adapter = StandingAdapter(standings)
                recyclerView.adapter = adapter

            } catch (e: Exception) {
                Log.e("HomeFragment", "Error loading data", e)
            }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}