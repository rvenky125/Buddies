import Foundation
import shared

// Define routes of the app
enum AppRoute: Equatable {
    case Feed
    case SelectLocation // Typesafe parameters
    case AddBuddy(args: AddBuddyNavArgs)
}
