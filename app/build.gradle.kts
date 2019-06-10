plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
    id("kotlin-android")
    id("kotlin-android-extensions")
    id("kotlin-kapt")
    id("androidx.navigation.safeargs.kotlin")
    id("com.google.gms.google-services")
}

android {
    compileSdkVersion(28)
    defaultConfig {
        applicationId = "com.hjalmarsson.fashion"
        minSdkVersion(23)
        targetSdkVersion(28)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFile(getDefaultProguardFile("proguard-android.txt"))
            proguardFile("proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    packagingOptions {
        exclude("META-INF/DEPENDENCIES")
    }
}

dependencies {
    val dagger_version = "2.21"
    val retrofit_version = "2.5.0"
    val moshi_version = "1.8.0"
    val kotlin_version = rootProject.extra["kotlin_version"] as String
    val nav_version = rootProject.extra["nav_version"] as String

    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlin_version")
    implementation("androidx.appcompat:appcompat:1.0.2")
    implementation("androidx.core:core-ktx:1.0.2")
    implementation("com.google.android.material:material:1.0.0")
    implementation("androidx.constraintlayout:constraintlayout:1.1.3")

    kapt("com.google.dagger:dagger-compiler:$dagger_version")
    implementation("com.google.dagger:dagger:$dagger_version")


    implementation("io.reactivex.rxjava2:rxjava:2.2.8")
    implementation("io.reactivex.rxjava2:rxkotlin:2.3.0")
    implementation("io.reactivex.rxjava2:rxandroid:2.1.1")

    implementation("androidx.viewpager2:viewpager2:1.0.0-alpha04")

    implementation("com.jakewharton.timber:timber:4.7.1")

    implementation("android.arch.navigation:navigation-ui-ktx:$nav_version")
    implementation("android.arch.navigation:navigation-fragment-ktx:$nav_version")

    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    implementation("androidx.lifecycle:lifecycle-common-java8:2.0.0")
    implementation("androidx.lifecycle:lifecycle-extensions:2.0.0")

    implementation("androidx.fragment:fragment:1.0.0")
    implementation("com.android.support:design:28.0.0")
    implementation("com.google.android.material:material:1.1.0-alpha07")
    implementation("androidx.constraintlayout:constraintlayout:2.0.0-beta1")

    implementation("com.mikepenz:fastadapter:3.3.1")
    implementation("com.mikepenz:fastadapter-commons:3.3.1")
    implementation("androidx.recyclerview:recyclerview:1.0.2")

    implementation("com.squareup.moshi:moshi:$moshi_version")
    implementation("com.squareup.moshi:moshi-kotlin:$moshi_version")
    implementation("com.squareup.moshi:moshi-adapters:$moshi_version")
    implementation("com.squareup.retrofit2:retrofit:$retrofit_version")
    implementation("com.squareup.retrofit2:converter-moshi:$retrofit_version")
    implementation("com.squareup.retrofit2:adapter-rxjava2:$retrofit_version")

    implementation("com.google.firebase:firebase-core:16.0.9")
    implementation("com.google.firebase:firebase-database:17.0.0")
}
