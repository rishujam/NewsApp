Features:

1. Watch Breaking news in your country.
2. Search news.
3. Bookmark your favorite news and save that in local DB.

Decisions:

1. Used the news adapter in SavedNewsFragment as well.
2. It was confusing first for dagger hilt. That it should be used here or not. Later I understood that dagger hilt helps very much in initializing the values and reduces many lines of code in bigger project.
3. Making use case classes and adding the domain layer. It came to my mind that it will be too much for this kind of small project.
