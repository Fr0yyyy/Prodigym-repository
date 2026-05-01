\## Arquitectura del Proyecto (Clean Architecture + MVVM)



El proyecto sigue una estructura estricta de Clean Architecture, separando las responsabilidades en distintas capas e integrando utilidades transversales y de inyección de dependencias. Patrón de arquitectura MVVM.



\* \*\*📁 core\*\* (Utilidades transversales)

&#x20; \* `Constants.kt`

&#x20; \* `Extensions.kt`



\* \*\*📁 di\*\* (Hilt: Módulos de inyección)

&#x20; \* `NetworkModule.kt`

&#x20; \* `DatabaseModule.kt`

&#x20; \* `RepositoryModule.kt`



\* \*\*📁 data\*\* (Capa de Datos)

&#x20; \* \*\*📁 dataSource\*\*

&#x20;   \* \*\*📁 exercise\*\*

&#x20;     \* \*\*📁 remote\*\* (api, dto)

&#x20;     \* \*\*📁 local\*\* (room, dbo)

&#x20;     \* `ExerciseDataSource.kt` (Interfaz)

&#x20;     \* `ExerciseDataSourceImpl.kt`

&#x20; \* \*\*📁 repository\*\*

&#x20;   \* `ExerciseRepositoryImpl.kt` (Incluye Mappers DTO/DBO -> Entity)



\* \*\*📁 domain\*\* (Capa de Dominio)

&#x20; \* \*\*📁 entity\*\*

&#x20;   \* \*\*📁 exercise\*\*

&#x20;     \* `ExerciseEntity.kt`

&#x20; \* \*\*📁 repository\*\*

&#x20;   \* `ExerciseRepository.kt` (Interfaz)

&#x20; \* \*\*📁 useCase\*\*

&#x20;   \* \*\*📁 exercise\*\*

&#x20;     \* `ExercisesUseCase.kt` (Interfaz única)

&#x20;     \* `ExercisesUseCaseImpl.kt` (Todas las implementaciones)



\* \*\*📁 ui\*\* (Capa de Presentación)

&#x20; \* \*\*📁 theme\*\* (Colores, Tipografía, Tema de Compose)

&#x20; \* \*\*📁 navigation\*\* (NavHost y rutas de la app)

&#x20; \* \*\*📁 components\*\* (Reutilizables: dialog, topbar)

&#x20; \* \*\*📁 exercise\*\* (Feature Principal)

&#x20;   \* `ExerciseScreen.kt`

&#x20;   \* `ExerciseScreenViewModel.kt`

&#x20;   \* `ExerciseUiState.kt` (Estado de la UI: Loading, Success, Error)

&#x20; \* \*\*📁 profile\*\* (Feature de Usuario)

&#x20;   \* `ProfileScreen.kt`

&#x20;   \* `ProfileScreenViewModel.kt`



&#x20;   CONTEXT: This project strictly follows a custom Clean Architecture structure with an MVVM pattern (NOT MVI) using Jetpack Compose. The folder structure provided in architecture.md is the absolute source of truth.



✅ MANDATORY (MUST DO)

Strict Folder Adherence: You MUST place files exactly where defined in the structure. For example, the UI layer is named ui, not presentation.



Pure MVVM Flow: The UI (Compose Screen) observes the UI State (e.g., ExerciseUiState) exposed by the ViewModel via StateFlow. User interactions must trigger direct function calls on the ViewModel (e.g., viewModel.loadExercises()).



Mapper Placement: All data mapping logic (DTO to Entity, DBO to Entity, and vice versa) MUST be implemented inside the RepositoryImpl class located in data/repository.



Grouped Use Cases: All implementations of use cases for a specific feature MUST be grouped inside a single implementation file (e.g., ExercisesUseCaseImpl.kt implements all functions defined in ExercisesUseCase.kt).



Pure Domain Layer: The domain layer MUST remain completely agnostic of the Android framework, database libraries (Room), or network libraries (Retrofit). Only Kotlin standard library classes are allowed here.



