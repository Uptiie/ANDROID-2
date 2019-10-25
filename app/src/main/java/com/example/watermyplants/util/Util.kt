package com.example.watermyplants.util

import android.content.Context
import android.text.InputType
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.watermyplants.R
import org.json.JSONObject

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


fun getUsernameFromToken(token: String?): String{
    val splitToken = token?.split(".")
    val codedData = splitToken?.get(1)
    val decodedByte = android.util.Base64.decode(codedData, android.util.Base64.DEFAULT)
    val decodedString = String(decodedByte)
    val stringToJSON = JSONObject(decodedString)
    return stringToJSON.get("username").toString()
}

fun isNotBlank(name: EditText?, phone: EditText?, pass: EditText?, passConfirm: EditText?): Boolean{
    return !name?.text.isNullOrBlank() && !phone?.text.isNullOrBlank() && !pass?.text.isNullOrBlank() && !passConfirm?.text.isNullOrBlank()
}


fun View.hideKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}