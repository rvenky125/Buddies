import SwiftUI
import shared
import UIKit
import UIPilot

struct ContentView: View {
    @StateObject var pilot = UIPilot(initial: AppRoute.Feed)
    
    var body: some View {
        UIPilotHost(pilot) { route in
            switch route {
                case .Feed: FeedScreen().navigationTitle("Buddies").toolbar {
                    ToolbarItem(placement: .primaryAction) {
                        Button("Add Buddy", action: {
                            pilot.push(.SelectLocation)
                        })
                    }
                }
                case .SelectLocation: SelectLocationScreen().navigationTitle("Buddy Location")
            case .AddBuddy(let addBuddyNavArgs): AddBuddyScreeen(addBuddyNavArgs: addBuddyNavArgs).navigationTitle("Add Buddy")
            }
        }
    }
}
