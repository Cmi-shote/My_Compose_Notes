# Notey - A Modern Note-Taking App

## Overview

Notey is a modern, feature-rich note-taking app built using Jetpack Compose, adhering to clean architecture principles. The app supports secure user authentication (including Google Sign-In), enables users to create, edit, and retrieve notes with media attachments.



## Tech Stack

- Jetpack Compose: For modern UI development.

- Firebase: Authentication, Firestore (cloud database), and Storage (media uploads).

- Room Database: For local data storage.

- Koin: Dependency Injection.

- Coroutines and Flow: For asynchronous operations and reactive programming.

- JUnit & Mockk: For testing.


## Clean Architecture

**The app follows clean architecture principles:**

1. Presentation Layer - Built using Jetpack Compose.

    - Contains UI elements and state management.

2. Domain Layer

    - Includes use cases encapsulating business logic.

3. Data Layer

    - Manages data from Room, Firebase Firestore, and Firebase Storage.

    - Implements repository pattern for data access.
