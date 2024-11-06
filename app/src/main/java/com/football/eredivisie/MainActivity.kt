package com.football.eredivisie

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.football.eredivisie.ui.matches.MatchesFragment
import com.football.eredivisie.ui.standings.StandingsFragment
import com.football.eredivisie.ui.teams.TeamsFragment

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        drawerLayout = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.nav_view)
        navView.setNavigationItemSelectedListener(this)

        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_standings -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.nav_host_fragment_content_main, StandingsFragment())
                    .addToBackStack(null)
                    .commit()
                supportActionBar?.title = getString(R.string.menu_standings)
                supportActionBar?.setIcon(R.drawable.ic_menu_standings)
            }
            R.id.nav_teams -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.nav_host_fragment_content_main, TeamsFragment())
                    .addToBackStack(null)
                    .commit()
                supportActionBar?.title = getString(R.string.menu_teams)
                supportActionBar?.setIcon(R.drawable.ic_menu_teams)
            }
            R.id.nav_matches -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.nav_host_fragment_content_main, MatchesFragment())
                    .addToBackStack(null)
                    .commit()
                supportActionBar?.title = getString(R.string.menu_matches)
                supportActionBar?.setIcon(R.drawable.ic_menu_matches)
            }
            // Handle other menu items if needed
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}