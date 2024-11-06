package com.football.eredivisie.model

data class EredivisieTable(
    val standings: List<StandingGroup>
)

data class StandingGroup(
    val table: List<StandingEntry>
)

data class StandingEntry(
    val position: Int,
    val team: Team,
    val points: Int
)