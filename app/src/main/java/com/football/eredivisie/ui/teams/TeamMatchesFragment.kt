package com.football.eredivisie.ui.teams

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.football.eredivisie.MatchesAdapter
import com.football.eredivisie.R
import com.football.eredivisie.model.Match
import com.football.eredivisie.model.Team
import com.football.eredivisie.network.RetrofitInstance
import kotlinx.coroutines.launch

class TeamMatchesFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var matchesAdapter: MatchesAdapter
    private var allMatches: List<Match> = emptyList()
    private var team: Team? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_matches, container, false)
        recyclerView = view.findViewById(R.id.recyclerViewMatches)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.layoutManager = LinearLayoutManager(context)
        matchesAdapter = MatchesAdapter(emptyList())
        recyclerView.adapter = matchesAdapter

        team = arguments?.getParcelable("team")
        fetchTeamMatches()
    }

    private fun fetchTeamMatches() {
        lifecycleScope.launch {
            try {
                val response = RetrofitInstance.api.getMatches()
                allMatches = response.matches.filter { it.homeTeam.id == team?.id || it.awayTeam.id == team?.id }
                updateMatches()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun updateMatches() {
        val groupedMatches = mutableListOf<Any>()
        allMatches.groupBy { it.matchday }
            .toSortedMap()
            .forEach { (matchday, matches) ->
                groupedMatches.add("Match day $matchday")
                groupedMatches.addAll(matches)
            }
        matchesAdapter.updateMatches(groupedMatches)
    }
}