# Rijksmuseum Assignment

### IMPORTANT
Rijksmuseum Api includes many seemingly duplicate items with different Object Numbers. These were not filtered out.
The Api also has a lot of items with Static images. These are filtered out during Dto -> Domain mapping
The application doesn't provide UI options for sorting and searching. However the architecture was designed in a way that adding these would be quite simple.

## Overview

The Rijksmuseum Assignment is an Android application designed to provide a seamless way to explore the Rijksmuseum's collection. Leveraging the Rijksmuseum API, users can browse their collection and see details of the art works within the museum. This project showcases modern Android development practices with technologies like Jetpack Compose, Hilt, and Paging 3.

---

## Features

### 1. **Browse Art Collection**
- Paginated list of artworks retrieved using the Rijksmuseum API.
- Includes artwork images, titles, and authors.
- Implements `Pull-to-Refresh` functionality for manual data refresh.

### 2. **Detailed Artwork Information**
- Fetches detailed data about individual artworks, including descriptions, presenting dates, techniques, and materials.
- Provides a high-quality image of the artwork.
  
---

## Architecture

The project follows the Clean Architecture with **MVVM (Model-View-ViewModel)** architectural pattern, ensuring a clean separation of concerns and scalability.

### Core Layers:

1. **Data Layer**
   - Handles API calls and data transformations.
   - Uses Retrofit for network operations and Kotlin Serialization for parsing JSON responses.

2. **Domain Layer**
   - Contains use cases encapsulating business logic (e.g., fetching artwork lists or details).

3. **Presentation Layer**
   - Uses Jetpack Compose for UI rendering.
   - Includes `ViewModel` classes for state management and data flow.

---

## Tech Stack

### 1. **Kotlin**
- Primary programming language for the app.

### 2. **Jetpack Compose**
- Declarative UI framework for building responsive and modern UIs.

### 3. **Hilt**
- Dependency injection framework for managing dependencies and improving testability.

### 4. **Retrofit**
- Used for making network requests to the Rijksmuseum API.

### 5. **Paging 3**
- Efficiently loads paginated data for smooth scrolling experiences.

### 6. **Coil**
- Handles image loading and caching.

### 7. **Kotlinx Serialization**
- Parses JSON responses into Kotlin data classes.

### 8. **Turbine and Mockk**
- For unit testing and mocking in a coroutine-safe environment.

---

## Getting Started

### Prerequisites

- **Android Studio** (Arctic Fox or later)
- **Minimum SDK**: 24
- **Target SDK**: 35

### Setup

1. Clone the repository:
   ```bash
   git clone https://github.com/Kmozcan1/Rijksmuseum_Assignment.git
   ```

2. Open the project in Android Studio.

3. Sync the project and run the app.

---

## Key Endpoints

### 1. **Get Art Collection**
Fetches a list of artworks based on specified filters:

```kotlin
@GET("{culture}/collection")
suspend fun getCollection(
    @Path("culture") culture: String = DEFAULT_CULTURE, //en
    @Query("format") format: String? = null,
    @Query("p") page: Int = 0,
    @Query("ps") pageSize: Int = DEFAULT_PAGE_SIZE, //10
    @Query("q") query: String? = null,
    @Query("involvedMaker") involvedMaker: String? = null,
    @Query("type") type: String? = null,
    @Query("material") material: String? = null,
    @Query("technique") technique: String? = null,
    @Query("f.dating.period") period: Int? = null,
    @Query("f.normalized32Colors.hex") colorHex: String? = null,
    @Query("imgonly") imgOnly: Boolean? = null,
    @Query("toppieces") topPieces: Boolean? = null,
    @Query("s") sort: SortOption? = DEFAULT_SORT_OPTION //"artist"
): CollectionResponse
```

### 2. **Get Artwork Details**
Fetches detailed information about a specific artwork:

```kotlin
@GET("{culture}/collection/{objectNumber}")
suspend fun getCollectionDetails(
    @Path("culture") culture: String = DEFAULT_CULTURE, //en
    @Path("objectNumber") objectNumber: String
): CollectionDetailsResponse
```

---

## Tests

### Unit Tests
- **ViewModels**: Ensures proper state management and interactions with use cases.
- **PagingSource**: Verifies pagination logic and API integration.
- **Use Cases**: Validates business logic for fetching data.

### Tools Used:
- **JUnit**
- **Turbine** (for Flow testing)
- **Mockk** (mocking dependencies)

Run tests:
```bash
./gradlew test
```

---

## Contact

For any questions or suggestions, feel free to contact:
- **Author**: [Kmozcan1](https://github.com/Kmozcan1)

---

Happy coding!
