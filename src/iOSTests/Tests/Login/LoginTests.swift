import Foundation

class LoginTests: ComponentTests {

    func testLoginSuccessAsUser() throws {
        start
            .stub(LoginMock.successAsUser)
            .loginWithDefaultUser()
            .singleLibrary()
    }

    func testLoginInvalidEmail() throws {
        start
            .stub(LoginMock.unSuccessAsUserWithInvalidPassword)
            .loginWithDefaultUser()
            .failed(error: .notFound)
    }

}