Dependency Injection: All providers for Room, Retrofit, and Repositories MUST be defined in their respective modules inside the di (Hilt) root folder.



🚫 PROHIBITED (NEVER DO)

NO MVI Patterns: DO NOT use Intent/Action/Event sealed classes to pass user interactions from the View to the ViewModel. DO NOT create a centralized handleEvent(event: UiEvent) function in the ViewModel. Stick to standard MVVM function calls.



NO Root Folder Alterations: DO NOT invent new root folders or rename existing ones. Stick strictly to core, di, data, domain, and ui.



NO Model Bleeding: DO NOT leak ExerciseDto (from remote) or ExerciseDbo (from local) into the domain or ui layers. The UI and UseCases must only know about ExerciseEntity.



NO UI Logic in Compose: DO NOT place business logic, data formatting, or complex state calculations directly inside @Composable functions. The Composable must be "dumb" and only react to the ViewModel's state.



NO Multiple Use Case Files: DO NOT create a separate file for every single use case implementation (e.g., no AddExerciseUseCase.kt, DeleteExerciseUseCase.kt). They must be grouped into the single Impl file per feature as defined in the architecture.


# TECH STACK \& DEPENDENCIES (AI INSTRUCTIONS)



\*\*CONTEXT:\*\* This document defines the libraries and tools used in the project. The AI MUST use these libraries when implementing features. \*\*Note:\*\* Version numbers are omitted (except Kotlin); use the latest stable versions compatible with \*\*Kotlin 2.0.21\*\* and \*\*Compose BOM\*\*.



\## 1. Core Framework

\* \*\*Language:\*\* Kotlin 2.0.21.

\* \*\*UI Framework:\*\* Jetpack Compose (Declarative UI).

\* \*\*Dependency Injection:\*\* Hilt (Dagger-Hilt).



\## 2. Data \& Persistence

\* \*\*Room Database:\*\* Used for local persistence of `ExerciseDbo` and athlete data.

&#x20;   \* \*Implementation Note:\* Use \*\*KSP\*\* (Kotlin Symbol Processing) instead of KAPT.

\* \*\*Retrofit:\*\* Used for network requests to ExerciseDB.

&#x20;   \* \*Converter:\* Gson Converter for JSON to `ExerciseDto` mapping.

\* \*\*OkHttp Logging Interceptor:\*\* Mandatory for debugging network traffic in the `data` layer.



\## 3. Media \& Assets

\* \*\*Coil (Compose Extension):\*\* Mandatory for loading and displaying exercise GIFs from `gifUrl`.

&#x20;   \* \*Note:\* Ensure GIF support is enabled in the ImageLoader configuration.



\## 4. Navigation

\* \*\*Jetpack Navigation Compose:\*\* Used for screen transitions.

&#x20;   \* \*Location:\* Navigation logic must reside in `ui/navigation/`.

&#x20;   \* \*Pattern:\* Use Type-safe navigation or Route constants defined in `Routes.kt`.



\---



\## ⚙️ INTEGRATION GUIDELINES FOR AI



1\. \*\*Hilt Modules:\*\*

&#x20;   \* Create `NetworkModule.kt` for Retrofit/OkHttp providers.

&#x20;   \* Create `DatabaseModule.kt` for Room Database and DAO providers.

&#x20;   \* Create `RepositoryModule.kt` for binding interfaces to implementations.



2\. \*\*Room Setup:\*\*

&#x20;   \* Define `TypeConverters` if complex objects (like Lists) need to be stored in `ExerciseDbo`.

&#x20;   \* Use `suspend` functions in DAOs for all database operations.



3\. \*\*Coil Implementation:\*\*

&#x20;   \* Use `AsyncImage` within the UI layer.

&#x20;   \* Implement content descriptions and placeholder/error states.



4\. \*\*Network Handling:\*\*

&#x20;   \* All network calls MUST be executed within the `IO` dispatcher.

&#x20;   \* Ensure the `api\_key` and `host` headers for ExerciseDB are managed in `NetworkModule.kt` or a dedicated Interceptor.


