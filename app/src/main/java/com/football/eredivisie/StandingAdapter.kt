package com.football.eredivisie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.football.eredivisie.model.Standing

class StandingAdapter(private val standings: List<Standing>) :
    RecyclerView.Adapter<StandingAdapter.StandingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StandingViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_standing, parent, false)
        return StandingViewHolder(view)
    }

    override fun onBindViewHolder(holder: StandingViewHolder, position: Int) {
        val standing = standings[position]
        holder.positionTextView.text = standing.position.toString()
        standing.team.let { team ->
            holder.teamNameTextView.text = team.name
        } ?: run {
            holder.teamNameTextView.text = "Unknown Team"
        }
        holder.pointsTextView.text = standing.points.toString()

        // Alternar a cor das linhas
        val backgroundColor = if (position % 2 == 0) {
            R.color.defaultRowBackground
        } else {
            R.color.selectedRowBackground
        }
        holder.itemView.setBackgroundResource(backgroundColor)
    }

    override fun getItemCount(): Int = standings.size

    class StandingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val positionTextView: TextView = itemView.findViewById(R.id.positionTextView)
        val teamNameTextView: TextView = itemView.findViewById(R.id.teamNameTextView)
        val pointsTextView: TextView = itemView.findViewById(R.id.pointsTextView)
    }
}