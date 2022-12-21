//package ...tests


import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import de.mannodermaus.junit5.ActivityScenarioExtension
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockWebServer
import okio.IOException
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.extension.RegisterExtension
//import ...activity.MainActivity


open class BaseTests {
    private val mockWebServer = MockWebServer()
    private val mockServerPort = 8888

    @JvmField
    @RegisterExtension
    val scenarioExtension = ActivityScenarioExtension.launch<MainActivity>()

    @Throws(IOException::class, InterruptedException::class)
    fun setupMockServerWithDispatcher(dispatcher: Dispatcher) {
        setupDispatcher(dispatcher)
        mockWebServer.start(mockServerPort)
    }

    internal fun setupDispatcher(dispatcher: Dispatcher) {
        mockWebServer.dispatcher = dispatcher
    }

    @AfterEach
    @Throws(IOException::class)
    fun tearDown() {
        scenarioExtension.scenario.close()
        mockWebServer.shutdown()
    }
}
