import SwiftUI
import GoogleMaps
import shared

struct GoogleMapsView: UIViewRepresentable {
    let onEvent: (SelectLocationEvent) -> ()
    
    private let zoom: Float = 15.0
    let marker = GMSMarker(position: CLLocationCoordinate2D(latitude: 10, longitude: 10))
    
    func makeUIView(context: Self.Context) -> GMSMapView {
        let camera = GMSCameraPosition.camera(withLatitude: -33.86, longitude: 151.20, zoom: 6.0)
        let mapView = GMSMapView.map(withFrame: CGRect.zero, camera: camera)
        marker.position = camera.target
        marker.map = mapView
        return mapView
    }
    
    func updateUIView(_ mapView: GMSMapView, context: Context) {
        
    }
    
    func makeCoordinator() -> Coordinator {
        Coordinator(owner: self, onEvent: onEvent)
    }
    
    class Coordinator: NSObject, GMSMapViewDelegate {
       let owner: GoogleMapsView       // access to owner view members,
        let onEvent: (SelectLocationEvent) -> ()


        init(owner: GoogleMapsView, onEvent: @escaping (SelectLocationEvent) -> ()) {
         self.owner = owner
           self.onEvent = onEvent
       }
        
        func mapView(_ mapView: GMSMapView, didChange position: GMSCameraPosition) {
            onEvent(SelectLocationEvent.OnChangeLocation(location: LocationModel(latitude: position.target.latitude, longitude: position.target.longitude)))
        }
    }
}
