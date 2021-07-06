plugins {
    id("com.android.application")
    id("kotlin-android")
    id("org.jetbrains.kotlin.kapt")
}

android {
    compileSdkVersion(30)
    buildToolsVersion("30.0.3")

    defaultConfig {
        applicationId("com.pdi.pokeproject")
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

        getByName("debug") {
            applicationIdSuffix = ".debug"
            isDebuggable = true
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
    implementation(project(":network"))
    implementation(project(":pokemon_list"))
    implementation(project(":share"))
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.4.32")
    implementation("androidx.core:core-ktx:1.3.2")
    implementation("androidx.appcompat:appcompat:1.2.0")
    implementation("com.google.android.material:material:1.3.0")
    implementation("androidx.constraintlayout:constraintlayout:2.0.4")

    //Testes
    testImplementation("junit:junit:4.+")
    androidTestImplementation("androidx.test.ext:junit:1.1.2")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.3.0")

    //Retrofit - Auxiliar na comunicação com a API
    implementation("com.squareup.retrofit2:retrofit:2.9.0")

    //Gson - Acho que posso remover
    implementation("com.squareup.retrofit2:converter-gson:2.7.1")
    implementation("com.google.code.gson:gson:2.8.6")

    //Glide - Gerenciamento de Imagens
    implementation("com.github.bumptech.glide:glide:4.12.0")
    annotationProcessor("com.github.bumptech.glide:compiler:4.12.0")

    //Dagger2 - Injeção de Dependência
    implementation("com.google.dagger:dagger:2.34.1")
    implementation("com.google.dagger:dagger-android-support:2.34.1")
    kapt("com.google.dagger:dagger-compiler:2.34.1")
    kapt("com.google.dagger:dagger-android-processor:2.34.1")

    //Room - Banco de dados
    //def room_version = "2.3.0"
    implementation("androidx.room:room-runtime:2.3.0")
    kapt("androidx.room:room-compiler:2.3.0")
    //Room - Test helpers
    testImplementation("androidx.room:room-testing:2.3.0")

    //RxJava - Gerenciamento de Threads
    implementation("io.reactivex.rxjava2:rxandroid:2.1.1")
    implementation("io.reactivex.rxjava2:rxjava:2.2.11")
    implementation("io.reactivex.rxjava2:rxkotlin:2.4.0")

    //Mockk - Testes unitários
    testImplementation("io.mockk:mockk:1.11.0")

    //ViewModel - Necessária para funcionar o 'by viewModels()'
    implementation("androidx.fragment:fragment-ktx:1.3.3")
}
