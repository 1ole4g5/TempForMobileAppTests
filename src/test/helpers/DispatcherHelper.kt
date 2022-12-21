package helpers

import GET_BY_IDS
import LIST_BOOK_PROGRESS
import NEXT_BOOK_PROGRESS
import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest
import okio.Buffer
import okio.buffer
import okio.source
import java.io.InputStream


fun String.toResponse(code: Int): MockResponse {
    return MockResponse()
        .setResponseCode(code)
        .setBody(this)
}

internal val userAuthDispatcher: Dispatcher = object : Dispatcher() {
    @Throws(InterruptedException::class)
    override fun dispatch(request: RecordedRequest): MockResponse {
        return when {
            request.path.isRequestContains(AUTH_LOGIN) -> SUCCESS_AS_USER_ROLE.toResponse(OK)
            request.path.isRequestContains(USER_LIBRARY) -> LIBRARY_AJAX.toResponse(OK)
            request.path.isRequestContains(BOOKS_WISHLIST) -> WITH_OUT_BOOKS_IN_WISH_LIST
                .toResponse(OK)

            request.path.isRequestContains(BOOK_PROGRESS_LIST_OF_BOOKS_IDS_PATH) ->
                EMPTY_BOOK_PROGRESS.toResponse(OK)

            request.path.isRequestContains(BOOK_PROGRESS_LIST_ID_LIMIT_10) -> EMPTY_BOOK_PROGRESS
                .toResponse(OK)

            request.path.isRequestContains(BOOK_PROGRESS_SET_LIST) -> SET_LIST.toResponse(OK)
            else -> MockResponse().setResponseCode(NOT_FOUND)
        }
    }
}
}