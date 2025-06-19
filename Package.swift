// swift-tools-version: 5.9
import PackageDescription

let package = Package(
    name: "AntiFakeGps",
    platforms: [.iOS(.v14)],
    products: [
        .library(
            name: "AntiFakeGps",
            targets: ["AntiFakeGpsPlugin"])
    ],
    dependencies: [
        .package(url: "https://github.com/ionic-team/capacitor-swift-pm.git", from: "7.0.0")
    ],
    targets: [
        .target(
            name: "AntiFakeGpsPlugin",
            dependencies: [
                .product(name: "Capacitor", package: "capacitor-swift-pm"),
                .product(name: "Cordova", package: "capacitor-swift-pm")
            ],
            path: "ios/Sources/AntiFakeGpsPlugin"),
        .testTarget(
            name: "AntiFakeGpsPluginTests",
            dependencies: ["AntiFakeGpsPlugin"],
            path: "ios/Tests/AntiFakeGpsPluginTests")
    ]
)