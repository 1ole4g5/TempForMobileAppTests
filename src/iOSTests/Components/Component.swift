import Foundation
import XCTest
import Catbird

class Component {

    let app: XCUIApplication
    let catbird: Catbird

    internal let waitForExistence: TimeInterval = 15

    required init(application: XCUIApplication, catbird: Catbird) {
        self.app = application
        self.catbird = catbird
        validate()
    }

    func validate() {}

    @discardableResult
    func stub(_ mock: CatbirdMockConvertible) -> Self {
        XCTAssertNoThrow(try catbird.send(.add(mock)))
        return self
    }

    internal func waitWithAssert(_ element: XCUIElement) {
        XCTAssertTrue(element.waitForExistence(timeout: waitForExistence))
    }

    internal func waitWithFalseAssert(_ element: XCUIElement) {
        XCTAssertFalse(element.waitForExistence(timeout: waitForExistence))
    }

    internal func create<T>(type: T.Type) -> T where T: Component {
        T(application: app, catbird: catbird)
    }

}
