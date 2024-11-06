package com.football.eredivisie.model

import java.util.Date

data class Team(
    val id: Int? = null,
    var name: String? = null,
    var shortName: String? = null,
    var tla: String? = null,
    var crest: String? = null,
    var address: String? = null,
    var website: String? = null,
    var founded: String? = null,
    var clubColors: String? = null,
    var venue: String? = null,
    var lastUpdated: Date? = null
)