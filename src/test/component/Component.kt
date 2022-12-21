//package ...component

import android.view.View
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import org.hamcrest.Matcher
//import ...helpers.waitUntilViewIsDisplayed

open class Component {

    inline fun <reified T : Component> on(): T {
        val page = T::class.constructors.first().call()
        page.verify()
        return page
    }

    open fun verify(): Component {
        return this
    }

    companion object {
        inline fun <reified T : Component> on(): T {
            return Component().on()
        }
    }

    fun checkThat(matcher: Matcher<View>, assertionMatcher: Matcher<View>) {
        waitUntilViewIsDisplayed(matcher)
        Espresso.onView(matcher).check(ViewAssertions.matches(assertionMatcher))
    }

    fun sendText(matcher: Matcher<View>, string: String) {
        waitUntilViewIsDisplayed(matcher)
        Espresso.onView(matcher).perform(ViewActions.typeText(string), ViewActions.click())
    }

    fun clickOn(matcher: Matcher<View>) {
        waitUntilViewIsDisplayed(matcher)
        Espresso.onView(matcher).perform(ViewActions.click())
    }

    fun checkMessage(matcher: Matcher<View>, assertionMatcher: Matcher<View>): Component {
        checkThat(matcher, assertionMatcher)
        return this
    }

    internal fun pressBackButton() {
        Espresso.onView(ViewMatchers.isRoot()).perform(ViewActions.pressBack())
    }
}