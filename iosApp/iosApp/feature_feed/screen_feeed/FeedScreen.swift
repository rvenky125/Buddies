import SwiftUI
import UIPilot

struct FeedScreen: View {
    @ObservedObject var viewModel = FeedScreenIosVm()
    @EnvironmentObject var pilot: UIPilot<AppRoute>
    
    init() {
        viewModel.observeState()
    }
    
    var body: some View {
        List {
            ForEach(viewModel.state.buddies, id: \.self) { buddy in
                ZStack {
                    BuddyItemCard(buddy: buddy)

                }
            }
        }.listStyle(PlainListStyle())
    }
}

//
//struct FeedScreen_Preview: PreviewProvider {
//    static var previews: some View {
//        FeedScreen()
//    }
//}
