import SwiftUI

struct FeedScreen: View {
    @ObservedObject var viewModel = FeedScreenIosVm()
    
    init() {
        viewModel.observeState()
    }
    
    var body: some View {
        LazyVStack {
            ForEach(viewModel.state.buddies, id: \.self) { buddy in
                Text("Placeholder \(buddy.name)")
            }
        }.frame(width: .infinity, height: .infinity)
    }
}

//
//struct FeedScreen_Preview: PreviewProvider {
//    static var previews: some View {
//        FeedScreen()
//    }
//}
