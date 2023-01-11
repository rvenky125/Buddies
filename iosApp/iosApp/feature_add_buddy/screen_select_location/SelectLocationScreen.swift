import SwiftUI
import GoogleMaps

struct SelectLocationScreen: View {
    @ObservedObject var viewModel: SelectLocationIosVm = SelectLocationIosVm()
    var body: some View {
        VStack {
            ZStack {
                GoogleMapsView(onEvent: {event in viewModel.onEvent(event: event)}).edgesIgnoringSafeArea(.all)
                
            }
        }
    }
}
