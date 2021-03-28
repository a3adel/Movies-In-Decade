## Movies In Decade App
### The past decade held a lot of movies, some left a mark and some were just a set of 24-60 pictures per second., In this project I'm trying to showcase some of these movies

## IMPORTANT NOTE
    To try the application clone the repo and add your flicker API key to local.properties file under and rebuild the project or it would give you syntax error
--------------------------------------------------------------------------------------
This Application has 3 main user stories:-
- User can Get all the movies from json file stored in the assets foler
- User can search for a specifi movie and the search results should be grouped by year and sorted by rating
- User can see the details of the movie and search for images with the same name from flickr api
---------------------------------------------------------------------------------------


I'm using the following stack:-
- Clean Architecture
- MVVM presentation Architecutre
- Hilt for Dagger2
- coroutines & flow
- LiveData
- Paging-3 
- Moshi (as its faster than Gson)
- Retrofit
-By applying the Clean Architecutre it was simple to do unit testing for the application, I have implemented 32 different unit testing functions I used Mockk to mock different objects

The Clean Architecture I implemented works in the following way:-
## Presentation Layer:- 
In this Layer I implement the Views, Adapters and ViewModels 
The ViewModels publish their data to the views via `LiveData` or `Flow`
It also contains any UI Validation Logic by implementing Validator classes and injecting thos classes in the ViewModels for example `SearchMoviesInputValidator` validates the user input and returns `UIValidationResult` that holds the state of the validation and any error messages from the resources

## Domain Layer
In this Layer I handle any business scenarios( like filtering and sorting) and Application Models 
It also contains Mappers (When needed) If the Model in the Ui is different from the Application Models

## Data Layer
This layer is all about the data It fetches the data from different data sources(`Remote`,`Local`,`Memory`), it mapps the response model to the application model and it returns those data
The entry point of the data layer is the `Repo` class which is called from the domain layer
each `Repo` consumes different client based on the source of the data `RemoteClient`, `LocalClient` or `MemoryClient`
each `Client` consumes `DataInterface` for example the `MoviesLocalClient` uses the `MoviesFileInterface` and each `DataInterface` returns `Response` entity object to the `Client` in which the `Client` mapps it to Data Object,
----------------------------------------------------------------------------------------------

To make the searching process faster I did the following:-
- I cached the movies in the MemoryCacheClient 
- I applied `debounce` operator on the `TextWatcher` to make the search starts after the user stops typing by `500ms` So it wouldn't search each time the user enters a chacrcter 
