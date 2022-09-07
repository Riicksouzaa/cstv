package com.codenome.cstv.ui.match

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.codenome.cstv.base.BaseViewModel
import com.codenome.cstv.model.Match
import com.codenome.cstv.repository.MatchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class MatchesViewModel(private val repository: MatchRepository) :
    BaseViewModel<List<Match>>(repository) {

    private lateinit var _matchesFlow: Flow<PagingData<Match>>
    val matchesFlow: Flow<PagingData<Match>>
        get() = _matchesFlow

    init {
        getMatches()
    }

    private fun getMatches() {
        viewModelScope.launch {
            _matchesFlow = repository.getMatches().cachedIn(viewModelScope)
        }
    }
}