
### Details & Description 
 - Architecture : MVVM with Unidirectional data flow. It could be called MVI too.
 - State Retaining: Android ViewModel
 - Publish Subscribe: Livedata
 - UI State Management: Data classes
 - Dependency Injection: Koin
 - UI Binding: Android datadinding
 - Image Loading: Picaso
 - Guidlines: DRY, YAGNI, SOlID
 - Development Strategy : 
     - Outsid-In TDD
     - Domain drevin developement
 - Test pyramid:
     - Small tests : 11
     - Medium tests: 1
 - Testing Tools
     - custom coroutine rule
     - Junit

     - MockK
     - Truth assertion
 - JVM Test: 12, including contract tests
 - Instrument Test or Ui Test: 0
 
#### Note
- Paging is not included in this project. However, a separate usecase could be created and provided to 'FetchingMoviesListUseCase' to save and culculate the next page on Recyclerview scrolling. 
- The Acceptance test is validating end to end flow. However, no Instraument test is added for now due to time constraints. How I can write UI test and I have demonstrated it in one my other project:https which could be found [here](//github.com/Irfanulhaq1991/Grid-Puzzl-Android/blob/master/app/src/test/java/com/irfan/gridpuzzl/ui/PuzzleUITest.kt)
- I  have also written liberaries which could be found at [Androd-Shared-Preference-Utility](https://github.com/Irfanulhaq1991/Androd-Shared-Preference-Utility) and [Android-RecylerView-Adapter for databindings](https://github.com/Irfanulhaq1991/Android-RecylerView-Adapter).
