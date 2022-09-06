package com.codenome.cstv.di

import com.codenome.cstv.base.BaseRepository
import com.codenome.cstv.model.Match
import com.codenome.cstv.repository.MatchRepository
import com.codenome.cstv.repository.TeamRepository
import org.koin.dsl.module


val repositoryModule = module {
    single<BaseRepository<List<Match>>> { MatchRepository(get(), get()) }
    single { TeamRepository(get(), get()) }
}