package com.football.eredivisie.ui.teams

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.football.eredivisie.R
import com.football.eredivisie.model.Team
import com.squareup.picasso.Picasso

class TeamDetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_team_detail, container, false)
        val team = arguments?.getParcelable<Team>("team")

        val teamCrestImageView: ImageView = view.findViewById(R.id.teamCrestImageView)
        val teamNameTextView: TextView = view.findViewById(R.id.teamNameTextView)
        val teamAddressTextView: TextView = view.findViewById(R.id.teamAddressTextView)
        val teamWebsiteTextView: TextView = view.findViewById(R.id.teamWebsiteTextView)
        val teamFoundedTextView: TextView = view.findViewById(R.id.teamFoundedTextView)
        val teamClubColorsTextView: TextView = view.findViewById(R.id.teamClubColorsTextView)
        val teamVenueTextView: TextView = view.findViewById(R.id.teamVenueTextView)

        team?.let {
            Log.d("TeamDetailFragment", "Loading image URL: ${it.crest}")
            Picasso.get()
                .load(it.crest)
                .error(R.drawable.error_image) // Ensure this drawable exists
                .into(teamCrestImageView, object : com.squareup.picasso.Callback {
                    override fun onSuccess() {
                        Log.d("TeamDetailFragment", "Image loaded successfully: ${it.crest}")
                    }

                    override fun onError(e: Exception?) {
                        Log.e("TeamDetailFragment", "Error loading image: ${it.crest}", e)
                    }
                })
            teamNameTextView.text = it.name
            teamAddressTextView.text = it.address
            teamWebsiteTextView.text = it.website
            teamFoundedTextView.text = it.founded
            teamClubColorsTextView.text = it.clubColors
            teamVenueTextView.text = it.venue
        } ?: run {
            Log.e("TeamDetailFragment", "Team data is null")
        }

        return view
    }
}