package com.codenome.cstv.api

import com.codenome.cstv.model.Team
import retrofit2.http.GET
import retrofit2.http.Query

interface TeamsEndpoint {
    @GET("teams")
    suspend fun getTeamsByID(@Query("filter[id]") id: String?): List<Team>?
}