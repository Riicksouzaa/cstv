package com.codenome.cstv.repository

import android.content.Context
import com.codenome.cstv.api.TeamsEndpoint
import com.codenome.cstv.base.BaseRepository
import com.codenome.cstv.model.Team
import com.codenome.cstv.utils.Resource
import com.codenome.cstv.utils.getPlayersPlaceholder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class TeamRepository(private val endpoint: TeamsEndpoint, context: Context) :
    BaseRepository<List<Team>>(context) {

    override suspend fun getFromApi(id: Int?): List<Team>? = null

    private suspend fun getTeamsById(id: String?): List<Team>? {


        return endpoint.getTeamsByID(id)?.toMutableList()
            ?.map { it.copy(players = it.players + getPlayersPlaceholder()) }
    }


    fun getTeamsByID(id: String?): Flow<Resource<List<Team>>> = flow {
        emit(Resource.loading())
        try {
            getTeamsById(id)?.let {
                emit(Resource.success(it))
            } ?: kotlin.run { emit(Resource.error("Ocorreu um erro.")) }
        } catch (e: Exception) {
            emit(Resource.error(e.message.toString()))
        }
    }

}