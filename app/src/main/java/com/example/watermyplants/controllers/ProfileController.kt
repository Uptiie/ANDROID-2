package com.example.watermyplants.controllers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.Observer
import com.bluelinelabs.conductor.ControllerChangeHandler
import com.bluelinelabs.conductor.ControllerChangeType
import com.example.watermyplants.App
import com.example.watermyplants.R
import com.example.watermyplants.models.EditUser
import com.example.watermyplants.util.getUsernameFromToken
import com.example.watermyplants.util.isNotBlank
import com.example.watermyplants.util.showToast
import com.example.watermyplants.viewmodel.UpdateUserViewModel
import kotlinx.android.synthetic.main.profile_layout.view.*
import work.beltran.conductorviewmodel.ViewModelController

class ProfileController : ViewModelController {

    constructor() : super()
    constructor(args: Bundle?) : super(args)

    lateinit var viewModel: UpdateUserViewModel

    // Inflate Profile Layout
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        val view = inflater.inflate(R.layout.profile_layout, container, false)
        viewModel = viewModelProvider().get(UpdateUserViewModel::class.java)

        val token = App.sharedPref?.getString(App.TOKEN_KEY, "")

        val usernameDeToken = getUsernameFromToken(token)

        val username = view.et_username_profile
        val phone = view.et_phone_number_profile
        val password = view.et_register_password_profile
        val confirmPassword = view.et_register_confirm_password_profile

        username.setText(usernameDeToken)

        // When "Update" is clicked, the plant_list will inflate from the PlantListController
        view?.findViewById<Button>(R.id.btn_update)?.setOnClickListener {
            val usernameValue = username.text.toString()
            val phoneValue = phone.text.toString()
            val passwordValue = password.text.toString()
            val confirmPasswordValue = confirmPassword.text.toString()

            if (isNotBlank(username, phone, password, confirmPassword)) {
                if (passwordValue == confirmPasswordValue){
                    val user = EditUser(usernameValue, passwordValue, phoneValue)
                    if (token != null) viewModel.updateUser(token, user)

                    viewModel.userUpdated()?.observe(this, Observer {
                        if (it != null) {
                            if (it != -1){
                                router.popCurrentController()
                                view.context.showToast("$usernameDeToken updated")
                            }
                            else view.context.showToast("Failed to update $usernameDeToken")
                        }
                    })

                } else view.context.showToast("Passwords to match")

            } else view.context.showToast("Please fill out all fields")
        }
        return view
    }

    override fun onChangeEnded(changeHandler: ControllerChangeHandler, changeType: ControllerChangeType) {
        super.onChangeEnded(changeHandler, changeType)

        viewModel.reset()

    }
}
