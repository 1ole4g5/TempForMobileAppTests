import Foundation
import Catbird
import XCTest

public extension ResponseMock {

    class ResponseMockBundleFinder {}

    init(status: Int = 200, headers: [String: String] = [:], anyFile: String? = nil, limit: Int? = nil, delay: Int? = nil) {
        let fileNameElements = anyFile?.split(separator: ".")
        let fileName = fileNameElements?.dropLast().joined(separator: ".")
        let fileType = fileNameElements?.last.map(String.init)

        var data: Data?
        if let url = Bundle(for: ResponseMockBundleFinder.self).url(forResource: fileName, withExtension: fileType) {
            XCTAssertNoThrow(data = try Data(contentsOf: url))
        } else if let anyFile = anyFile {
            XCTAssert(false, "Error: Can't load file \(anyFile)")
        }

        self.init(status: status, headers: headers, body: data, limit: limit, delay: delay)
    }

    static func successJson(file: String) -> ResponseMock {
        return ResponseMock(status: 200, headers: ["Content-Type": "application/json"], anyFile: file)
    }

    static func badRequestJson(file: String) -> ResponseMock {
        return ResponseMock(status: 400, headers: ["Content-Type": "application/json"], anyFile: file)
    }

    static func forbiddenJson(file: String) -> ResponseMock {
        return ResponseMock(status: 403, headers: ["Content-Type": "application/json"], anyFile: file)
    }

    static func notFoundJson(file: String) -> ResponseMock {
        return ResponseMock(status: 404, headers: ["Content-Type": "application/json"], anyFile: file)
    }

    static func internalServerErrorJson(file: String) -> ResponseMock {
        return ResponseMock(status: 500, headers: ["Content-Type": "application/json"], anyFile: file)
    }

    static func successImage(file: String) -> ResponseMock {
        return ResponseMock(status: 200, headers: ["Content-Type": "image/png"], anyFile: file)
    }

    static func successAudio(file: String) -> ResponseMock {
        return ResponseMock(status: 200, headers: ["Content-Type": "audio-mpeg"], anyFile: file)
    }

    static func successText(file: String) -> ResponseMock {
        return ResponseMock(status: 200, headers: ["Content-Type": "text/html"], anyFile: file)
    }

    static func internalServerErrorText(file: String) -> ResponseMock {
        return ResponseMock(status: 500, headers: ["Content-Type": "text/html"], anyFile: file)
    }

}
