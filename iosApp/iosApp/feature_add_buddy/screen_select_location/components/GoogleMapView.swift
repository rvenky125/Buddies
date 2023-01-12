import SwiftUI
import GoogleMaps
import shared

struct GoogleMapsView: UIViewRepresentable {
    var onEvent: (SelectLocationEvent) -> ()
    @State var selectedLocation: LocationModel
//
//    init?(onEvent: @escaping (SelectLocationEvent) -> Void, selectedLocation: LocationModel) {
//        self.onEvent = onEvent
//        self.selectedLocation = selectedLocation
//    }
//
//
//    required init?(coder: NSCoder) {
//        fatalError("init(coder:) has not been implemented")
//    }
//
//    override func loadView() {
//      super.loadView()
//      let camera = GMSCameraPosition.camera(
//        withLatitude: 1.285,
//        longitude: 103.848,
//        zoom: 12
//      )
//      let mapView = GMSMapView.map(withFrame: .zero, camera: camera)
//      mapView.delegate = self
//      self.view = mapView
//    }
//
//    private let zoom: Float = 15.0
//    let marker = GMSMarker(position: CLLocationCoordinate2D(latitude: 10, longitude: 10))
    
    func makeUIView(context: Self.Context) -> GMSMapView {
        let camera = GMSCameraPosition.camera(withLatitude: -33.86, longitude: 151.20, zoom: 6.0)
        let mapView = GMSMapView.map(withFrame: CGRect.zero, camera: camera)
        mapView.delegate = context.coordinator
//        marker.position = camera.target
//        marker.map = mapView1
        return mapView
    }
    
    func updateUIView(_ mapView: GMSMapView, context: Context) {

    }
    
    func makeCoordinator() -> Coordinator {
        Coordinator(self)
    }

    class Coordinator: NSObject, GMSMapViewDelegate {
        var parent: GoogleMapsView

        init(_ parent: GoogleMapsView) {
            self.parent = parent
        }
        
        func mapView(_ mapView: GMSMapView, idleAt position: GMSCameraPosition) {
            parent.onEvent(SelectLocationEvent.OnChangeLocation(location: LocationModel(latitude: position.target.latitude, longitude: position.target.longitude)))
        }
        
        func mapView(_ mapView: GMSMapView, didChange position: GMSCameraPosition) {
            print("Changed camera position")
            parent.onEvent(SelectLocationEvent.OnChangeLocation(location: LocationModel(latitude: position.target.latitude, longitude: position.target.longitude)))
        }

    }

}
