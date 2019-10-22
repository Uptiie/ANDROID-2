package com.example.watermyplants.util

import android.content.Context
import android.text.InputType
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.watermyplants.R

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

fun Context.showToast(string: String){
    Toast.makeText(this, string, Toast.LENGTH_SHORT).show()
}