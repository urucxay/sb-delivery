plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-android-extensions")
    id("kotlin-kapt")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    compileSdkVersion(29)
    buildToolsVersion = "29.0.3"

    defaultConfig {
        applicationId = "ru.skillbranch.sbdelivery"
        minSdkVersion(23)
        targetSdkVersion(29)
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    androidExtensions {
        isExperimental = true
    }
}

dependencies {
    //Versions
    val kotlin = "1.3.72"
    val constraint = "2.0.0-beta8"
    val liveData = "2.2.0"
    val material = "1.3.0-alpha02"
    val navigation = "2.3.0"
    val paging = "2.1.2"
    val coroutines = "1.3.4"
    val glide = "4.11.0"
    val koin = "2.0.1"
    val loggingInterceptor = "4.8.0"
    val retrofit = "2.9.0"
    val moshi = "1.9.3"
    val preferences = "1.1.1"
    val room = "2.2.5"
    val timber = "4.7.1"
    val materialDrawer = "8.1.3"
    val annotation = "1.1.0"

    //Core
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation("org.jetbrains.kotlin:kotlin-stdlib:$kotlin")
    implementation("androidx.core:core-ktx:1.3.0")
    implementation("androidx.appcompat:appcompat:1.1.0")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")

    //Constraint
    implementation("androidx.constraintlayout:constraintlayout:$constraint")

    //LiveData
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$liveData")
    implementation("androidx.lifecycle:lifecycle-extensions:$liveData")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$liveData")

    //Material
    implementation("com.google.android.material:material:$material")

    //Navigation
    implementation("androidx.navigation:navigation-fragment-ktx:$navigation")
    implementation("androidx.navigation:navigation-ui-ktx:$navigation")

    //Paging
    implementation("androidx.paging:paging-runtime-ktx:$paging")

    //Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutines")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutines")

    //Glide
    implementation("com.github.bumptech.glide:glide:$glide")
    kapt("com.github.bumptech.glide:compiler:$glide")

    //Koin
    implementation("org.koin:koin-java:$koin")
    implementation("org.koin:koin-test:$koin")
    implementation("org.koin:koin-android:$koin")
    implementation("org.koin:koin-androidx-scope:$koin")
    implementation("org.koin:koin-androidx-viewmodel:$koin")

    //Shared Preferences
    implementation("androidx.preference:preference-ktx:$preferences")

    //Room
    implementation("androidx.room:room-runtime:$room")
    implementation("androidx.room:room-ktx:$room")
    kapt("androidx.room:room-compiler:$room")

    //Network
    implementation("com.squareup.okhttp3:logging-interceptor:$loggingInterceptor")
    implementation("com.squareup.retrofit2:retrofit:$retrofit")
    implementation("com.squareup.retrofit2:converter-moshi:$retrofit")
    implementation("com.squareup.moshi:moshi-kotlin:$moshi")
    kapt("com.squareup.moshi:moshi-kotlin-codegen:$moshi")

    //Timber
    implementation("com.jakewharton.timber:timber:$timber")

    //MaterialDrawer
    implementation("com.mikepenz:materialdrawer:$materialDrawer")
    implementation("com.mikepenz:materialdrawer-nav:$materialDrawer")
    implementation("androidx.annotation:annotation:$annotation")

    //Test
    testImplementation("junit:junit:4.13")
    androidTestImplementation("androidx.test.ext:junit:1.1.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.2.0")
}