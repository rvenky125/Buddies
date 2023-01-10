import SwiftUI
import shared
import SwiftUIRouter

struct ContentView: View {
    var body: some View {
        Router {
            Route("feed") {
                FeedScreen()
            }
            
            Route("select_location") {
                SelectLocationScreen()
            }
            
            Route("add_buddy") {
                AddBuddyScreeen()
            }
        }
    }
}
