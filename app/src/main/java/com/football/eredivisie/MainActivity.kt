package com.football.eredivisie

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.football.eredivisie.model.Standing
import com.football.eredivisie.model.Team
import com.football.eredivisie.network.RetrofitInstance
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        lifecycleScope.launch {
            try {
                val standingsResponse = RetrofitInstance.api.getStanding()
                val teamsResponse = RetrofitInstance.api.getTeams()

                Log.d("MainActivity", "Standings: ${standingsResponse.standings}")
                Log.d("MainActivity", "Teams: ${teamsResponse.teams}")

                val standings = standingsResponse.standings.flatMap { standingGroup ->
                    standingGroup.table.map { tableEntry ->
                        val team = tableEntry.team
                        val updatedTeam = teamsResponse.teams.find { it.id == team.id }
                        if (updatedTeam != null) {
                            Log.d("MainActivity", "Found matching team: ${updatedTeam.name}")
                        } else {
                            Log.d("MainActivity", "No matching team found for id: ${team.id}")
                        }
                        Standing(
                            position = tableEntry.position,
                            team = updatedTeam ?: team,
                            points = tableEntry.points
                        )
                    }
                }

                Log.d("MainActivity", "Processed Standings: $standings")

                val adapter = StandingAdapter(standings)
                recyclerView.adapter = adapter

                Log.d("MainActivity", "Adapter set with item count: ${adapter.itemCount}")
            } catch (e: Exception) {
                Log.e("MainActivity", "Error loading data", e)
            }
        }
    }
}