/*
 * Copyright 2023 Atick Faisal
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

@file:Suppress("UnstableApiUsage")

plugins {
    alias(libs.plugins.kotlin)
    alias(libs.plugins.android.library)
}

android {
    val javaVersion = libs.versions.java.get().toInt()
    val compileSdkVersion = libs.versions.compileSdk.get().toInt()
    val minSdkVersion = libs.versions.minSdk.get().toInt()
    val composeCompilerVersion = libs.versions.androidxComposeCompiler.get().toString()

    compileSdk = compileSdkVersion

    defaultConfig {
        minSdk = minSdkVersion
        consumerProguardFiles("consumer-rules.pro")
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
        sourceCompatibility = JavaVersion.values()[javaVersion - 1]
        targetCompatibility = JavaVersion.values()[javaVersion - 1]
    }

    kotlinOptions {
        jvmTarget = "$javaVersion"
        freeCompilerArgs = freeCompilerArgs + listOf(
            "-opt-in=kotlin.RequiresOptIn",
            "-opt-in=kotlinx.coroutines.FlowPreview",
            "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
            "-opt-in=androidx.compose.material3.ExperimentalMaterial3Api"
        )
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = composeCompilerVersion
    }

    namespace = "dev.atick.core.ui"
}

dependencies {
    // ... Modules
    api(project(":core:android"))

    // ... AppCompat
    api(libs.androidx.appcompat)

    // ... Fragment
    api(libs.androidx.fragment.ktx)

    // ... Activity
    api(libs.androidx.activity.ktx)
    api(libs.androidx.activity.compose)

    // ... Lifecycle
    api(libs.androidx.lifecycle.runtime.ktx)
    api(libs.androidx.lifecycle.livedata.ktx)
    api(libs.androidx.lifecycle.viewmodel.ktx)
    api(libs.androidx.lifecycle.runtimeCompose)
    api(libs.androidx.lifecycle.viewModelCompose)

    // ... Jetpack Compose
    api(platform(libs.androidx.compose.bom))
    api(libs.androidx.compose.runtime)
    api(libs.androidx.compose.foundation)
    api(libs.androidx.compose.ui.util)
    api(libs.androidx.compose.material3)
    api(libs.androidx.compose.material3.windowSizeClass)
    api(libs.androidx.compose.material.iconsExtended)
    api(libs.androidx.compose.ui.tooling.preview)
    debugApi(libs.androidx.compose.ui.tooling)

    // ... Navigation
    api(libs.androidx.navigation.fragment)
    api(libs.androidx.navigation.compose)

    // ... Lottie
    api(libs.lottie.compose)
}