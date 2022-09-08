package com.codenome.cstv.model

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.codenome.cstv.BuildConfig
import com.codenome.cstv.api.MatchEndpoint
import com.codenome.cstv.utils.getStringDate
import com.codenome.cstv.utils.parseDate
import com.codenome.cstv.utils.toCalendar
import retrofit2.HttpException
import java.io.IOException
import java.util.*


private const val FIRST_PAGE = 1

class MatchesPagingSource(private val endpoint: MatchEndpoint) : PagingSource<Int, Match>() {
    private var myKey: Int? = null
    override fun getRefreshKey(state: PagingState<Int, Match>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Match> {
        val pageIndex = params.key ?: FIRST_PAGE
        return try {
            val calendar = Calendar.getInstance()
            val pastMatches =
                endpoint.getPastMatches(end_at = calendar.getStringDate(), page = pageIndex)
                    .toMutableList()
            if (pastMatches.isEmpty()) {
                if (myKey == null) {
                    myKey = pageIndex - 1
                }

                val matches =
                    endpoint.getUpcomingMatches(page = pageIndex - (myKey ?: 0)).toMutableList()
                if (pageIndex == FIRST_PAGE) {
                    matches.addAll(endpoint.getRunningMatches(page = FIRST_PAGE)
                        .map { it.copy(isRunning = true) }
                        .toMutableList())
                }
                pastMatches.addAll(matches)
            }
            val filteredMatches =
                pastMatches.filter { it.firstOpponent != null && it.secondOpponent != null }
                    .sortedBy { it.original_scheduled_at?.parseDate()?.toCalendar()?.timeInMillis }

            val nextKey = if (filteredMatches.isEmpty()) {
                null
            } else {
                pageIndex + (params.loadSize / BuildConfig.PAGINATION_SIZE)
            }
            LoadResult.Page(
                data = filteredMatches,
                prevKey = if (pageIndex == FIRST_PAGE) null else pageIndex,
                nextKey = nextKey
            )
        } catch (e: IOException) {
            return LoadResult.Error(e)
        } catch (e: HttpException) {
            return LoadResult.Error(e)
        }
    }
}