import SwiftUI
import shared
import SDWebImageSwiftUI

struct BuddyItemCard: View {
    
    let buddy: Buddy
    
        init(buddy: Buddy) {
            self.buddy = buddy
        }
    
    var body: some View {
        HStack(alignment: .top) {
            WebImage(url: URL(string: buddy.files[0]))
                .resizable()
                .aspectRatio(contentMode: .fill)
                .frame(width: 150, height: 150)
                .padding(.all, 20)
            
            VStack(alignment: .leading) {
                Text(buddy.name)
                    .font(.system(size: 26, weight: .bold, design: .default))
                    .foregroundColor(.white)
                Text(buddy.address)
                    .font(.system(size: 16, weight: .bold, design: .default))
                    .foregroundColor(.gray)
                    .padding(.top, 5)
                HStack {
                    Text(buddy.gender + ", age: " + String(buddy.age) + " years")
                        .font(.system(size: 16, weight: .bold, design: .default))
                        .foregroundColor(.white)
                        .padding(.top, 5)
                }
            }.padding(.trailing, 20).padding(.vertical, 14)
            Spacer()
        }
        .frame(maxWidth: .infinity, alignment: .center)
        .background(Color(red: 32/255, green: 36/255, blue: 38/255))
        .modifier(CardModifier())
        .padding(.all, 10)
    }
}

//struct BuddyItemCard: View {
//
//    let buddy: BuddyDto
//
//    init(buddy: BuddyDto) {
//        self.buddy = buddy
//    }
//
//    var body: some View {
//            HStack {
//                WebImage(url: URL(string: buddy.files[0]))
//                    .resizable()
//                    .placeholder {
//                        Rectangle().foregroundColor(.white)
//                    }
//                    .indicator(.activity)
//                    .transition(.fade(duration: 0.5))
//                    .frame(width: UIScreen.main.bounds.width * 0.75, height: 250) // 2
//                    .clipped() // 3
//                VStack {
//                    Text("Text")
//                        .foregroundColor(.black)
//                    Spacer()
//                }.frame(maxWidth: .infinity)
//            }
////            .frame(width: metrics.size.width)
//            .background(Color.white)
//            .cornerRadius(8)
//            .shadow(radius: 5)
//    }
//}


struct CardModifier: ViewModifier {
    func body(content: Content) -> some View {
        content
            .cornerRadius(20)
            .shadow(color: Color.black.opacity(0.2), radius: 20, x: 0, y: 0)
    }
    
}
