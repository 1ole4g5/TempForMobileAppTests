//package ...helpers

import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import org.hamcrest.TypeSafeMatcher

fun closeButton(
    firstMatchers: Matcher<View>,
    firstPosition: Int,
    secondMatchers: Matcher<View>,
    secondPosition: Int
) {
    val appCompatImageButton = Espresso.onView(
        Matchers.allOf(
            childAtPosition(
                Matchers.allOf(
                    firstMatchers,
                    childAtPosition(
                        secondMatchers,
                        secondPosition
                    )
                ),
                firstPosition
            ),
            ViewMatchers.isDisplayed()
        )
    )
    appCompatImageButton.perform(ViewActions.click())
}

fun childAtPosition(
    parentMatcher: Matcher<View>, position: Int
): Matcher<View> {

    return object : TypeSafeMatcher<View>() {
        override fun describeTo(description: Description) {
            description.appendText("Child at position $position in parent ")
            parentMatcher.describeTo(description)
        }

        public override fun matchesSafely(view: View): Boolean {
            val parent = view.parent
            return parent is ViewGroup && parentMatcher.matches(parent)
                    && view == parent.getChildAt(position)
        }
    }
}