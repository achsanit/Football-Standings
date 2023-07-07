package com.achsanit.footballstandings.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "club_table")
data class ClubEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,
    @ColumnInfo(name = "name")
    val clubName: String,
    @ColumnInfo(name = "city")
    val clubCity: String,
    @ColumnInfo(name = "total_match")
    val match: Int = 0,
    @ColumnInfo(name = "total_win")
    val win: Int = 0,
    @ColumnInfo(name = "total_lose")
    val lose: Int = 0,
    @ColumnInfo(name = "total_draw")
    val draw: Int = 0,
    @ColumnInfo(name = "goals_for")
    val gm: Int = 0,
    @ColumnInfo(name = "goals_against")
    val gk: Int = 0,
    @ColumnInfo(name = "total_point")
    val point: Int = 0,
)
