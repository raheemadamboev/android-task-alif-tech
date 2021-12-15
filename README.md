# android-task-alif-tech

This is a task given by Alif Tech company for an Android developer position.

## Task

Create an Android application that displays information received over the network.

1. Get and print the data received from the above URL.
2. Parse the data received from the server into a list of Java / Kotlin objects.
3. Display your objects in an organized order (ListView, GridView, etc.).
4. Name, City, State and End Date should be displayed.
5. Also, the object name should reflect the image displayed in the url of each object.
6. On the main screen, load and display only the first 3 items of data. Implement "continuous (lazy) loading" while the user scrolls through the list and increases the number of list items by 3.
7. For every new download of data, save it to the local database.
8. When the item is clicked, open the item details (item details URI present in the JSON data) in internal webview.
9. Apply "Clean Architecture" architectural pattern.
10. Use Android JetPack libraries.
11. Apply MVVM architectural pattern.
12. Using SQLite database via Room.

Please use the data provided at https://guidebook.com/service/v2/upcomingGuides/

## Demo

<a href="https://github.com/raheemadamboev/android-task-alif-tech/blob/master/app-debug.apk">Download demo application.</a>

I developed this application using modern Jetpack libraries. Below is MAD (Modern Android Development) score of the application.

<img src="https://github.com/raheemadamboev/android-task-alif-tech/blob/master/summary.png" alt="Italian Trulli" width="800" height="400">

## Status

**✅ 1. Get and print the data received from the above URL.**

Data is requested from the URL provided above via Retrofit. Network call is executed in the background thread using Kotlin Coroutines. Also, data observed with the power of Kotlin Flow.

**✅ 2. Parse the data received from the server into a list of Java / Kotlin objects.**

JSON data is converted to Kotlin objects via GSON.

**✅ 3. Display your objects in an organized order (ListView, GridView, etc.).**

Data is shown in the RecyclerView. ListAdapter is implemented for Adapter as it is more effecient than simple RecyclerView.Adapter. It computes data changes automatically in the background thread via DiffUtil and handles changes, and animations.

**✅ 4. Name, City, State and End Date should be displayed.**

Only "name", "endDate" are displayed in the card as there is problem with provided API. There is no data about "state", "city", but there is "venue" object. I guess "venue" object should provide with "city", "state" but the object is empty in JSON data that is read from the URL provided. So, only "name", "endDate" are displayed.

**✅ 5. Also, the object name should reflect the image displayed in the url of each object.**

Honestly, the fifth task was little bit confusing. I just loaded images to ImageView with the help of Glide image loading library from the URL.

**✅ 6. On the main screen, load and display only the first 3 items of data. Implement "continuous (lazy) loading" while the user scrolls through the list and increases the number of list items by 3.**

<img align="left" width="296" height="576" src="https://github.com/raheemadamboev/android-task-alif-tech/blob/master/pagination.gif">

In this API, there is no documentation. It is just an URL of data. There is no query parameter for pagination purposes. So, all the data is downloaded at the same time, then inserted to local Room database. Room database is the single source of truth as the Google recommended. It is not possible to initially load 3 item and then do pagination. RecyclerView expects that items must fill whole screen in order to be scrollable and implement pagination. So, initially 3 times of page size is loaded. In our case, first items get loaded to RecyclerView. When, user scrolls it loads data 3 by 3 via pagination pattern. Also there is TextView that counts items, so you can see the count.

**✅ 7. For every new download of data, save it to the local database.**

All the data is stored in the local database via Room database that is built top of the SQLite. Data is queried with Dao (Database Access Object) interface.

**✅ 8. When the item is clicked, open the item details (item details URI present in the JSON data) in internal webview.**

When any item is clicked, new Fragment is added to load the url of the item in internal WebView. Fragments are managed via Jetpack Navigation Component.

But "url" field in JSON data is invalid, so it is not possible to load url.

"url": "/guide/187611" -> not valid url

I also tried to concatenate url to end of parent url. Still, invalid url

I tried following ways:
- https://guidebook.com/service/v2/upcomingGuides/guide/187611
- https://guidebook.com/service/v2/upcomingGuides//guide/187611
- https://guidebook.com/service/v2/guide/187611

<img align="left" width="296" height="576" src="https://github.com/raheemadamboev/android-task-alif-tech/blob/master/config_change.gif">

**✅ 9. Apply "Clean Architecture" architectural pattern.**

In the project, _Clean Architecture architectural pattern by Uncle Bob_ is strictly followed.

**✅ 10. Use Android JetPack libraries.**

As from MAD score, 35 Android Jetpack libraries are used in this project such as Jetpack Navigation Component, Jetpack Hilt Dependency Injection, Jetpack Room, and others.

**✅ 11. Apply MVVM architectural pattern.**

MVVM (Model-View-ViewModel) architectural pattern is strictly followed in this project. With the help of ViewModel, Repository, Kotlin Flow we are safe from configurations changes and process death. You can see from the video that is playing in the left, we loaded 15 items, and when we change dark mode or change the orientation of the device, all 15 items are loaded. It doesn't start over from beginning. The app is robust.

**✅ 12. Using SQLite database via Room.**

Room database is used to store data locally.

## Conclusion

It was really interesting task for me. I got so much fun, but there is problem with the provided API. Also, there is no documentation for the API.
<br/><br>

## Tech stack

- Clean Architecture
- MVVM
- Dependency Injection (Hilt)
- Jetpack Navigation Component
- Kotlin Coroutines
- Git
- Room
- Material Design
- Retrofit
- Flow, StateFlow
- ViewBinding
- Glide
