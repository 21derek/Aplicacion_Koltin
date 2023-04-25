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
            "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi"
        )
    }

    namespace = "dev.atick.core.android"
}

dependencies {
    // ... Core Android
    api(libs.androidx.core.ktx)

    // ... Coroutines
    api(libs.kotlinx.coroutines.android)

    // ... Serialization
    api(libs.kotlinx.serialization.json)

    // ... Date-Time
    api(libs.kotlinx.datetime)

    // ... Dagger-Hilt
    api(libs.dagger.hilt.android)

    // ... Logger
    api(libs.orhanbout.logger)
}