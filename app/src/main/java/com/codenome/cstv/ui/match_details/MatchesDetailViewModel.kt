package com.codenome.cstv.ui.match_details

import androidx.lifecycle.viewModelScope
import com.codenome.cstv.base.BaseViewModel
import com.codenome.cstv.model.Team
import com.codenome.cstv.repository.TeamRepository
import com.codenome.cstv.utils.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class MatchesDetailViewModel(private val repository: TeamRepository) :
    BaseViewModel<List<Team>>(repository) {

    private val _flowState = MutableStateFlow<Resource<List<Team>>>(Resource.loading())
    override val flowState
        get() = _flowState


    fun getTeamsById(id: String?) {
        viewModelScope.launch {
            repository.getTeamsByID(id = id).collect {
                _flowState.value = it
            }
        }
    }

}