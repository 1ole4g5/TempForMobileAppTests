import Foundation
import XCTest
import Catbird

class ComponentTests: XCTestCase {

    internal let catbird = Catbird.onLocalhost()
    internal var app: XCUIApplication!
    internal lazy var start = LoginComponent(application: app, catbird: catbird)

    override func setUp() {
        super.setUp()
        continueAfterFailure = true
        app = XCUIApplication()
        app.launchEnvironment = ["backend_url": catbird.url.absoluteString, "force_logout": "true"]
        app.launch()
    }

    override func tearDown() {
        XCTAssertNoThrow(try catbird.send(.removeAll), "Remove all requests")
        super.tearDown()
    }

}
