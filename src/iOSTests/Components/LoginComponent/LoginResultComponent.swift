import Foundation
import XCTest
import Catbird

class LoginResultComponent: Component {

    func failed(error: LoginComponent.Errors) {
        let errorMessage = app.staticTexts[error.rawValue]
        waitWithAssert(errorMessage)
    }

    @discardableResult
    func singleLibrary() -> TabBarComponent {
        create(type: TabBarComponent.self)
    }

    func multiLibrary() -> SelectLibraryComponent {
        create(type: SelectLibraryComponent.self)
    }

}
