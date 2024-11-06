package com.football.eredivisie.model

data class MatchesResponse(
    val competition: Competition,
    val season: Season,
    val matches: List<Match>
)