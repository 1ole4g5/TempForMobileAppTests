import Foundation
import Catbird

enum LoginMock: CatbirdMockConvertible {
    case successAsUser
    case successAsManager
    case successAsAdmin
    case successAsUserSeveralLibs
    case unSuccessAsBlockedUser
    case unSuccessAsInvitedUser
    case unSuccessAsNotFoundUser
    case unSuccessAsStoppedUser
    case unSuccessAsUserWithInvalidPassword
    case unSuccessToClosedLib

    var pattern: RequestPattern {
        RequestPattern(method: .POST, url: URL(string: "/auth/login.ajax")!)
    }

    var response: ResponseMock {
        switch self {
        case .successAsUser:
            return ResponseMock(
                status: 200,
                headers: ["Content-Type": "application/json"],
                jsonFile: "loginAsUser.json"
            )

        case .unSuccessAsUserWithInvalidPassword:
            return ResponseMock(
                status: 404,
                headers: ["Content-Type": "application/json"],
                jsonFile: "loginAsUserWithInvalidPassword.json"
            )
        }
    }
}
