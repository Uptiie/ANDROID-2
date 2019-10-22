package com.example.watermyplants

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.example.watermyplants.repo.Repo

class App: Application(){

    companion object {
        var repo: Repo? = null
        var sharedPref: SharedPreferences? = null
        val TOKEN_KEY = "key"
    }

    override fun onCreate() {
        super.onCreate()
        repo = Repo(this)
        sharedPref = getSharedPreferences(TOKEN_KEY, Context.MODE_PRIVATE)

    }

}