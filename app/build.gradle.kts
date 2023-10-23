import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import org.jetbrains.kotlin.konan.properties.loadProperties

@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.com.android.application)
    alias(libs.plugins.org.jetbrains.kotlin.android)
}

val entryPointProps = loadProperties("${projectDir.parent}/entrypoint-config.properties")

android {
    namespace = "id.yuana.buildconfig.demo"
    compileSdk = 34

    defaultConfig {
        applicationId = "id.yuana.buildconfig.demo"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        buildConfig = true
        viewBinding = true
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

        buildTypes.onEach {
            it.buildConfigField("Boolean", "ENTRY_POINT_ENABLED", entryPointProps["enabled"] as String)
            it.buildConfigField("id.yuana.buildconfig.demo.config.EntryPoint", "ENTRY_POINT_CONFIG", "new id.yuana.buildconfig.demo.config.EntryPoint(${entryPointProps["entryPoint.activity"] as String}.class, \"${entryPointProps["entryPoint.email"] as String}\", \"${entryPointProps["entryPoint.password"] as String}\")")
        }

    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
}