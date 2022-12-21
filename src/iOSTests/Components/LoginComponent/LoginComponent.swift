import Foundation
import XCTest
import Catbird

class LoginComponent: Component, TabBarAccessible {

    enum Elements: String {
        case navigationBarTitle = "Вход"
        case emailTextField = "Рабочая почта"
        case passwordTextField = "Пароль"
        case regisatrtionButton = "Регистрация"
    }

    enum ElementsId: String {
        case loginButton = "auth_login_button_id"
    }

    enum TestData: String {
        case emailField = "email"
        case passwordField = "password"
    }

    enum Errors: String {
        case notFound = "Неверная эл. почта или пароль."
        case libraryClosed = "Библиотека закрыта, потому что закончился срок действия контракта"
        case banned = "Аккаунт заблокирован. Обратитесь в поддержку МИФа: reply-lib@miflib.ru"
        case suspended = "На вашу почту отправлена ссылка для входа в библиотеку. Перейдите по ней"
    }

    override func validate() {
        waitWithAssert(app.navigationBars[Elements.navigationBarTitle.rawValue])
    }

    func enterEmail(_ email: String) -> Self {
        let emailTextField = app.scrollViews.otherElements.textFields[Elements.emailTextField.rawValue]
        waitWithAssert(emailTextField)
        emailTextField.tap()
        emailTextField.typeText(email)
        return self
    }

    func enterPassword(_ password: String) -> Self {
        let passwordTextField = app.scrollViews.otherElements.secureTextFields[Elements.passwordTextField.rawValue]
        waitWithAssert(passwordTextField)
        passwordTextField.tap()
        passwordTextField.typeText(password)
        return self
    }

    func tapLoginButton() -> LoginResultComponent {
        let loginButton = app.scrollViews.otherElements.buttons[ElementsId.loginButton.rawValue]
        waitWithAssert(loginButton)
        loginButton.tap()
        return create(type: LoginResultComponent.self)
    }

    func login(email: String, password: String) -> LoginResultComponent {
        enterEmail(email)
            .enterPassword(password)
            .tapLoginButton()
    }

    func loginWithDefaultUser() -> LoginResultComponent {
        login(email: TestData.emailField.rawValue, password: TestData.passwordField.rawValue)
    }

    func stubbedLoginWithDefaultUser() -> Self {
        stub(LoginMock.successAsUser)
            .loginWithDefaultUser()
            .singleLibrary()
        return self
    }

}
