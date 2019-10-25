package com.example.watermyplants.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.watermyplants.App
import com.example.watermyplants.api.ApiBuilder
import com.example.watermyplants.models.EditUser
import com.example.watermyplants.models.User
import com.example.watermyplants.models.UserToken
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

object UserDao {
    private val _loginSuccessful = MutableLiveData<Boolean>()
    val loginSuccessful: LiveData<Boolean> = _loginSuccessful
    private val disposableLogin = CompositeDisposable()

    fun resetUser(){
        _loginSuccessful.value = null
        _registerSuccessful.value = null
        _updateSuccessful.value = null
    }

    fun getLoginToken(user: User) {
        val observable = ApiBuilder.apiCall().login(user)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
        disposableLogin.add(observable.subscribeWith(object : DisposableObserver<UserToken>() {
            override fun onComplete() {
                _loginSuccessful.value = true
            }
            override fun onNext(t: UserToken) {
                App.sharedPref?.edit()?.putString(App.TOKEN_KEY, t.token)?.apply()
            }

            override fun onError(e: Throwable) {
                _loginSuccessful.value = false
            }

        }))
    }


    private val _registerSuccessful = MutableLiveData<Boolean>()
    val registerSuccessful: LiveData<Boolean> = _registerSuccessful

    private val disposableRegister = CompositeDisposable()

    fun registerUser(user: EditUser){
        val observable = ApiBuilder.apiCall().register(user)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
        disposableRegister.add(observable.subscribeWith(object : DisposableObserver<User>() {
            override fun onComplete() {
                val userLogin = User(user.username, user.password)
                getLoginToken(userLogin)
            }

            override fun onNext(t: User){
                _registerSuccessful.value = true
            }

            override fun onError(e: Throwable) {
                _registerSuccessful.value = false
            }

        }))
    }


    private val disposableUpdate = CompositeDisposable()

    private val _updateSuccessful = MutableLiveData<Int>()
    val updateSuccessful: LiveData<Int> = _updateSuccessful

    fun updateUser(token: String, user: EditUser){
        val observable = ApiBuilder.apiCall().updateInfo(token, user)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
        disposableUpdate.add(observable.subscribeWith(object : DisposableObserver<Int>() {
            override fun onComplete() {}

            override fun onNext(t: Int){
                _updateSuccessful.value = t
            }

            override fun onError(e: Throwable) {
                println(e)
                _updateSuccessful.value = -1
            }

        }))
    }


}