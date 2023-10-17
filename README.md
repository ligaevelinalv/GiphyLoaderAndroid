# GiphyLoaderAndroid - an Android app in Jetpack Compose using Hilt, Ktor, Coil, Paging3 and Coroutines

This app implements [Giphy API search endpoint](https://developers.giphy.com/docs/api/endpoint#search). 

This app has one screen which contains a search field and a staggered grid of GIFs. Scrolling down will trigger paging. The app has basic error handling and shows a loading icon while fetching GIFs.

## Launching the app

The [Github repository](https://github.com/ligaevelinalv/GiphyLoaderAndroid) for this project contains a file called `app-debug.apk` which can be moved to an Android mobile phone and installed from storage. This method yields the best app performance.

## Building in Android Studio

To build the project from AS, a file by the name `ApiKey.kt` needs to be created in the `/app/src/main/java/com/example/giphyloader/common` path. This file needs to contain a valid Giphy API key Here is what the file should look like:

```kotlin
package com.example.giphyloader.common

const val API_KEY = "[VALID GIPHY API KEY]"

```

While building on an emulator is possible and the app will be usable, for better performance, the app should be built on a physical Android mobile phone.

## Figma

An initial design was created for this app in Figma. To view this and screenshots from the app, open [this link](https://www.figma.com/file/5Ax7ZhojQ0gUbKV14xHUwX/GiphyLoader?type=design&node-id=0%3A1&mode=design&t=zApiQujxuuOBIZLi-1).

## Sources used for reference

**Android developer documentation**

[https://developer.android.com/](https://developer.android.com/)

**StackOverflow**

[https://stackoverflow.com/](https://stackoverflow.com/)

**Ktor setup**

[https://tahaben.com.ly/2023/02/making-api-request-using-ktor-client-in-android/](https://tahaben.com.ly/2023/02/making-api-request-using-ktor-client-in-android/)

**Ktor + Hilt**

[https://tahaben.com.ly/2023/02/how-to-use-hilt-for-dependency-injection-in-your-android-projects/](https://tahaben.com.ly/2023/02/how-to-use-hilt-for-dependency-injection-in-your-android-projects/)

**VM, UI, debounce**

[https://github.com/apoplawski96/medium-code-samples/?source=post_page-----c1e904a59ce1--------------------------------](https://github.com/apoplawski96/medium-code-samples/?source=post_page-----c1e904a59ce1--------------------------------)

**LaunchedEffect**

[https://betterprogramming.pub/jetpack-compose-side-effects-launchedeffect-with-example-99c2f51ff463](https://betterprogramming.pub/jetpack-compose-side-effects-launchedeffect-with-example-99c2f51ff463)

**Response class**

[https://medium.com/codex/kotlin-sealed-classes-for-better-handling-of-api-response-6aa1fbd23c76](https://medium.com/codex/kotlin-sealed-classes-for-better-handling-of-api-response-6aa1fbd23c76)

**Pagination**

[https://betterprogramming.pub/turn-the-page-overview-of-android-paging3-library-integration-with-jetpack-compose-3a7881ed75b4](https://betterprogramming.pub/turn-the-page-overview-of-android-paging3-library-integration-with-jetpack-compose-3a7881ed75b4)

[https://proandroiddev.com/pagination-in-jetpack-compose-with-and-without-paging-3-e45473a352f4#3674](https://proandroiddev.com/pagination-in-jetpack-compose-with-and-without-paging-3-e45473a352f4#3674)

[https://slack-chats.kotlinlang.org/t/468683/hey-guys-i-want-to-run-debounce-on-a-flow-from-within-a-comp](https://slack-chats.kotlinlang.org/t/468683/hey-guys-i-want-to-run-debounce-on-a-flow-from-within-a-comp)
