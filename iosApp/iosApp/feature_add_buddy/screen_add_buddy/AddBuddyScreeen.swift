import SwiftUI
import shared
import AwaitToast
import UIPilot

struct AddBuddyScreeen: View {
    @ObservedObject var addBuddyIosVm: AddBuddyIosVm = AddBuddyIosVm()
    let addBuddyNavArgs: AddBuddyNavArgs
    
    @EnvironmentObject var pilot: UIPilot<AppRoute>


    @State private var isSheetPresented = false
    
    init(addBuddyNavArgs: AddBuddyNavArgs) {
        self.addBuddyNavArgs = addBuddyNavArgs
        addBuddyIosVm.viewModel.setPickedLocation(navArgs: addBuddyNavArgs)
        addBuddyIosVm.observeState()
    }
    
    var body: some View {
        VStack(alignment: HorizontalAlignment.leading) {
            Text("Add Photos/Videos").font(.system(.subheadline)).padding(.top, 10)
            
            Button("Add Image", action: {
                isSheetPresented = true
            }).padding(.top, 10)
            LazyHGrid(rows: [GridItem(.fixed(100))], content: {
                ForEach(addBuddyIosVm.state.files, id: \.self) { file in
                    Image(uiImage: file).resizable().frame(width: 100, height: 100)
                }
            }).padding(.top, 10)
            
            Form {
                Section(header: Text("More details of buddy")) {
                                    
                    TextField("Buddy Name", text: Binding(get: {addBuddyIosVm.state.name}, set: {value in addBuddyIosVm.onEvent(event: AddBuddyEvent.OnNameChange(text: value))})).frame(maxWidth: .infinity)
                    TextField("Age in years(ex: 2.6)",text: Binding(get: {addBuddyIosVm.state.age}, set: {value in addBuddyIosVm.onEvent(event: AddBuddyEvent.OnAgeChange(age: value))})).frame(maxWidth: .infinity, maxHeight: 50)
            
                    Picker("Gender", selection: Binding(get: {addBuddyIosVm.state.gender ?? Gender.select}, set: {value in
                        addBuddyIosVm.onEvent(event: AddBuddyEvent.OnGenderChange(gender: value))
                    })) {
                        ForEach([Gender.male, Gender.female, Gender.other], id: \.self) { gender in
                            Text(gender.name).tag(gender)
                        }
                    }
                    TextField("Note",text: Binding(get: {addBuddyIosVm.state.note}, set: {value in addBuddyIosVm.onEvent(event: AddBuddyEvent.OnNoteChange(text: value))})).frame(maxWidth: .infinity, maxHeight: 50)
                }
                
                Button(action: {
                    if (addBuddyIosVm.state.name.isEmpty && addBuddyIosVm.state.note.isEmpty && addBuddyIosVm.state.age.isEmpty) {
                        let toast = Toast.default(text: "Please provide all fields")
                        toast.show()
                        DispatchQueue.main.asyncAfter(deadline: .now() + 1.0, execute: {
                            toast.dismiss()
                        })
                        return
                    }
                    
                    addBuddyIosVm.onEvent(event: AddBuddyEvent.OnSubmit())
                }) {
                    HStack {
                            Spacer()
                            Text("Submit")
                            Spacer()
                          }
                }
            }
            
            Spacer()
        }.padding(16)
            .sheet(isPresented: $isSheetPresented) {
                ImagePicker(onSelectImage: {image in
                    addBuddyIosVm.onEvent(event: AddBuddyEvent.OnAddFiles(files: Array(arrayLiteral: image as UIImage?)))
                })
            }
        
    }
}
