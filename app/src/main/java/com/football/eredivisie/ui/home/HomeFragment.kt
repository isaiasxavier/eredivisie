package com.football.eredivisie.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.football.eredivisie.R
import com.football.eredivisie.databinding.FragmentHomeBinding
import com.football.eredivisie.ui.matches.MatchesFragment
import com.football.eredivisie.ui.standings.StandingsFragment
import com.football.eredivisie.ui.teams.TeamsFragment

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = HomeFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Carregar a imagem usando Glide
        // Standings
        Glide.with(this)
            .load(R.drawable.standings_home_png)
            .into(binding.standingsHome)

        // Matches
        Glide.with(this)
            .load(R.drawable.matches_home_png)
            .into(binding.matchesHome)

        // Teams
        Glide.with(this)
            .load(R.drawable.team_home)
            .into(binding.teamsHome)

        // Profile
        Glide.with(this)
            .load(R.drawable.profile_home)
            .into(binding.profileHome)

        binding.standingsHome.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment_content_main, StandingsFragment())
                .addToBackStack(null)
                .commit()
        }

        binding.matchesHome.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment_content_main, MatchesFragment())
                .addToBackStack(null)
                .commit()
        }

        binding.teamsHome.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment_content_main, TeamsFragment())
                .addToBackStack(null)
                .commit()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}