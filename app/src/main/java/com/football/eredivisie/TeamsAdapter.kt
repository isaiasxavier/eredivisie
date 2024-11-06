package com.football.eredivisie.ui.teams

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.football.eredivisie.R
import com.football.eredivisie.model.Team
import com.squareup.picasso.Picasso

class TeamsAdapter(private val teams: List<Team>, private val onItemClick: (Team) -> Unit) :
    RecyclerView.Adapter<TeamsAdapter.TeamViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_team, parent, false)
        return TeamViewHolder(view)
    }

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        val team = teams[position]
        holder.teamNameTextView.text = team.name
        Log.d("TeamsAdapter", "Loading image URL: ${team.crest}")
        Picasso.get().load(team.crest).into(holder.teamImageView)
        holder.itemView.setOnClickListener { onItemClick(team) }
    }

    override fun getItemCount(): Int = teams.size

    class TeamViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val teamImageView: ImageView = itemView.findViewById(R.id.teamImageView)
        val teamNameTextView: TextView = itemView.findViewById(R.id.teamNameTextView)
    }
}