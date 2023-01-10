import Foundation
import shared


class FeedScreenIosVm: ObservableObject {
    let feedScreenVm = ViewModels().feedViewModel
    
    @Published var state = FeedState(loading: false, buddies: [])
    
//    func onEvent(event: FeedEvent) {
//        feedScreenVm.onEvent(event: event)
//    }
    
    func observeState() {
        feedScreenVm.state.subscribe(onCollect: { [weak self] state in
            if let state {
                self?.state = state
            }
        })
    }
}
