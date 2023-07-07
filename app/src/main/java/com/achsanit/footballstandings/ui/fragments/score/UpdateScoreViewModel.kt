package com.achsanit.footballstandings.ui.fragments.score

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.achsanit.footballstandings.data.DataClubRepository
import kotlinx.coroutines.launch

class UpdateScoreViewModel(private val repo: DataClubRepository): ViewModel() {
    fun getListName(): LiveData<List<String>> {
        return repo.getListName()
    }

    fun updateScore(
        name: String,
        point: Int,
        goalsFor: Int,
        goalsAgainst: Int,
        draw: Int,
        win: Int,
        lose: Int
    ) {
        viewModelScope.launch {
            repo.updateScore(
                name, point, goalsFor, goalsAgainst, draw, win, lose
            )
        }
    }
}