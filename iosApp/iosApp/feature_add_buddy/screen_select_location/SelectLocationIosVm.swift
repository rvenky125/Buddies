import Foundation
import shared


class SelectLocationIosVm : ObservableObject {
    let viewModel = ViewModels().selectLocationVM
    
    @Published var state = SelectLocationState(loading: false, queryValue: "", placeToShow: PlaceToShow(name: "", address: ""), places: [], location: nil, selectedPlace: nil)
    
    func onEvent(event: SelectLocationEvent) {
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
