package com.achsanit.footballstandings.ui.fragments.dataclub.add

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.achsanit.footballstandings.data.ClubEntity
import com.achsanit.footballstandings.data.DataClubRepository
import kotlinx.coroutines.launch

class AddDataClubViewModel(private val repo: DataClubRepository) : ViewModel() {

    fun addDataClub(data: ClubEntity) {
        viewModelScope.launch {
            repo.insertClub(data)
        }
    }

    fun checkClubData(name: String, city: String): LiveData<ClubEntity> {
        return repo.checkClubData(name, city)
    }
}