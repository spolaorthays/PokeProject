plugins {
    id(Plugins.ANDROID_LIBRARY)
    id(Plugins.ANDROID_KOTLIN)
    id(Plugins.KAPT)
}

android {
    compileSdkVersion(Configs.COMPILE_VERSION)
    buildToolsVersion(Configs.BUILD_TOOLS_VERSION)

    defaultConfig {
        minSdkVersion(Configs.MIN_SDK)
        targetSdkVersion(Configs.TARGET_VERSION)
        versionCode(Configs.VERSION_CODE)
        versionName(Configs.VERSION_NAME)

        testInstrumentationRunner(Configs.JUNIT_RUNNER)

        javaCompileOptions {
            annotationProcessorOptions {
                arguments[Configs.ROOM_INCREMENTAL] = Configs.TRUE_STRING
                arguments[Configs.KAPT_GENERATED] = Configs.TRUE_STRING
            }
        }
    }

    buildFeatures {
        dataBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = Configs.JVM_TARGET
    }
}

dependencies {
    implementation(project(Modules.NETWORK))
    implementation(project(Modules.SHARE))

    implementation(Dependencies.KOTLIN_STD_LIBRARY)
    implementation(Dependencies.AndroidLibrarys.APPCOMPAT)
    implementation(Dependencies.AndroidLibrarys.CONSTRAINT_LAYOUT)
    implementation(Dependencies.AndroidLibrarys.CORE_KTX)
    implementation(Dependencies.AndroidLibrarys.MATERIAL_DESIGN)

    testImplementation(Dependencies.UnitTests.JUNIT)
    androidTestImplementation(Dependencies.UnitTests.JUNIT_EXT)
    androidTestImplementation(Dependencies.UnitTests.ESPRESSO)

    implementation(Dependencies.Retrofit.RETROFIT)

    implementation(Dependencies.Gson.GSON_CONVERTER)
    implementation(Dependencies.Gson.GSON)

    implementation(Dependencies.Dagger.DAGGER)
    implementation(Dependencies.Dagger.DAGGER_ANDROID_SUP)
    kapt(Dependencies.Dagger.DAGGER_COMPILER)
    kapt(Dependencies.Dagger.DAGGER_ANDROID_PROCESSOR)

    implementation(Dependencies.RxJava.RXJAVA)
    implementation(Dependencies.RxJava.RXJAVA_ANDROID)
    implementation(Dependencies.RxJava.RXJAVA_KOTLIN)

    testImplementation(Dependencies.MOCKK)

    implementation(Dependencies.VIEW_MODEL)

    //Palette - Lib de cores
    implementation(Dependencies.PALETTE)

    implementation(Dependencies.Glide.GLIDE)
    annotationProcessor(Dependencies.Glide.GLIDE_COMPILER)
}