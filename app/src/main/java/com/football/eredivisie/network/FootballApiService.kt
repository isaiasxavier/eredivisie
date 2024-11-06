package com.football.eredivisie.network

import com.football.eredivisie.model.EredivisieTable
import com.football.eredivisie.model.TeamsResponse
import retrofit2.http.GET
import retrofit2.http.Headers

interface FootballApiService {
    @Headers("X-Auth-Token: 5be239f512d74fd991afb3ab502916e4")
    @GET("competitions/DED/standings")
    suspend fun getStanding(): EredivisieTable

    @Headers("X-Auth-Token: 5be239f512d74fd991afb3ab502916e4")
    @GET("competitions/DED/teams")
    suspend fun getTeams(): TeamsResponse
}