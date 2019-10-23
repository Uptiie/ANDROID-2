package com.example.watermyplants.controllers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import androidx.lifecycle.Observer
import com.bluelinelabs.conductor.ControllerChangeHandler
import com.bluelinelabs.conductor.ControllerChangeType
import com.bluelinelabs.conductor.RouterTransaction
import com.bluelinelabs.conductor.changehandler.HorizontalChangeHandler
import com.example.watermyplants.R
import com.example.watermyplants.models.EditUser
import com.example.watermyplants.util.isNotBlank
import com.example.watermyplants.util.showToast
import com.example.watermyplants.viewmodel.LoginViewModel
import work.beltran.conductorviewmodel.ViewModelController

class RegisterController : ViewModelController {

    constructor() : super()
    constructor(args: Bundle?) : super(args)

    private lateinit var viewModel: LoginViewModel

    // Inflate Registration Layout
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        val view = inflater.inflate(R.layout.registration_layout, container, false)

        viewModel = viewModelProvider().get(LoginViewModel::class.java)

        return view
    }

    override fun onChangeEnded(
        changeHandler: ControllerChangeHandler,
        changeType: ControllerChangeType
    ) {
        super.onChangeEnded(changeHandler, changeType)

        val username = view?.findViewById<EditText>(R.id.et_username)
        val password = view?.findViewById<EditText>(R.id.et_register_password)
        val confirmPassword = view?.findViewById<EditText>(R.id.et_register_confirm_password)
        val phoneNumber = view?.findViewById<EditText>(R.id.et_phone_number)
        val progressBar = view?.findViewById<ProgressBar>(R.id.pb_register)


        // When "Submit" button is clicked, the plant_list will inflate from the RootController
        view?.findViewById<Button>(R.id.btn_submit)?.setOnClickListener {
            if (isNotBlank(username, phoneNumber, password, confirmPassword)) {
                val usernameValue = username?.text.toString()
                val passwordValue = password?.text.toString()
                val confirmPasswordValue = confirmPassword?.text.toString()
                val phoneNumberValue = phoneNumber?.text.toString()
                if (passwordValue == confirmPasswordValue){
                    val user = EditUser(usernameValue, passwordValue, phoneNumberValue)
                    progressBar?.visibility = View.VISIBLE

                    viewModel.registerUser(user)
                    viewModel.registerSuccessful()?.observe(this, Observer<Boolean>{
                        if (!it) {
                            view?.context?.showToast("Failed to register user")
                            progressBar?.visibility = View.INVISIBLE
                        }
                        else {
                            router.pushController(
                                RouterTransaction.with(PlantListController(args))
                                    .pushChangeHandler(HorizontalChangeHandler())
                                    .popChangeHandler(HorizontalChangeHandler())
                            )
                        }
                    })
                } else {
                    view?.context?.showToast("Passwords to match")
                }
            } else {
                view?.context?.showToast("Please fill out all fields")
            }
        }
    }

}

