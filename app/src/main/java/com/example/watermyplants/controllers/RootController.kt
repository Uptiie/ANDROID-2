package com.example.watermyplants.controllers

import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.bluelinelabs.conductor.Controller
import com.bluelinelabs.conductor.ControllerChangeHandler
import com.bluelinelabs.conductor.ControllerChangeType
import com.bluelinelabs.conductor.RouterTransaction
import com.bluelinelabs.conductor.changehandler.HorizontalChangeHandler
import com.example.watermyplants.R
import com.example.watermyplants.api.ApiBuilder
import com.example.watermyplants.models.User
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RootController: Controller {

    constructor(): super()
    constructor(args: Bundle?) : super(args)

    // Inflate Login Layout
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {



        val view = inflater.inflate(R.layout.login_layout, container, false)
        return view
    }

    override fun onChangeEnded(
        changeHandler: ControllerChangeHandler,
        changeType: ControllerChangeType
    ) {
        super.onChangeEnded(changeHandler, changeType)

        val username = view?.findViewById<EditText>(R.id.et_username)
        val password = view?.findViewById<EditText>(R.id.et_password)

        showPassword(view)


        // When "Login" button is clicked, the plant_list will inflate from the PlantListController
        view?.findViewById<Button>(R.id.btn_login)?.setOnClickListener {
            if (!username?.text.isNullOrBlank() || !password?.text.isNullOrBlank()){
                val usernameValue = username?.text.toString()
                val passwordValue = password?.text.toString()
                val user = User(usernameValue, passwordValue)


                ApiBuilder.apiCall().login(user)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe {
                        println(it.token)
                        
                    }


                /*router.pushController(
                    RouterTransaction.with(PlantListController(args))
                        .pushChangeHandler(HorizontalChangeHandler())
                        .popChangeHandler(HorizontalChangeHandler())
                )*/
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





fun showPassword(view: View?){
    var isShown = false
    val showButton = view?.findViewById<Button>(R.id.btn_show_hide)
    val password = view?.findViewById<EditText>(R.id.et_password)

    showButton?.setOnClickListener {
        if (isShown){
            password?.inputType = InputType.TYPE_TEXT_VARIATION_PASSWORD or InputType.TYPE_CLASS_TEXT
            showButton.text = "Show"
            password?.setSelection(password.text.length)
            isShown = false
        } else {
            password?.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            showButton.text = "Hide"
            password?.setSelection(password.text.length)
            isShown = true
        }
    }
}