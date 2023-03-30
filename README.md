<h1 align="center">Izoh</h1></br>

<p align="center">
:page_facing_up: Izoh is a lightweight logger library for Android.
</p><br>

<p align="center">
  <a href="https://github.com/behzod1996/izoh"><img width="210px" alt="Logo" src="https://github.com/behzod1996/izoh/blob/master/docs/images/01.%20izoh-logo.png?raw=true"/></a> <br>
</p>


## Installl


Add the dependency below into your **module**'s `build.gradle` file:

```gradle
dependencies {
    implementation("io.github.behzod1996:izoh:$version")
}
```

## Izoh

<b>`Izoh`</b> is a tool that serves as a primary log manager, facilitating the installation of loggers and printing of log messages. To begin using `Izoh`, you must first install an `Izohher` on it. If you are working on an Android project, you can easily install the `AndroidIzohher`, which is a straightforward logger designed for Android.


First, you need to install a logger for Android with `AndroiIzohher` like the below:

```kotlin
class IzohApp : Application() {
    override fun onCreate() {
        super.onCreate()
       
        // Install AndroidIzohher
        AndroidIzohher.install(this)
    }
}
```

>**Note**: It is advisable to install the logger only once within your application class.

Now, you can easily print log messages using the following syntax:

```kotlin
izoh { "This is a izoh logging library" }
```
