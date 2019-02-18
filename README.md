# Football Fixtures
Football Fixtures is a football app that provides match [Data](https://www.football-data.org/) of all major leagues.

## Guideline
1. Clone from the `master` branch.

2. Add your API KEY in the `Constants.kt` file
```
  const val API_KEY = "YOUR_API_KEY"
```
## App's Features
There are over 100 football competitions available with `live scores`, `fixtures`, `tables`, `squads`, `lineups/subs`, `goalscorers`, etc. 
but the app is built on a free plan. Therefore, it is limited to some data contents.

## Code Pattern
[MVVM](https://www.journaldev.com/20292/android-mvvm-design-pattern) architectural pattern was used in order to detach the data logic implementation from the views.

## Libraries
1. [Android Support Libraries](https://developer.android.com/topic/libraries/support-library/)
2. [Constraint Layout](https://developer.android.com/training/constraint-layout/): for better way of presenting views and layouts.
3. [Dagger 2](https://github.com/google/dagger) for Dependency Injection.
4. [Retrofit](https://square.github.io/retrofit/) and RxJava2(https://github.com/ReactiveX/RxJava) for making network calls.
5. [OkHttp](https://square.github.io/okhttp/) for Network Logging Interceptor
6. [Moshi](https://github.com/square/moshi): modern JSON library for Kotlin and Java.
7. [Glide](https://github.com/bumptech/glide): image loading library.
8. [AndroidSVG](https://github.com/BigBadaboom/androidsvg) for loading images from API especially SVG formats.
9. [Room](https://developer.android.com/training/data-storage/room/) for local data persistence.
10. [Espresso](https://developer.android.com/training/testing/espresso/) for UI and Automated tests.
