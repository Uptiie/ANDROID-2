package com.example.watermyplants.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.watermyplants.App.Companion.repo
import com.example.watermyplants.models.EditUser
import com.example.watermyplants.models.User

class LoginViewModel: ViewModel(){

    fun getUser(user: User){
        repo?.getLoginToken(user)
    }

    fun reset(){
        repo?.resetLoginCheck()
    }

    fun loginSuccessful(): LiveData<Boolean>?{
        return repo?.loginSuccessful()
    }

    fun registerUser(user: EditUser){
        repo?.registerUser(user)
    }

    fun  registerSuccessful(): LiveData<Boolean>?{
        return repo?.registerSuccessful()
    }

}

