package com.football.eredivisie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.football.eredivisie.model.Match
import java.text.SimpleDateFormat
import java.util.Locale

class MatchesAdapter(private var matches: List<Any>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val VIEW_TYPE_HEADER = 0
        private const val VIEW_TYPE_ITEM = 1
    }

    override fun getItemViewType(position: Int): Int {
        return if (matches[position] is String) VIEW_TYPE_HEADER else VIEW_TYPE_ITEM
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_TYPE_HEADER) {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_match_header, parent, false)
            HeaderViewHolder(view)
        } else {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_match, parent, false)
            MatchViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is HeaderViewHolder) {
            holder.headerTextView.text = matches[position] as String
        } else if (holder is MatchViewHolder) {
            val match = matches[position] as Match
            holder.homeTeamTextView.text = match.homeTeam.name ?: "Unknown Team"
            holder.awayTeamTextView.text = match.awayTeam.name ?: "Unknown Team"
            holder.homeTeamScoreTextView.text = match.score.fullTime.home?.toString() ?: "-"
            holder.awayTeamScoreTextView.text = match.score.fullTime.away?.toString() ?: "-"

            // Format and set the match date
            val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            holder.matchDateTextView.text = dateFormat.format(match.utcDate)

            // Alternar a cor das linhas dentro de cada matchday
            val backgroundColor = if (position % 2 == 0) {
                R.color.defaultRowBackground
            } else {
                R.color.selectedRowBackground
            }
            holder.itemView.setBackgroundResource(backgroundColor)
        }
    }

    override fun getItemCount(): Int = matches.size

    fun updateMatches(newMatches: List<Any>) {
        matches = newMatches
        notifyDataSetChanged() // Notifica o RecyclerView sobre as mudanças nos dados
    }

    class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val headerTextView: TextView = itemView.findViewById(R.id.headerTextView)
    }

    class MatchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val homeTeamTextView: TextView = itemView.findViewById(R.id.homeTeamTextView)
        val homeTeamScoreTextView: TextView = itemView.findViewById(R.id.homeTeamScoreTextView)
        val awayTeamTextView: TextView = itemView.findViewById(R.id.awayTeamTextView)
        val awayTeamScoreTextView: TextView = itemView.findViewById(R.id.awayTeamScoreTextView)
        val matchDateTextView: TextView = itemView.findViewById(R.id.matchDateTextView)
    }
}