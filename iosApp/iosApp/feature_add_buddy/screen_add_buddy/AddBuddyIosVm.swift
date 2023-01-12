import Foundation
import shared

class AddBuddyIosVm: ObservableObject {
    let viewModel = ViewModels().addBuddyViewModel
    
    @Published var state = AddBuddyState(loading: false, files: [], note: "", name: "", showMap: false, age: "", gender: nil)
    
    func onEvent(event: AddBuddyEvent) {
        viewModel.onEvent(event: event)
    }
    
    func observeState() {
        viewModel.state.subscribe(onCollect: { [weak self] state in
            if let state {
                self?.state = state
            }
        })
    }
}
