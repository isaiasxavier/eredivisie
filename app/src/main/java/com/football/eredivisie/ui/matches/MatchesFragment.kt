package com.football.eredivisie.ui.matches

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.football.eredivisie.MatchesAdapter
import com.football.eredivisie.R
import com.football.eredivisie.model.Match
import com.football.eredivisie.network.RetrofitInstance
import kotlinx.coroutines.launch
import java.util.Date

class MatchesFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var matchesAdapter: MatchesAdapter
    private var allMatches: List<Match> = emptyList()
    private var showFutureMatches: Boolean = false // Alterado para false

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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_matches, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_filter -> {
                showFutureMatches = !showFutureMatches
                toggleMatches(showFutureMatches)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun fetchMatches() {
        lifecycleScope.launch {
            try {
                val response = RetrofitInstance.api.getMatches()
                allMatches = response.matches
                toggleMatches(showFutureMatches) // Mostrar matches passados por padrão
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun toggleMatches(showFuture: Boolean) {
        val filteredMatches = if (showFuture) {
            allMatches.filter { it.utcDate.after(Date()) }.sortedBy { it.utcDate }
        } else {
            allMatches.filter { it.status == "FINISHED" }.sortedByDescending { it.utcDate }
        }

        val groupedMatches = mutableListOf<Any>()
        filteredMatches.groupBy { it.matchday }
            .toSortedMap(if (showFuture) naturalOrder() else reverseOrder())
            .forEach { (matchday, matches) ->
                groupedMatches.add("Match day $matchday")
                groupedMatches.addAll(matches)
            }
        matchesAdapter.updateMatches(groupedMatches)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }
}