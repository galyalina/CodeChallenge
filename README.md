# CodeChallenge

Simple android application organized accourding to MVP(Model View Presenter) pattern.
The MVP pattern allows separate the presentation layer from the logic and makes views independent from data source. 
Application into three different layers, which let us test them independently. Additional advantage of 
MVP pattern that it allows to test most of the logic without using instrumentation tests.

# Features
 - Repositories list view
 
Fetch the list of repositories from the API Endpoint mentioned and display them
in a list view. Each item of the list contain the name, the description and the last
time the repository was updated.

 - Detail view
 
This view will open when a user clicks on a list item. The view contain name,
description, programming languages, whether or not the repository is a fork,
date of the last update and owner information.

### Pre-requisites
Android SDK 25

Android Build Tools v25.0.2

Android Support Repository

### Tech

Dillinger uses a number of open source projects to work properly:

* [Picasso](https://github.com/square/picasso) - A powerful image downloading and caching library for Android

Used for simple loading of User's avatar

* [Retrofit](http://square.github.io/retrofit/) - Type-safe HTTP client for Android and Java by Square, Inc.

Used for Get requests

* [Apache Commons](https://commons.apache.org/proper/commons-lang/)  Lang provides a host of helper utilities for the java.lang API, notably String manipulation methods, basic numerical methods, object reflection, concurrency, creation and serialization and System properties. 

Usefull when overriding methods, such as hashCode, toString and equals.

### Todos

- Tablet two panel view support
- Refresh data 
- Add more tests 
- Add error message handling(currently jusy Toast notification)
- Add analytics/bug reports SDKs(Splunk mint etc)

### License


    Copyright 2013 Square, Inc.

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
