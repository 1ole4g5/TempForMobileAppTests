//package ...component.tabBar

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import R
import helpers.waitUntilViewIsDisplayed

class TabBarComponent: ru.mif.library.component.Component() {

    private val booksCollectionsId: Int = R.id.nav_catalog_graph
    private val profileId: Int = R.id.nav_profile
    private val catalogId: Int = R.id.nav_search
    private val myBooksId: Int = R.id.nav_my_books

    override fun verify(): TabBarComponent {
        waitUntilViewIsDisplayed(withId(booksCollectionsId))
        waitUntilViewIsDisplayed(withId(profileId))
        waitUntilViewIsDisplayed(withId(catalogId))
        waitUntilViewIsDisplayed(withId(myBooksId))
        return this
    }

    internal fun selectBooksCollections(): TabBarComponent {
        onView(withId(booksCollectionsId))
            .perform(click())
        return this
    }

    internal fun selectProfile(): TabBarComponent {
        onView(withId(profileId))
            .perform(click())
        return this
    }

    internal fun selectCatalog(): TabBarComponent {
        onView(withId(catalogId))
            .perform(click())
        return this
    }

    internal fun selectMyBooks(): TabBarComponent {
        onView(withId(myBooksId))
            .perform(click())
        return this
    }
}