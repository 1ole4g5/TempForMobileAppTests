import Foundation
import Catbird

extension Catbird {

    static var localHostDomain = URL(string: "http://localhost:8080")!

    static func onLocalhost() -> Catbird {
        return Catbird(url: localHostDomain)
    }

}
