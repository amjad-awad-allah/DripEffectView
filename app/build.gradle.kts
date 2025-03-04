plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.amjad.fluid"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.amjad.fluid"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(platform(libs.androidx.compose.bom.v20230300))

    // Jetpack Compose Core
    implementation(libs.androidx.compose.ui.ui)
    implementation(libs.androidx.compose.material.material)
    implementation(libs.androidx.compose.ui.ui.tooling.preview)

    // Lifecycle & Coroutines
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.ui)

    // Compose Runtime
    implementation(libs.androidx.runtime.livedata.v150)

    // Activity & UI
    implementation(libs.androidx.activity.compose.v172)
    implementation(libs.androidx.ui.v150)
    implementation(libs.androidx.material3.android)

    // Debug tools for Compose
    debugImplementation(libs.ui.tooling)
    debugImplementation(libs.ui.test.manifest)

    // Core Dependencies
    implementation(libs.androidx.core.ktx.v1101)
    implementation(libs.androidx.appcompat)
}
