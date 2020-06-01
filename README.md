# Football Fixtures
Football Fixtures is a football app that provides match [Data](https://www.football-data.org/) of all major leagues.

## Guideline
1. Clone from the `develop` branch to get the refactored code.

2. Add your API KEY in the `local.properties` file
```
  API_KEY = "YOUR_API_KEY"
```
## App's Features
There are over 100 football competitions available with `live scores`, `fixtures`, `tables`, `squads`, `lineups/subs`, `goalscorers`, etc. 
but the app is built on a free plan. Therefore, it is limited to some data contents.

## Code Pattern
This code was refactored from a single to multi module using clean architecture, architectural pattern (MVVM) and Jetpack components (Data Binding, Livedata, Lifecycles, Navigation, Room, ViewModel).

This illustrates the use of multi-module with clean architecture to separate the different layers involved. Also, to make testing easier.
