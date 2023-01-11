import SwiftUI
import shared
import GoogleMaps

@main
struct iOSApp: App {
	var body: some Scene {
		WindowGroup {
			ContentView()
		}
	}
    
    init() {
        GMSServices.provideAPIKey("AIzaSyDMUBcXRG08RcGle0no7luNMqiozxyrH6k")
        HelpersKt.doInitKoin()
    }
}
