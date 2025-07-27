# TravelApp

A simple Android application built with Kotlin, designed to help users explore and manage travel-related information. This project uses Firebase for real-time database support and follows modern Android development practices including ViewBinding and Jetpack libraries.

## Features

- Browse and manage travel data
- Firebase Realtime Database integration
- ViewBinding enabled for cleaner UI code
- Material Design components

## Tech Stack

- **Language**: Kotlin
- **Build Tool**: Gradle (Kotlin DSL)
- **Min SDK**: 24  
- **Target SDK**: 35  
- **Compile SDK**: 36  
- **Java Compatibility**: 17  
- **Firebase**: Realtime Database  
- **Testing**: JUnit, Espresso

## Recent Changes

- Updated `compileOptions` to use Java 17  
- Added `com.github.ben-manes.versions` plugin to manage and check for dependency updates  
- Added ViewBinding support  
- Organized dependencies using `libs.versions.toml`

## Dependency Management

The project uses the [Ben Manes Versions Plugin](https://github.com/ben-manes/gradle-versions-plugin) to identify outdated dependencies. Run the following command to check for updates:

```bash
./gradlew dependencyUpdates
```

⚠️ Requires a working Java 17+ environment
