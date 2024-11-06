package com.football.eredivisie.ui.matches

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
import com.football.eredivisie.network.RetrofitInstance
import kotlinx.coroutines.launch

class MatchesFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var matchesAdapter: MatchesAdapter

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

        fetchMatches()
    }

    private fun fetchMatches() {
        lifecycleScope.launch {
            try {
                val response = RetrofitInstance.api.getMatches()
                val groupedMatches = mutableListOf<Any>()
                response.matches.filter { it.status == "FINISHED" } // Filter only finished matches
                    .groupBy { it.matchday }
                    .toSortedMap(reverseOrder()) // Sort matchdays in descending order
                    .forEach { (matchday, matches) ->
                        groupedMatches.add("Matchday $matchday")
                        groupedMatches.addAll(matches)
                    }
                matchesAdapter.updateMatches(groupedMatches)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}