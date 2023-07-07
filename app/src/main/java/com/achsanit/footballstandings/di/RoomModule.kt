package com.achsanit.footballstandings.di

import androidx.room.Room
import com.achsanit.footballstandings.data.DataClubRepository
import com.achsanit.footballstandings.data.FootballStandingsDb
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module


val localDbModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            FootballStandingsDb::class.java,
            "football_standings.db"
        ).build()
    }

    factory{ get<FootballStandingsDb>().dataClubDao() }
}

val repositoryModule = module {
    single { DataClubRepository(get()) }
}