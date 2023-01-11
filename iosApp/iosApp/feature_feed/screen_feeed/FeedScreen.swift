import SwiftUI
import UIPilot

struct FeedScreen: View {
    @ObservedObject var viewModel = FeedScreenIosVm()
    @EnvironmentObject var pilot: UIPilot<AppRoute>
    
    init() {
        viewModel.observeState()
    }
    
    var body: some View {
        LazyVStack {
            ForEach(viewModel.state.buddies, id: \.self) { buddy in
                Button("Placeholder \(buddy.name)") {
                    pilot.push(.SelectLocation)
                }
            }.frame(width: .infinity, height: .infinity)
        }
    }
}

//
//struct FeedScreen_Preview: PreviewProvider {
//    static var previews: some View {
//        FeedScreen()
//    }
//}
