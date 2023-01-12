import SwiftUI
import UIPilot
import GoogleMaps
import shared

struct SelectLocationScreen: View {
    @ObservedObject var viewModel: SelectLocationIosVm = SelectLocationIosVm()
    @EnvironmentObject var pilot: UIPilot<AppRoute>
    
    var body: some View {
        ZStack(alignment: Alignment.bottom) {
            ZStack(alignment: .center) {
                GoogleMapsView(onEvent: {event in viewModel.onEvent(event: event)}, selectedLocation: viewModel.state.location ?? LocationModel(latitude: 0, longitude: 0)).edgesIgnoringSafeArea(.all)
                Image("map_pin").resizable().frame(width: 30, height: 30,alignment: .center)
                    .aspectRatio(contentMode: .fit)
                    .padding(.bottom, 30)
            }

            VStack {
                Text(viewModel.state.loading ? "Fetching" : "viewModel.state.placeToShow.name").foregroundColor(.white)
                Text(viewModel.state.placeToShow.address)
                    .font(.system(size: 16, weight: .bold, design: .default))
                    .foregroundColor(.gray)
                    .padding(.top, 5)
                Button("Pick this Location", action: {
                    pilot.push(.AddBuddy)
                })
            }.frame(maxWidth: .infinity).background(Color(red: 32/255, green: 36/255, blue: 38/255))
                .modifier(CardModifier())
        }
    }
}
