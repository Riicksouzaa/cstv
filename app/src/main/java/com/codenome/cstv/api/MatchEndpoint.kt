package com.codenome.cstv.api

import com.codenome.cstv.BuildConfig
import com.codenome.cstv.model.Match
import retrofit2.http.GET
import retrofit2.http.Query

interface MatchEndpoint {

    @GET("matches/upcoming")
    suspend fun getUpcomingMatches(
        @Query("per_page") per_page: Int = BuildConfig.PAGINATION_SIZE,
        @Query("page") page: Int
    ): List<Match>

    @GET("matches/running")
    suspend fun getRunningMatches(
        @Query("per_page") per_page: Int = BuildConfig.PAGINATION_SIZE,
        @Query("page") page: Int
    ): List<Match>

    @GET("matches/past")
    suspend fun getPastMatches(
        @Query("filter[end_at]") end_at: String? = null,
        @Query("per_page") per_page: Int = BuildConfig.PAGINATION_SIZE,
        @Query("page") page: Int
    ): List<Match>
}