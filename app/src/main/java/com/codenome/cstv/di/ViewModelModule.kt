package com.codenome.cstv.di

import com.codenome.cstv.ui.match.MatchesViewModel
import com.codenome.cstv.ui.match_details.MatchesDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MatchesViewModel(get()) }
    viewModel { MatchesDetailViewModel(get()) }
}