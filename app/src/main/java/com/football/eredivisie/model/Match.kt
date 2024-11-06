package com.football.eredivisie.model

import java.util.Date

data class Match(
    val id: Int,
    val utcDate: Date,
    val status: String,
    val matchday: Int,
    val stage: String,
    val group: String?,
    val lastUpdated: Date,
    val homeTeam: Team,
    val awayTeam: Team,
    val score: Score,
    val referees: List<Any>
)

data class Score(
    val winner: String?,
    val duration: String,
    val fullTime: TimeScore,
    val halfTime: TimeScore
)

data class TimeScore(
    val home: Int?,
    val away: Int?
)