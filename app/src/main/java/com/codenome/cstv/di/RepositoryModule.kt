package com.codenome.cstv.di

import com.codenome.cstv.repository.MatchRepository
import com.codenome.cstv.repository.TeamRepository
import org.koin.dsl.module


val repositoryModule = module {
    single { MatchRepository(get(), get()) }
    single { TeamRepository(get(), get()) }
}