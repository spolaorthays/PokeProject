plugins {
    id("com.android.library")
    id("kotlin-android")
    id("org.jetbrains.kotlin.kapt")
}

android {
    compileSdkVersion(30)
    buildToolsVersion("30.0.3")

    defaultConfig {
        minSdkVersion(21)
        targetSdkVersion(30)
        versionCode(1)
        versionName("1.0")

        testInstrumentationRunner("androidx.test.runner.AndroidJUnitRunner")

        javaCompileOptions {
            annotationProcessorOptions {
                arguments["room.incremental"] = "true"
                arguments["kapt.kotlin.generated"] = "true"
            }
        }
    }

    buildFeatures {
        dataBinding = true
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
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
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.4.32")
    implementation("androidx.core:core-ktx:1.3.2")
    implementation("androidx.appcompat:appcompat:1.2.0")
    implementation("com.google.android.material:material:1.3.0")
    implementation("androidx.constraintlayout:constraintlayout:2.0.4")

    //Dagger2 - Injeção de Dependência - Acho que posso remover
    implementation("com.google.dagger:dagger:2.34.1")
    implementation("com.google.dagger:dagger-android-support:2.34.1")
    kapt("com.google.dagger:dagger-compiler:2.34.1")
    kapt("com.google.dagger:dagger-android-processor:2.34.1")

    //RxJava - Gerenciamento de Threads - Acho que posso remover
    implementation("io.reactivex.rxjava2:rxandroid:2.1.1")
    implementation("io.reactivex.rxjava2:rxjava:2.2.11")
    implementation("io.reactivex.rxjava2:rxkotlin:2.4.0")

//    //ViewModel - Necessária para funcionar o 'by viewModels()'
//    implementation "androidx.fragment:fragment-ktx:1.3.3"

    //Picasso - Gerenciamento de Imagens
    implementation("com.squareup.picasso:picasso:2.71828")

}