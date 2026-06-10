CampsiteCommander 

An Android app for campers to add, save, and view their gear checklist. Built with Kotlin + Material Design. 

Features 

Add Gear: Enter gear name, category, quantity, and comments 

View Packed List: See all added gear in a Material Card RecyclerView 

Data Persistence: Gear list saves automatically using SharedPreferences + Gson. Data stays after app closes 

Dark Theme UI: Easy on eyes for night camping planning 

Back Navigation: Return from packed list to add more gear 

Tech Stack 

Language: Kotlin 

UI: XML Layouts + Material Design Components 

Storage: SharedPreferences with Gson for ArrayList serialization 

Min SDK: 24 

Target SDK: 34 

How to Run 

Open project in Android Studio 

Sync Gradle: implementation 'com.google.code.gson:gson:2.10.1' 

Build → Run on emulator or physical device 

App Flow 

User enters gear details → Tap "ADD GEAR" 

Total count updates at bottom  

Tap "VIEW PACKED LIST" → Opens DetailActivity with RecyclerView 

Tap "Back to Add Gear" → Returns to main screen 

Close/reopen app → Gear list persists 

Screenshots 
Main Activity - Add Gear Screen
![Main Screen Splash](app/src/main/res/screenshots/mainscreensplash.png)

### Detail Activity - Packing List with Swipe Delete
![Packing List](app/src/main/res/screenshots/packinglist.png)

Add your app screenshots here after running: 

Main screen with input fields 

Packed gear list with Material Cards 

Author 

Nelly Mgijima ST10533474 

CampsiteCommander v1.0 - April 2026 

Future Improvements 

Swipe to delete individual items 

Edit gear details 

Sort by category 

Export list to PDF/CSV 

 
