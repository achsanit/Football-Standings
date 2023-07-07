package com.achsanit.footballstandings.ui.fragments.standings

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.achsanit.footballstandings.data.ClubEntity
import com.achsanit.footballstandings.data.DataClubRepository
import kotlinx.coroutines.launch

class StandingsViewModel(private val repo: DataClubRepository): ViewModel() {

    fun getStandings(): LiveData<List<ClubEntity>> {
        return repo.getStandings()
    }

    fun resetStandings() {
        viewModelScope.launch {
            repo.resetStandings()
        }
    }
}