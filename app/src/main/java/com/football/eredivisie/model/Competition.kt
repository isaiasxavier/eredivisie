package com.football.eredivisie.model

data class Competition(
    val id: Int,
    val name: String,
    val code: String,
    val type: String,
    val emblem: String
)