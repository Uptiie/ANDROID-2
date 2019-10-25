package com.example.watermyplants

import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions

open class UITestBase {

    fun commonViewAction(text: String): Array<ViewAction> {
        return arrayOf(
            ViewActions.clearText(),
            ViewActions.typeText(text),
            ViewActions.closeSoftKeyboard()
        )
    }
}