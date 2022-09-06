package com.codenome.cstv.repository

import android.content.Context
import com.codenome.cstv.api.MatchEndpoint
import com.codenome.cstv.base.BaseRepository
import com.codenome.cstv.model.Match

class MatchRepository(private val endpoint: MatchEndpoint, context: Context) :
    BaseRepository<List<Match>>(context) {

    override suspend fun getFromApi(id: Int?): List<Match> {
        val runningMatches = endpoint.getRunningMatches()
            .map { it.copy(isRunning = true) }
            .toMutableList()

        val upcommingMatches = endpoint.getUpcomingMatches()
            .toMutableList()

        val filteredMateches =
            upcommingMatches.filter { it.firstOpponent != null && it.secondOpponent != null }

        runningMatches.addAll(filteredMateches)
        return runningMatches
    }
}