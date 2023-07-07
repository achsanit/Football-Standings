package com.achsanit.footballstandings.data

import androidx.lifecycle.LiveData
import androidx.room.Query

class DataClubRepository(private val clubDao: DataClubDao) {
    suspend fun insertClub(data: ClubEntity) {
        clubDao.insertClub(data)
    }

    fun checkClubData(
        name: String,
        city: String
    ): LiveData<ClubEntity> {
        return clubDao.checkClubData(name, city)
    }

    fun getAllClub(): LiveData<List<ClubEntity>> {
        return clubDao.getAllClub()
    }

    fun getStandings(): LiveData<List<ClubEntity>> {
        return clubDao.getStandings()
    }

    fun getListName(): LiveData<List<String>> {
        return clubDao.getListClubName()
    }

    suspend fun updateScore(
        name: String,
        point: Int,
        goalsFor: Int,
        goalsAgainst: Int,
        draw: Int,
        win: Int,
        lose: Int
    ) {
        clubDao.updateScore(
            name, point, goalsFor, goalsAgainst, draw, win, lose
        )
    }
    suspend fun resetStandings() {
        clubDao.resetStandings()
    }

    suspend fun deleteDataClub() {
        clubDao.deleteDataClub()
    }
}