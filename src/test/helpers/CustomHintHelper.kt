//package ...helpers

import android.view.View
import android.widget.EditText
import androidx.test.espresso.matcher.BoundedMatcher
import com.google.common.base.Preconditions
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.Description
import org.hamcrest.Matcher


object Matchers {
    fun withItemHint(hintText: String?): BoundedMatcher<View?, EditText> {
        // use preconditions to fail fast when a test is creating an invalid matcher.
        Preconditions.checkArgument(hintText != null)
        return withItemHint(`is`(hintText))
    }

    private fun withItemHint(matcherText: Matcher<String?>): BoundedMatcher<View?, EditText> {
        // use preconditions to fail fast when a test is creating an invalid matcher.
        Preconditions.checkNotNull<Any>(matcherText)
        return object : BoundedMatcher<View?, EditText>(EditText::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("with item hint: $matcherText")
            }

            override fun matchesSafely(editTextField: EditText): Boolean {
                return matcherText.matches(editTextField.hint.toString())
            }
        }
    }
}