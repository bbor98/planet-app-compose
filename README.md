# Planet App Compose
Simple app that shows some planet facts, built to learn & practice [Jetpack Compose](https://developer.android.com/jetpack/compose) and [GitHub collaboration](https://docs.github.com/en/pull-requests/collaborating-with-pull-requests)

## Features
* In HomeScreen, TopAppBar is used, which contains three icons to do the job of navigating to the AuthorScreen, opening VisibiltyDialog and opening SortDialog respectively. Below the ToppAppBar, the planet list is displayed in a LazyColumn with an animated expandable Card item, that shows some of the planet's facts.
* Planet fact's visibility can be toggled in VisibiltyDialog composable, where CheckBoxes used within AlertDialog. 
* Planets can be sorted by the desired fact in ascending/descending order, in SortDialog composable, where RadioButtons used within AlertDialog. 
* In AuthorScreen,  users can be navigated to the author's GitHub & LinkedIn profile in browser or in app if installed, with the usage of AnnotatedStrings and ClickableTexts.
* Splash screen showed in the initialization of app, with the usage of [SplashScreen API](https://developer.android.com/guide/topics/ui/splash-screen)

## Screen Records
### HomeScreen
<img src="https://user-images.githubusercontent.com/88214480/165896325-e0bfc075-3f53-41d6-8516-2659b956b0ae.gif" width="240" height="540" title="HomeScreen">

### VisibilityDialog
<img src="https://user-images.githubusercontent.com/88214480/165896333-8f946e34-9fc7-4a83-83d4-c3e8fb1db473.gif" width="240" height="550" title="VisibilityDialog">

### SortDialog
<img src="https://user-images.githubusercontent.com/88214480/165896077-ff1b508d-3f7c-49cf-914a-d39bc435c629.gif" width="240" height="540" title="SortDialog">

### AuthorScreen
<img src="https://user-images.githubusercontent.com/88214480/165896346-b2e352ef-027d-4a93-a804-31060e1d67ef.gif" width="240" height="540" title="AuthorScreen">

# License
```
Copyright (c) 2022 Bora Bor

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
