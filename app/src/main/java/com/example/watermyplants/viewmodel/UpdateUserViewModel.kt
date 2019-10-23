package com.example.watermyplants.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.watermyplants.App.Companion.repo
import com.example.watermyplants.models.EditUser

class UpdateUserViewModel: ViewModel() {

    fun updateUser(token: String, user: EditUser){
        repo?.updateUser(token, user)
    }

    fun userUpdated(): LiveData<Int>?{
        return repo?.userUpdated()
    }

}