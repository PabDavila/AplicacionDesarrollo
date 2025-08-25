plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.example.aplicaciondesarrollo"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.aplicaciondesarrollo"
        minSdk = 31
        targetSdk = 34
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

    // 🔹 Activamos Compose
    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.3" // Use latest compatible version for Compose.
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)

    // 🔹 Jetpack Compose BOM for automatic version management
    implementation(platform("androidx.compose:compose-bom:2024.05.00"))

    // 🔹 UI Compose
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.ui:ui-tooling-preview")

    // 🔹 Navigation for Compose
    implementation("androidx.navigation:navigation-compose:2.8.0")

    // 🔹 Activity Compose
    implementation("androidx.activity:activity-compose:1.9.0")

    // 🔹 Debugging tools
    debugImplementation("androidx.compose.ui:ui-tooling")

    // Testing libraries
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
