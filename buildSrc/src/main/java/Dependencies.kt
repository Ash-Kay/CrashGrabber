object Dependencies {
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.3.72"
    const val kotlinGradle = "org.jetbrains.kotlin:kotlin-gradle-plugin:1.3.72"
    const val gradle = "com.android.tools.build:gradle:4.0.0"
    const val daggerHilt = "com.google.dagger:hilt-android-gradle-plugin:2.28-alpha"
    const val ktLint = "org.jlleitschuh.gradle:ktlint-gradle:9.2.1"
    const val materialDesign = "com.google.android.material:material:1.3.0-alpha01"
}

object Android {
    const val appcompat = "androidx.appcompat:appcompat:1.1.0"
    const val activityKtx = "androidx.activity:activity-ktx:1.1.0"
    const val coreKtx = "androidx.core:core-ktx:1.2.0"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:1.1.3"
    const val swipeRefreshLayout = "androidx.swiperefreshlayout:swiperefreshlayout:1.0.0"
    const val fragmentKtx = "androidx.fragment:fragment-ktx:1.2.5"
    const val databinding = "com.android.databinding:compiler:3.1.4"
}

object Hilt {
    private const val hiltVersion = "2.44"
    const val hiltAndroid = "com.google.dagger:hilt-android:${hiltVersion}"
    const val hiltKapt = "com.google.dagger:hilt-compiler:${hiltVersion}"
}

object Dagger {
    private const val daggerVersion = "2.47"
    const val dagger =  "com.google.dagger:dagger:${daggerVersion}"
    const val daggerKapt =  "com.google.dagger:dagger-compiler:${daggerVersion}"
}

object ViewPager {
    const val viewPager2  = "androidx.viewpager2:viewpager2:1.0.0"
}

object GoogleServices {
    private const val googlePlayServicesAuth = "18.0.0"
    const val auth = "com.google.android.gms:play-services-auth:${googlePlayServicesAuth}"
    const val googleServices = "com.google.gms:google-services:4.3.3"
}

object Firebase{
    const val firebaseAnalytics = "com.google.firebase:firebase-analytics:17.2.2"
}

object NavigationComponent {
    private const val navigation = "2.3.0"
    const val fragment = "androidx.navigation:navigation-fragment-ktx:${navigation}"
    const val ui = "androidx.navigation:navigation-ui-ktx:${navigation}"
    const val runtime = "androidx.navigation:navigation-runtime-ktx:2.2.0-rc01"
}

object OkHttp3 {
    private const val retrofit_log = "3.10.0"
    const val loggingInterceptor =
        "com.squareup.okhttp3:logging-interceptor:${retrofit_log}"
    const val okHttp3 = "com.squareup.okhttp3:okhttp:3.12.1"
}

object Retrofit2 {
    private const val retrofitVersion = "2.5.0"
    const val adapterRxjava2 = "com.squareup.retrofit2:adapter-rxjava2:${retrofitVersion}"
    const val converterGson = "com.squareup.retrofit2:converter-gson:${retrofitVersion}"
    const val retrofit = "com.squareup.retrofit2:retrofit:${retrofitVersion}"
}

object RxJava {
    private const val rx = "2.1.0"
    private const val rxAndroidVersion = "2.0.1"
    const val rxAndroid = "io.reactivex.rxjava2:rxandroid:${rxAndroidVersion}"
    const val rxjava2 = "io.reactivex.rxjava2:rxjava:${rx}"
}

object Chucker {
    private const val chucker = "3.2.0"
    const val debug = "com.github.ChuckerTeam.Chucker:library:${chucker}"
    const val release = "com.github.ChuckerTeam.Chucker:library-no-op:${chucker}"
}

object LeakCanary {
    private const val leakCanaryVersion = "2.3"
    const val leakCanary = "com.squareup.leakcanary:leakcanary-android:${leakCanaryVersion}"
}

object Paging {
    private const val pagingVersion= "2.1.2"
    const val paging = "android.arch.paging:runtime:${pagingVersion}"
    const val pagingRx = "androidx.paging:paging-rxjava2:${pagingVersion}"
}

object Timber {
    private const val timberVersion = "4.7.1"
    const val timber = "com.jakewharton.timber:timber:${timberVersion}"
}

object Facebook {
    private const val stethoVersion = "1.5.1"
    const val stetho = "com.facebook.stetho:stetho:${stethoVersion}"
    const val stethoNetwork = "com.facebook.stetho:stetho-okhttp3:${stethoVersion}"
}

object Glide {
    private const val glideVersion = "4.9.0"
    const val glide = "com.github.bumptech.glide:glide:${glideVersion}"
    const val annotationProcessor = "androidx.annotation:annotation:1.0.0"
    const val annotationCompiler = "com.github.bumptech.glide:compiler:${glideVersion}"
}

object Room {
    private const val room = "2.5.2"
    const val runtime = "androidx.room:room-runtime:${room}"
    const val ktx = "androidx.room:room-ktx:${room}"
    const val annotationProcessor = "androidx.room:room-compiler:${room}"
}

object Test {
    const val junit = "junit:junit:4.13.1"
    const val junitImpl = "androidx.test.ext:junit:1.1.2"
    const val espresso = "androidx.test.espresso:espresso-core:3.3.0"
}