package com.football.eredivisie.ui.teams

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.football.eredivisie.R
import com.football.eredivisie.model.Player

class TeamSquadAdapter(private val squad: List<Player>) :
    RecyclerView.Adapter<TeamSquadAdapter.SquadViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SquadViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_player, parent, false)
        return SquadViewHolder(view)
    }

    override fun onBindViewHolder(holder: SquadViewHolder, position: Int) {
        val player = squad[position]
        holder.nameTextView.text = player.name
        holder.positionTextView.text = player.position
        holder.dateOfBirthTextView.text = player.dateOfBirth
        holder.nationalityTextView.text = player.nationality

        // Alternar cores das linhas
        if (position % 2 == 0) {
            holder.itemView.setBackgroundColor(holder.itemView.context.getColor(R.color.colorEvenRow))
        } else {
            holder.itemView.setBackgroundColor(holder.itemView.context.getColor(R.color.colorOddRow))
        }
    }

    override fun getItemCount(): Int = squad.size

    class SquadViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        val positionTextView: TextView = itemView.findViewById(R.id.positionTextView)
        val dateOfBirthTextView: TextView = itemView.findViewById(R.id.dateOfBirthTextView)
        val nationalityTextView: TextView = itemView.findViewById(R.id.nationalityTextView)
    }
}