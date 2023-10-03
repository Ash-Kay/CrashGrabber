# CrashGrabber

CrashGrabber simplifies finding the crash during development. It intercepts the crashes and save the logs and its meta data locally, providing a UI for inspecting and sharing their details.

## Features
1. **Crash Log Detection:** Automatically captures crash logs when your app encounters an unhandled exception, ensuring you never miss critical information about crashes.
2. **User-Friendly UI interface:**  Crash logs are presented in a human-readable format, making it easier for developers to understand the root cause of the crash and easier for QA to report crashes. It also adds a shortcut to your app, allowing you to see all the past crashes easily while adding ability to share crashes.
3. **Export Logs:** Crash logs can be easily be exported and shared.

# Getting Started
To integrate the CrashGrabber into your project, follow these steps:

CrashGrabber is distributed through Maven Central. To use it you need to add the following Gradle dependency to the build.gradle file of your android app module (NOT the root file).
Please note that you should add both the library and the library-no-op variant to isolate CrashGrabber from release builds as follows:

```gradle
dependencies {
  debugImplementation "io.ashkay.crashgrabber:crashgrabber:x.x.x"
  releaseImplementation "io.ashkay.crashgrabber:crashgrabber-no-op:x.x.x"
}
```

To start using CrashGrabber, just do the folling on you application's `onCreate()`:

```kotlin
CrashGrabber.init(context)
CrashGrabber.createShortcut(context)
```

That's it! 🎉 CrashGrabber will now record crashes in your app.
