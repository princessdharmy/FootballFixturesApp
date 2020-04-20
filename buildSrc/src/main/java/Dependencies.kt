import sun.misc.Version

object ApplicationId {
    val id = "com.app.footballfixtures"
}

object Modules {
    val app = ":footballfixtures"
    val common = ":common"
    val core = ":core"
    val data = ":data"
    val domain = ":domain"
    val navigation = ":navigation"
    val presentation = ":presentation"

    // Features
    val competitions = ":features:competitions"
    val competitionDetails = ":features:competitiondetails"
}

object Releases {
    val versionCode = 1
    val versionName = "1.0"
}

/**
 * Arranged alphabetically
 */
object Versions {
    val appCompat = "1.1.0"
    val androidSvg = "1.4"
    val androidTestRunner = "1.2.0"
    val androidJunit = "1.1.0"
    val buildTool = "29.0.2"
    val cardViewVersion = "1.0.0"
    val coreKtx = "1.1.0"
    val compileSdkVersion = 29
    val constraintLayout = "1.1.3"
    val coroutines = "1.1.1"
    val dagger = "2.24"
    val espressoCore = "3.2.0"
    val fragment = "1.2.0"
    val glideVersion = "4.9.0"
    val gradle = "3.5.3"
    val gson = "2.8.5"
    val junit = "4.12"
    val kotlin = "1.3.61"
    val lifecycle = "2.1.0"
    val minSdk = 23
    val legacyVersion = "1.0.0"
    val logginInterceptor = "3.8.1"
    val materialDesignVersion = "1.0.0"
    val moshiVersion = "1.9.2"
    val nav = "2.0.0"
    val okHttp = "3.12.1"
    val retrofit = "2.5.0"
    val retrofitCoroutines = "0.9.2"
    val retrofitGson = "2.4.0"
    val roomVersion = "2.2.2"
    val recyclerview = "1.0.0"
    val rxJavaAndroid = "2.1.1"
    val rxJava = "2.2.9"
    val rxJavaKotlin = "2.2.0"
    val safeArgs = "2.1.0-alpha01"
    val shapeImageVersion = "0.9.3@aar"
    val svgDecoderVersion = "1.2.1"
    val targetSdk = 28
    val vectorDrawableVersion = "1.1.0"
}

object Libraries {
    // Dagger core
    val dagger2 = "com.google.dagger:dagger:${Versions.dagger}"
    val daggerCompiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"

    // RxJava
    val rxJavaAndroid = "io.reactivex.rxjava2:rxandroid:${Versions.rxJavaAndroid}"
    val rxJava = "io.reactivex.rxjava2:rxjava:${Versions.rxJava}"
    val rxJavaKotlin = "io.reactivex.rxjava2:rxkotlin:${Versions.rxJavaKotlin}"

    // RETROFIT
    val gson = "com.google.code.gson:gson:${Versions.gson}"
    val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    val retrofitAdapter = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofit}"
    val retrofitGsonConverter = "com.squareup.retrofit2:converter-gson:${Versions.retrofitGson}"
    val httpLoggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.logginInterceptor}"
    val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okHttp}"

    // MOSHI
    val moshi = "com.squareup.moshi:moshi:${Versions.moshiVersion}"

    // VIEWS AND ANIMATIONS
    val shapeImage = "com.github.siyamed:android-shape-imageview:${Versions.shapeImageVersion}"
    val glide =  "com.github.bumptech.glide:glide:${Versions.glideVersion}"
    val glideCompiler = "com.github.bumptech.glide:compiler:${Versions.glideVersion}"
    val svgDecoder = "com.caverock:androidsvg:${Versions.svgDecoderVersion}"
    //val androidSvg = "com.caverock:androidsvg-aar:${Versions.androidSvg}"

}

object KotlinLibraries {
    val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
}

object AndroidLibraries {
    val legacy = "androidx.legacy:legacy-support-v4:${Versions.legacyVersion}"

    // ANDROID
    val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    val recyclerView = "androidx.recyclerview:recyclerview:${Versions.recyclerview}"
    val cardView = "androidx.cardview:cardview:${Versions.cardViewVersion}"
    val vectorDrawable = "androidx.vectordrawable:vectordrawable:${Versions.vectorDrawableVersion}"
    val navigation = "androidx.navigation:navigation-ui-ktx:${Versions.nav}"
    val navigationFrag = "androidx.navigation:navigation-fragment-ktx:${Versions.nav}"
    val fragmentKtx = "androidx.fragment:fragment-ktx:${Versions.fragment}"
    val fragment = "androidx.fragment:fragment:${Versions.fragment}"


    val materialDesign = "com.google.android.material:material:${Versions.materialDesignVersion}"

    // ViewModel and LiveData
    val lifecycleViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    val lifecycleExtensions = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle}"
    val lifecycleCompile = "androidx.lifecycle:lifecycle-compiler:${Versions.lifecycle}"

    // ReactiveStreams support for LiveData
    val lifecycleReactiveStreams = "androidx.lifecycle:lifecycle-reactivestreams-ktx:${Versions.lifecycle}"

    // ROOM
    val room = "androidx.room:room-runtime:${Versions.roomVersion}"
    val roomCompiler = "androidx.room:room-compiler:${Versions.roomVersion}"

    // optional - Kotlin Extensions and Coroutines support for Room
    val roomExtension = "androidx.room:room-ktx:${Versions.roomVersion}"

    // optional - RxJava support for Room
    val roomRxJava = "androidx.room:room-rxjava2:${Versions.roomVersion}"

}

object TestLibraries {
    // ANDROID TEST
    val androidTestRunner = "androidx.test:runner:${Versions.androidTestRunner}"
    val espresso = "androidx.test.espresso:espresso-core:${Versions.espressoCore}"
    val espressoContrib = "androidx.test.espresso:espresso-contrib:${Versions.espressoCore}"
    val junit = "androidx.test.ext:junit:${Versions.androidJunit}"

}