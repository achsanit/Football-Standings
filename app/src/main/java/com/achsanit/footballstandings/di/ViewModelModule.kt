package com.achsanit.footballstandings.di

import com.achsanit.footballstandings.ui.fragments.dataclub.DataClubViewModel
import com.achsanit.footballstandings.ui.fragments.dataclub.add.AddDataClubViewModel
import com.achsanit.footballstandings.ui.fragments.score.UpdateScoreViewModel
import com.achsanit.footballstandings.ui.fragments.standings.StandingsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { AddDataClubViewModel(get()) }
    viewModel { DataClubViewModel(get()) }
    viewModel { StandingsViewModel(get()) }
    viewModel { UpdateScoreViewModel(get()) }
}