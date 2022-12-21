//package ...helpers

import androidx.test.platform.app.InstrumentationRegistry

private fun setNetworkTimeout() {
    setTimeout(5000)
}

fun disableNetworkConnection() {
    setNetworkTimeout()
    InstrumentationRegistry.getInstrumentation().uiAutomation
        .executeShellCommand("svc wifi disable")
    InstrumentationRegistry.getInstrumentation().uiAutomation
        .executeShellCommand("svc data disable")
    setNetworkTimeout()
}

fun enableNetworkConnection() {
    setNetworkTimeout()
    InstrumentationRegistry.getInstrumentation().uiAutomation
        .executeShellCommand("svc wifi enable")
    setNetworkTimeout()
}