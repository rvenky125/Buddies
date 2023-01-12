import SwiftUI
import UIPilot
import GoogleMaps
import shared

struct SelectLocationScreen: View {
    @ObservedObject var viewModel: SelectLocationIosVm = SelectLocationIosVm()
    @EnvironmentObject var pilot: UIPilot<AppRoute>
    
    init() {
        viewModel.observeState()
    }
    
    var body: some View {
        ZStack(alignment: Alignment.bottom) {
            ZStack(alignment: .center) {
                GoogleMapsView(onEvent: {event in viewModel.onEvent(event: event)}, selectedLocation: viewModel.state.location ?? LocationModel(latitude: 0, longitude: 0)).edgesIgnoringSafeArea(.all)
                Image("map_pin").resizable().frame(width: 30, height: 30,alignment: .center)
                    .aspectRatio(contentMode: .fit)
                    .padding(.bottom, 30)
            }
            
            ZStack {VStack {
                Text(viewModel.state.loading ? "Fetching" : viewModel.state.placeToShow.address).foregroundColor(.white)
                Button("Pick this Location", action: {
                    pilot.push(.AddBuddy(args: AddBuddyNavArgs(location: viewModel.state.location ?? LocationModel(latitude: 0, longitude: 0), place: viewModel.state.placeToShow)))
                }).padding(.top, 20).padding(.bottom, 10)
                
            }.padding()}.frame(maxWidth: .infinity).background(Color(red: 32/255, green: 36/255, blue: 38/255))
                .modifier(CardModifier())
        }
    }
}
