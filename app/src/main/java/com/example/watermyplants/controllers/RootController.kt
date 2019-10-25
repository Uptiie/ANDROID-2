package com.example.watermyplants.controllers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import com.bluelinelabs.conductor.ControllerChangeHandler
import com.bluelinelabs.conductor.ControllerChangeType
import com.bluelinelabs.conductor.RouterTransaction
import com.bluelinelabs.conductor.changehandler.HorizontalChangeHandler
import com.example.watermyplants.R
import com.example.watermyplants.models.User
import com.example.watermyplants.util.showPassword
import com.example.watermyplants.util.showToast
import com.example.watermyplants.viewmodel.LoginViewModel
import work.beltran.conductorviewmodel.ViewModelController

class RootController : ViewModelController {

    constructor() : super()
    constructor(args: Bundle?) : super(args)


    private lateinit var viewModel: LoginViewModel

    // Inflate Login Layout
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        val view = inflater.inflate(R.layout.login_layout, container, false)

        viewModel = viewModelProvider().get(LoginViewModel::class.java)

        return view
    }

    override fun onChangeEnded(
        changeHandler: ControllerChangeHandler,
        changeType: ControllerChangeType
    ) {
        super.onChangeEnded(changeHandler, changeType)

        val username = view?.findViewById<EditText>(R.id.et_username)
        val password = view?.findViewById<EditText>(R.id.et_password)
        val progressBar = view?.findViewById<ProgressBar>(R.id.pb_login)

        showPassword(view)

        // When "Login" button is clicked, the plant_list will inflate from the PlantListController
        view?.findViewById<Button>(R.id.btn_login)?.setOnClickListener {
            if (!username?.text.isNullOrBlank() && !password?.text.isNullOrBlank()) {
                val usernameValue = username?.text.toString()
                val passwordValue = password?.text.toString()
                val user = User(usernameValue, passwordValue)
                progressBar?.visibility = View.VISIBLE

                viewModel.getUser(user)
                viewModel.loginSuccessful()?.observe(this, androidx.lifecycle.Observer<Boolean> {
                    when (it) {
                        true -> {
                            router.setRoot(RouterTransaction.with(PlantListController()))
                            router.popController(RootController())
                            viewModel.reset()
                        }
                        false -> {
                            progressBar?.visibility = View.INVISIBLE
                            view?.context?.showToast("Please enter in a valid login")
                            viewModel.reset()
                        }
                    }
                })
            } else {
                view?.context?.showToast("Please enter in a username/password")
            }
        }


        // When "Register" button is clicked, the registration_layout will inflate from the RegisterController
        view?.findViewById<Button>(R.id.btn_register)?.setOnClickListener {
            router.pushController(
                RouterTransaction.with(RegisterController(args))
                    .pushChangeHandler(HorizontalChangeHandler())
                    .popChangeHandler(HorizontalChangeHandler())
            )
        }
    }
}





