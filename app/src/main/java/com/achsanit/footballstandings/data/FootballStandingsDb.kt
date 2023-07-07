package com.achsanit.footballstandings.data

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(
    entities = [ClubEntity::class],
    version = 1,
    exportSchema = false
)
abstract class FootballStandingsDb: RoomDatabase() {
    abstract fun dataClubDao(): DataClubDao
}