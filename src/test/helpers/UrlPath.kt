package helpers

// auth
const val AUTH_TOKEN_REFRESH_PATH = "auth/refreshJwt?refreshToken=$REFRESH_TOKEN"
const val AUTH_TOKEN_ACTIVATE_PATH = "$AUTH_ACTIVATE?token="
const val AUTH_CHECK_CODE_PATH = "$AUTH_CHECK_CODE?code=test&email=test%40test"