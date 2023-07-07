package com.achsanit.footballstandings.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DataClubDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertClub(data: ClubEntity)

    @Query("SELECT * FROM CLUB_TABLE WHERE name LIKE:name and city LIKE:city")
    fun checkClubData(name: String, city: String): LiveData<ClubEntity>

    @Query("SELECT * FROM CLUB_TABLE")
    fun getAllClub(): LiveData<List<ClubEntity>>

    @Query("SELECT * FROM CLUB_TABLE ORDER BY total_point DESC")
    fun getStandings(): LiveData<List<ClubEntity>>

    @Query("SELECT name FROM CLUB_TABLE")
    fun getListClubName(): LiveData<List<String>>

    @Query("UPDATE CLUB_TABLE SET total_point= total_point + :point, total_match = total_match + 1, goals_against = goals_against + :goalsAgainst, goals_for = goals_for + :goalsFor, total_win = total_win + :win, total_draw = total_draw + :draw, total_lose = total_lose + :lose WHERE name LIKE :name")
    suspend fun updateScore(
        name: String,
        point: Int,
        goalsFor: Int,
        goalsAgainst: Int,
        draw: Int,
        win: Int,
        lose: Int
    )

    @Query("UPDATE CLUB_TABLE SET total_point= 0, total_match = 0, goals_against = 0, goals_for = 0, total_win = 0, total_draw = 0, total_lose = 0")
    suspend fun resetStandings()

    @Query("DELETE FROM CLUB_TABLE")
    suspend fun deleteDataClub()
}