package com.example.watermyplants

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bluelinelabs.conductor.Conductor
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction
import com.example.watermyplants.controllers.PlantListController
import com.example.watermyplants.controllers.RootController
import com.example.watermyplants.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var router: Router
    private val container: ViewGroup by lazy {
        this.findViewById<ViewGroup>(R.id.activity_main_parent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel = ViewModelProviders.of(this)[MainViewModel::class.java]

        val token = App.sharedPref?.getString(App.TOKEN_KEY, "")
        println(token)
        router = Conductor.attachRouter(this, container, savedInstanceState)

        if (!router.hasRootController()) {
            if (token != null) {
                viewModel.checkToken(token)
            } else router.setRoot(RouterTransaction.with(RootController()))

            viewModel.isTokenGood.observe(this, Observer<Boolean> {
                if (it != null){
                    if (it) router.setRoot(RouterTransaction.with(PlantListController()))
                    else router.setRoot(RouterTransaction.with(RootController()))
                }

            })

        }
    }

    override fun onBackPressed() {
        if (!router.handleBack()){
            super.onBackPressed()
        }

    }
}
