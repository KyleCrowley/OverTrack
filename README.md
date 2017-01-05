Overtrack
===

Android companion app for [Overwatch](https://playoverwatch.com) written in [Kotlin](https://kotlinlang.org).

Uses [Lootbox API](https://gitlab.com/SingularityIO/LootBoxAPI) for player stats and achievements, as well as Overwatch patch notes. 

Dependencies:
===

- [Dagger 2](https://github.com/google/dagger)

- [RxJava](https://github.com/ReactiveX/RxJava)
 * [RxAndroid](https://github.com/ReactiveX/RxAndroid)
 
- [Retrofit](https://github.com/square/retrofit)
 * [Retrofit Moshi Converter](https://github.com/square/retrofit/tree/master/retrofit-converters/moshi)
 * [Retrofit RxJava Adapter](https://github.com/square/retrofit/tree/master/retrofit-adapters/rxjava)
 * [OkHttp3 Logging Interceptor](https://github.com/square/okhttp/tree/master/okhttp-logging-interceptor)
 
- [Picasso](https://github.com/square/picasso)
 * [Picasso OkHttp3 Downloader](https://github.com/JakeWharton/picasso2-okhttp3-downloader)
 
- [Timber](https://github.com/JakeWharton/timber)

- [ButterKnife](https://github.com/JakeWharton/butterknife)

Features:
===

- [x] Overwatch Patch Notes

- [x] Player Search
 - [ ] Player Achievements
 - [x] Player Profile (Overview)
 - [x] Player stats for all heros (general stats)
 - [ ] Player stats for a particular hero
 - [ ] Player stats for heros (overall)

Notes:
===

I am using this project for learning, and as such, some functionality *may not* work as intended.
