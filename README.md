# BangScreenToolsMaster
## Chinese Document
	This document describes how BangScreenTools work and how to use it. 
	Document Web URL: https://blog.csdn.net/qq_29856589/article/details/83714642
## How to used?
used way：https://jitpack.io/#KilleTom/BangScreenToolsMaster this web can telled your how many versions can provide to be used;
but if you don't want to chose , you can copy this to use.
### Init
Pasue this message in the builde.gradle(project)file:
```gradle
allprojects {
    repositories {
        google()
        jcenter()
        maven { url 'https://jitpack.io' }
    }
}

``` 
Pasue this message in the builde.gradle(app)file:
```gradle
	dependencies {
	        implementation 'com.github.KilleTom:BangScreenToolsMaster:v1.0.0'
	}
```
### Use in Activity
#### make the statusbar was transparent
```Kotlin
//used onCreat method
    BangScreenTools.getBangScreenTools().transparentStatusCutout(window, this)
//used onWindowFocusChanged method
    BangScreenTools.getBangScreenTools().windowChangeTransparentStatusCutout(window)
```
#### make the layout extend statusbar
```Kotlin
//used onCreat method
    BangScreenTools.getBangScreenTools().extendStatusCutout(window, this)
//used onWindowFocusChanged method
    BangScreenTools.getBangScreenTools().windowChangeExtendStatusCutout(window)
```
#### make the layout was fullscreen
```Kotlin
//used onCreat method
    BangScreenTools.getBangScreenTools().fullscreen(window, this)
//used onWindowFocusChanged method,this void is make fullscreen is worked.
    BangScreenTools.getBangScreenTools().windowChangeFullscreen(window)
```
#### make the layout not use bangscreen
```kotlin
//used onCreat method
    BangScreenTools.getBangScreenTools().blockDisplayCutout(window)
```
