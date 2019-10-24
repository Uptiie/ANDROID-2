package com.example.watermyplants.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.watermyplants.api.ApiBuilder
import com.example.watermyplants.models.User
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MainViewModel: ViewModel(){

    private val _isTokenGood = MutableLiveData<Boolean>()
    val isTokenGood: LiveData<Boolean> = _isTokenGood

    fun checkToken(token: String){
        ApiBuilder.apiCall().currentUser(token)
            .toObservable()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object: Observer<User>{
                override fun onComplete() {
                    _isTokenGood.value = true
                }

                override fun onSubscribe(d: Disposable) {}
                override fun onNext(t: User) {}

                override fun onError(e: Throwable) {
                    _isTokenGood.value = false
                }
            })
    }

}