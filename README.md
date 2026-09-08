# Codans Digital Store

Android application developed with **Kotlin and Jetpack Compose** as a collaborative project for the Codans course.

The project simulates a digital store and focuses on applying **MVVM, Clean Architecture, REST API integration, dependency injection, navigation, and Git/GitHub collaboration practices**.

---

## About the Project

**Codans Digital Store** uses the public **DummyJSON** API to provide application data.

The application is divided into five main screens:

| Screen | Description |
|---|---|
| Home | Main application menu |
| Products | Product list and product details |
| Cart | Products added to the cart |
| Users | User information |
| Posts | Available posts |

The project is organized by features, allowing each team member to work independently on their own branch and integrate their work through Pull Requests.

---

## Features

### Home

- Main application menu
- Access to the main features
- Navigation to the products screen
- Modern interface built with Jetpack Compose

### Products

- Product data retrieved from DummyJSON
- Product list
- Product image, name, and price
- Navigation to product details
- Fetching a specific product by ID

### Product Details

- Product image
- Product name
- Price
- Description
- Add-to-cart action

### Cart

- Dedicated feature structure
- Display of products added to the cart

### Users

- Display of users provided by the API

### Posts

- Display of posts provided by the API

---

## Architecture

The project combines **Clean Architecture** and **MVVM**, keeping responsibilities separated between layers.

```text
Presentation
Compose UI + ViewModels
        |
        v
Domain
Models + Repository Contract
        |
        v
Data
API + DTOs + Repository Implementation
        |
        v
DummyJSON API
```

### Data Flow

```text
API
 ↓
Retrofit
 ↓
ProductApi
 ↓
Repository
 ↓
ViewModel
 ↓
StateFlow
 ↓
Jetpack Compose
```

This separation prevents the presentation layer from depending directly on the API implementation.

---

## Project Structure

```text
com.ranielschneider.codansdigitalstore
│
├── core
│   ├── di
│   │   └── AppModule.kt
│   ├── navigation
│   │   └── AppNavigation.kt
│   └── network
│
├── features
│   ├── home
│   │   └── presentation
│   │       └── HomeScreen.kt
│   │
│   ├── products
│   │   ├── data
│   │   │   ├── ProductApi.kt
│   │   │   ├── ProductDto.kt
│   │   │   ├── ProductsResponseDto.kt
│   │   │   └── ProductRepositoryImpl.kt
│   │   ├── domain
│   │   │   ├── Product.kt
│   │   │   └── ProductRepository.kt
│   │   └── presentation
│   │       ├── ProductsScreen.kt
│   │       ├── ProductsViewModel.kt
│   │       ├── ProductDetailScreen.kt
│   │       └── ProductDetailViewModel.kt
│   │
│   ├── cart
│   ├── users
│   └── posts
│
└── ui
    ├── components
    │   └── AppTopBar.kt
    └── theme
        ├── Color.kt
        ├── Theme.kt
        └── Type.kt
```

---

## Technologies

- Kotlin
- Jetpack Compose
- Material 3
- MVVM
- Clean Architecture
- Retrofit
- Gson Converter
- OkHttp
- Hilt
- Navigation Compose
- Coil
- Coroutines
- StateFlow
- Git & GitHub

---

## API

The application uses the public **DummyJSON** API.

Base URL:

```text
https://dummyjson.com/
```

### Main Endpoints

```http
GET /products
```

Returns the product list.

```http
GET /products/{id}
```

Returns a specific product.

---

## DTO and Domain

The project separates the model received from the API from the model used internally by the application.

### DTO

`ProductDto` represents the external API data:

```kotlin
data class ProductDto(
    val id: Int,
    val title: String,
    val description: String,
    val price: Double,
    val thumbnail: String
)
```

### Domain

`Product` represents the model used by the application:

```kotlin
data class Product(
    val id: Int,
    val title: String,
    val description: String,
    val price: Double,
    val thumbnail: String
)
```

The conversion is performed inside `ProductRepositoryImpl`.

---

## Dependency Injection

The application uses **Hilt** to manage dependencies.

The `AppModule` provides components such as:

- Retrofit
- ProductApi
- ProductRepository

Dependency flow:

```text
ProductsViewModel
       ↓
ProductRepository
       ↓
ProductRepositoryImpl
       ↓
ProductApi
       ↓
Retrofit
```

Hilt provides dependencies automatically, avoiding manual object creation throughout the application.

---

## Navigation

Navigation is centralized in:

```text
core/navigation/AppNavigation.kt
```

Main navigation flow:

```text
Home
  ↓
Products
  ↓
Product Details
```

The product ID is passed through the following route:

```text
product/{productId}
```

On the product details screen, `SavedStateHandle` retrieves the ID received through navigation.

---

## User Interface

The interface was built with **Jetpack Compose**, focusing on a modern, clean, and consistent user experience.

Main UI elements include:

- Material 3
- Cards
- Custom typography
- Centralized theme
- Reusable components
- Image loading with Coil
- Reusable top bar
- Back navigation

---

## Git and GitHub

Development follows a feature-based branch strategy:

```text
main
│
├── feature/home
├── feature/products
├── feature/cart
├── feature/users
└── feature/posts
```

### Collaboration Flow

```text
Create Branch
     ↓
Develop Feature
     ↓
Commit
     ↓
Push
     ↓
Pull Request
     ↓
Code Review
     ↓
Approval
     ↓
Merge
```

### Conventional Commits

The project follows semantic commit messages:

```text
feat: new feature
fix: bug fix
style: UI or formatting changes
refactor: code refactoring
docs: documentation
test: tests
chore: maintenance
```

Example:

```text
feat: display products from api
```

---

## Learning Objectives

This project was developed to strengthen practical knowledge of:

- Modern Android development
- Kotlin
- Jetpack Compose
- MVVM
- Clean Architecture
- REST APIs
- Retrofit
- DTOs
- Repository Pattern
- Hilt
- Dependency Injection
- StateFlow
- Coroutines
- Navigation Compose
- Git and GitHub
- Pull Requests
- Code Review
- Collaborative development

---

## Getting Started

### Requirements

- Android Studio
- JDK compatible with the project
- Android SDK
- Git

### Installation

Clone the repository:

```bash
git clone https://github.com/ranielschneider/CodansDigitalStore.git
```

Open the project in Android Studio, wait for the Gradle synchronization to finish, and run the application on an Android emulator or physical device.

---

## Possible Future Improvements

- More complete network error handling
- Dedicated loading and error states
- Unit tests
- UI tests
- Local persistence
- Search and filtering
- Pagination
- Accessibility improvements
- Further improvements to the cart experience

---

## Team

| Contributor | Responsibilities |
|---|---|
| **Raniel Schneider** | Home and Products screens |
| **William Andra** | Cart screen |
| **Thiago** | Users and Posts screens |

The project was developed collaboratively using feature branches, Pull Requests, code reviews, and semantic commits.

---

## Academic Project

Developed for educational purposes as a practical project for the **Codans** course, focusing on Android development, software architecture, and collaborative development using Git and GitHub.
