package com.codenome.cstv.repository

import android.content.Context
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.codenome.cstv.BuildConfig
import com.codenome.cstv.api.MatchEndpoint
import com.codenome.cstv.base.BaseRepository
import com.codenome.cstv.model.Match
import com.codenome.cstv.model.MatchesPagingSource
import kotlinx.coroutines.flow.Flow

class MatchRepository(private val endpoint: MatchEndpoint, context: Context) :
    BaseRepository<List<Match>>(context) {

    override suspend fun getFromApi(id: Int?): List<Match> {
        val runningMatches = endpoint.getRunningMatches(page = 1)
            .map { it.copy(isRunning = true) }
            .toMutableList()

        val upcommingMatches = endpoint.getUpcomingMatches(page = 1)
            .toMutableList()

        val filteredMateches =
            upcommingMatches.filter { it.firstOpponent != null && it.secondOpponent != null }

        runningMatches.addAll(filteredMateches)
        return runningMatches
    }


    fun getMatches(): Flow<PagingData<Match>> {
        return Pager(
            config = PagingConfig(
                pageSize = BuildConfig.PAGINATION_SIZE,
                enablePlaceholders = true
            ),
            pagingSourceFactory = {
                MatchesPagingSource(endpoint)
            }
        ).flow
    }

//    fun getMatches(): Flow<PagingData<Match>> {
//        return Pager(
//            config = PagingConfig(
//                pageSize = BuildConfig.PAGINATION_SIZE,
//                enablePlaceholders = true
//            ),
//            pagingSourceFactory = {
//                MatchesPagingSource(endpoint)
//            }
//        ).flow
//    }
}