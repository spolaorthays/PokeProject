import org.jetbrains.kotlin.config.KotlinCompilerVersion

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("org.jetbrains.kotlin.kapt")
}

//repositories {
//    google()
//}

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
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
                targetCompatibility = JavaVersion.VERSION_1_8
    }

    // To avoid the compile error: "Cannot inline bytecode built with JVM target 1.8
    // into bytecode that is being built with JVM target 1.6"
//    kotlinOptions {
//        val options = this as org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions
//        options.jvmTarget = "1.8"
//    }
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

    //Retrofit - Auxiliar na comunicação com a API - Acho que posso remover
    implementation("com.squareup.retrofit2:retrofit:2.9.0")

    //Gson - Acho que posso remover
    implementation("com.squareup.retrofit2:converter-gson:2.7.1")
    implementation("com.google.code.gson:gson:2.8.6")

    //Picasso - Gerenciamento de Imagens - Acho que posso remover
    implementation("com.squareup.picasso:picasso:2.71828")

    //Dagger2 - Injeção de Dependência - Acho que posso remover
    implementation("com.google.dagger:dagger:2.34.1")
    implementation("com.google.dagger:dagger-android-support:2.34.1")
    kapt("com.google.dagger:dagger-compiler:2.34.1")
    kapt("com.google.dagger:dagger-android-processor:2.34.1")

    //Room - Banco de dados - Acho que posso remover
    //def room_version = "2.3.0"
    implementation("androidx.room:room-runtime:2.3.0")
    kapt("androidx.room:room-compiler:2.3.0")
    //Room - Test helpers
    testImplementation("androidx.room:room-testing:2.3.0")

    //RxJava - Gerenciamento de Threads - Acho que posso remover
    implementation("io.reactivex.rxjava2:rxandroid:2.1.1")
    implementation("io.reactivex.rxjava2:rxjava:2.2.11")
    implementation("io.reactivex.rxjava2:rxkotlin:2.4.0")

    //Mockk - Testes unitários
    testImplementation("io.mockk:mockk:1.11.0")

    //ViewModel - Necessária para funcionar o 'by viewModels()' - Acho que posso remover
    implementation("androidx.fragment:fragment-ktx:1.3.3")

    //Android-Gif-Drawable - Renderiza frames para disponibilizar Gifs Animados
    implementation("pl.droidsonroids.gif:android-gif-drawable:1.2.22")

    //Palette - Lib de cores - Acho que posso remover
    implementation("androidx.palette:palette-ktx:1.0.0")
}
