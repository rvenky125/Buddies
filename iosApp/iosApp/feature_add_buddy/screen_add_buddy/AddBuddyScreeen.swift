import SwiftUI
import shared
import JVFloatLabeledTextField

struct AddBuddyScreeen: View {
    @ObservedObject var addBuddyIosVm: AddBuddyIosVm = AddBuddyIosVm()
    let addBuddyNavArgs: AddBuddyNavArgs
    
    init(addBuddyNavArgs: AddBuddyNavArgs) {
        self.addBuddyNavArgs = addBuddyNavArgs
        addBuddyIosVm.viewModel.setPickedLocation(navArgs: addBuddyNavArgs)
        addBuddyIosVm.observeState()
    }
    
    var body: some View {
        VStack(alignment: HorizontalAlignment.leading) {
            Text("Add Photos/Videos").font(.system(.subheadline)).padding(.top, 10)
            
            Text("More about buddy").font(.system(.subheadline)).padding(.top, 10)
            
            TextField("Buddy Name", text: Binding(get: {addBuddyIosVm.state.name}, set: {value in addBuddyIosVm.onEvent(event: AddBuddyEvent.OnNameChange(text: value))})).frame(maxWidth: .infinity)
            TextField("Age",text: Binding(get: {addBuddyIosVm.state.age}, set: {value in addBuddyIosVm.onEvent(event: AddBuddyEvent.OnAgeChange(age: value))})).frame(maxWidth: .infinity, maxHeight: 30)
    
                    TextField("Note",text: Binding(get: {addBuddyIosVm.state.note}, set: {value in addBuddyIosVm.onEvent(event: AddBuddyEvent.OnNoteChange(text: value))})).frame(maxWidth: .infinity, maxHeight: 30)
            
            Spacer()
        }.padding(16)
        
    }
}
