# Flight-HotelApp
Design the application with help of MVVM architecture, by using Kotlin, View model,LiveData, Dagger 2, Binding,Glide,mockk for test, insturmation test and Room data base.

Description
---
This sample two fragment which are navigate by using BottomNavigationView to load the flights and hotels from the remote server or local data base.

Design app
--
Kotlin - The Data Binding Library is an Android Jetpack library that allows you to bind UI components in your XML layouts to data sources in your app using a declarative format rather than programmatically. This can reduce boilerplate code.

Data Binding - The Data Binding Library is an Android Jetpack library that allows you to bind UI components in your XML layouts to data sources in your app using a declarative format rather than programmatically. This can reduce boilerplate code.

Dagger 2 - Dagger 2 is one of the open source DI frameworks which generates a lot of boilerplate for us

MVVM
--
Model — contains all the data classes, database classes, API and repository

View — is the UI part that represents the current state of information that is visible to the user.

ViewModel — it contains the data required in the View and translates the data which is stored in Model which then can be present inside a View. ViewModel and View are connected through Databinding and the observable Livedata.

LiveData - LiveData is an observable data holder class. Unlike other observables, it is lifecycle aware 

Unit test
--
mockk - It is a new open source library, focused on making mocking in Kotlin great.

Espresso - UI test cases.


Dependencies
-------------
Retrofit 2 version: '2.4.0'

OkHttp 3 version: '3.11.0'

Glide version: '5.4.1'

Arch Lifecycle version: '1.1.1'

mockk version: '1.9.3'

