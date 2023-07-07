package com.achsanit.footballstandings.ui.fragments.dataclub

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.achsanit.footballstandings.data.ClubEntity
import com.achsanit.footballstandings.data.DataClubRepository
import kotlinx.coroutines.launch

class DataClubViewModel(private val repo: DataClubRepository): ViewModel() {

    fun getAllClub(): LiveData<List<ClubEntity>> {
        return repo.getAllClub()
    }

    fun deleteDataClub() {
        viewModelScope.launch {
            repo.deleteDataClub()
        }
    }
}