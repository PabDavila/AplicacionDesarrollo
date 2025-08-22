// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.example.aplicaciondesarrollo"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.aplicaciondesarrollo"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.3"
    }

    buildFeatures {
        compose = true
    }
}

dependencies {
    // Navegación Compose
    implementation("androidx.navigation:navigation-compose:2.8.0")

    // Activity Compose
    implementation("androidx.activity:activity-compose:1.9.0")

    // Compose BOM (maneja versiones)
    implementation(platform("androidx.compose:compose-bom:2024.05.00"))

    // UI Compose
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.ui:ui-tooling-preview")
    debugImplementation("androidx.compose.ui:ui-tooling")
}
