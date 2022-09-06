package com.codenome.cstv.ui.match

import com.codenome.cstv.base.BaseRepository
import com.codenome.cstv.base.BaseViewModel
import com.codenome.cstv.model.Match

class MatchesViewModel(repository: BaseRepository<List<Match>>) :
    BaseViewModel<List<Match>>(repository)