import SwiftUI
import shared
import UIKit
import UIPilot

struct ContentView: View {
    @StateObject var pilot = UIPilot(initial: AppRoute.Feed)
    
    var body: some View {
        UIPilotHost(pilot) { route in
            switch route {
                case .Feed: FeedScreen().navigationTitle("Buddies")
                case .SelectLocation: SelectLocationScreen().navigationTitle("Buddy Location")
                case .AddBuddy: AddBuddyScreeen().navigationTitle("Add Buddy")
            }
        }
    }
}
