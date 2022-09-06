package com.codenome.cstv.api

import com.codenome.cstv.model.Match
import retrofit2.http.GET

interface MatchEndpoint {

    @GET("matches/upcoming")
    suspend fun getUpcomingMatches(): List<Match>

    @GET("matches/running")
    suspend fun getRunningMatches(): List<Match>
}