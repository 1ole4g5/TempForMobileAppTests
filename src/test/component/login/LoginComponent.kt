package component.login

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import R
import helpers.waitUntilViewIsDisplayed

class LoginComponent: component.Component() {

    private val emailField: String = "email"
    private val passwordField: String = "password"

    override fun verify(): ru.mif.library.component.Component {
        waitUntilViewIsDisplayed(withId(R.id.et_lib_login))
        onView(withText(R.string.lib_auth_title))
            .check(matches(isCompletelyDisplayed()))
        return this
    }

    private fun enterEmail(email: String): LoginComponent {
        sendText(withId(R.id.et_lib_login), email)
        return this
    }

    private fun enterPassword(password: String): LoginComponent {
        sendText(withId(R.id.et_lib_pass), password)
        return this
    }

    private fun tapLoginButton() {
        clickOn(withId(R.id.btn_login))
    }

    private fun login(email: String, password: String) {
        enterEmail(email)
            .enterPassword(password)
                .tapLoginButton()
    }

    internal fun loginWithDefaultUser(): LoginComponent {
        login(emailField, passwordField)
        return this
    }

    internal fun checkErrorMsg(errorText: String): LoginComponent {
        checkThat(withId(R.id.snackbar_text), withText(errorText))
        return this
    }
}