# How to access location in Android

1. Set permissions in AndroidManifest.xml

    `<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION"/>`

    `<uses-permission android:name="android.permission.ACCESS_BACKGROUND_LOCATION"/>`

   `<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION"/>`

   `<uses-permission android:name="android.permission.INTERNET"/>`

2. Add dependencies in build.gradle (:app)
 
    `implementation("com.google.android.gms:play-services-location:21.2.0")`

3. See MainActivity.kt
