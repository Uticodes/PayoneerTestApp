# PayoneerTestApp

## About Project
This project implements a scrollable list of payment methods.
For each payment method, it shows the method logo and label

## Language:
* 100% Java

## Features
* MVVM
* Rx-Java
* Dagger Hilt
* Light/Dark Mode Support
* Mockito


## Prerequisite
To build this project, you require:
- Android Studio arctic fox
- Gradle 7.0.0+
- You may require Java 11 to run


## Libraries
- [Viewmodel](https://developer.android.com/topic/libraries/architecture/viewmodel) - ViewModel for persisting view state across config changes
- [Retrofit](https://square.github.io/retrofit/) - type safe http client and supports coroutines out of the box.
- [okhttp-logging-interceptor](https://github.com/square/okhttp/blob/master/okhttp-logging-interceptor/README.md) - logs HTTP request and response data.
- [Dagger Hilt](https://dagger.dev/hilt) - handles dependency injection
- [Rx-Java](https://github.com/ReactiveX/RxJava) - handles asynchronous and long running tasks off the main thread
- [Lombok](https://projectlombok.org/setup/android)
- [Android View Binding](https://developer.android.com/topic/libraries/view-binding)


## Building issue that may arise.
When cloning the project, you might have this issues, following the screenshot, change your gradle JDK to version 11, the problem should be fixed.

<img width="986" alt="issues" src="https://user-images.githubusercontent.com/43546652/147707634-90ed3103-a513-4ac7-87bf-72ff96a4eff7.png">


## Screenshots

https://user-images.githubusercontent.com/43546652/147707414-90ac16c1-0411-4462-a22d-29c4cf36ef38.mp4

<img width="293" alt="Screenshot 2021-12-29 at 3 30 00 PM" src="https://user-images.githubusercontent.com/43546652/147707485-375720c8-b70f-42ec-b1ef-3527e25cbde1.png">

<img width="330" alt="Screenshot 2021-12-29 at 3 36 17 PM" src="https://user-images.githubusercontent.com/43546652/147707502-983510d2-0221-4551-a52e-7fee8d78400b.png">

<img width="330" alt="Screenshot 2021-12-29 at 3 37 28 PM" src="https://user-images.githubusercontent.com/43546652/147707585-3b6a97c4-efc2-4340-b726-324fed15d866.png">


## Testing
Testing is done with Mockito, and JUnit testing framework

## Author
Utibe Etim

## License
This project is licensed under the Apache License 2.0 - See: http://www.apache.org/licenses/LICENSE-2.0.txt
