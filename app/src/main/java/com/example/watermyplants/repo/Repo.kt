package com.example.watermyplants.repo

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.watermyplants.api.ApiBuilder
import com.example.watermyplants.models.EditUser
import com.example.watermyplants.models.User
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

class Repo(context: Context) {


    fun getLoginToken(user: User){
        AuthDao.getLoginToken(user)
    }

    fun loginSuccessful(): LiveData<Boolean>{
        return AuthDao.loginSuccessful
    }

    fun resetLoginCheck(){
        AuthDao.resetLoginCheck()
    }

    fun registerUser(user: EditUser){
        AuthDao.registerUser(user)
    }

    fun registerSuccessful(): LiveData<Boolean>{
        return AuthDao.registerSuccessful
    }





}