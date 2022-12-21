package component.profile

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import org.hamcrest.CoreMatchers.allOf
import R

open class ProfileComponent: component.Component() {

    private val goOutLink: String = "Выйти"

    override fun verify(): ProfileComponent {
        onView(withId(R.id.toolbar_profile))
            .check(matches(isCompletelyDisplayed()))
        return this
    }

    fun tapLogout(): ProfileComponent {
        onView(allOf(withId(R.id.tv_title), withText(goOutLink)))
            .perform(click())
        return this
    }

    internal fun selectColorTheme() {
        onView(withId(R.id.chip_dark)).perform(click())
    }
}