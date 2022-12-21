
import android.util.Log
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import core.analytics.events.CoreParam
import core.analytics.tracker.event.TrackerEvent
import core.common.toLogTag
import analytics.TestAnalyticsSystem
import analytics.events.LibraryEvent
import analytics.events.LibraryParam.DOMAIN
import analytics.events.LibraryParam.READER_THEME

class ProfileTests : BaseTests() {

    @Nested
    inner class Profile {
        @BeforeEach
        fun setUp() {
            setupMockServerWithDispatcher(dispatcher = userAuthDispatcher)

            on<LoginComponent>().loginWithDefaultUser()
            on<TabBarComponent>().selectProfile()
            on<ProfileComponent>().tapLogout()
        }

        @Test
        fun testLogin() {
            on<LogoutAlertComponent>().confirm()
            on<LoginComponent>().verify()
        }

        @Test
        fun testCancelLogout() {
            on<LogoutAlertComponent>().cancel()
            on<ProfileComponent>().verify()
        }
    }

    @Nested
    inner class SelectItems {
        @BeforeEach
        fun preCondition() {
            setupMockServerWithDispatcher(dispatcher = userAuthDispatcher)

            on<LoginComponent>().loginWithDefaultUser()
            on<TabBarComponent>().selectProfile()
        }

        @Test
        fun testSelectWriteToSupport() {
            on<ProfileComponent>().selectWriteToSupport()

            assert(
                TestAnalyticsSystem.events.contains(
                    TrackerEvent(LibraryEvent.Action.PROFILE_SUPPORT)
                        .withValues(DOMAIN, LIB_DOMAIN)
                )
            )
        }
    }
}