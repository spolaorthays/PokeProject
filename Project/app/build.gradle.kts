plugins {
    id(Plugins.ANDROID_APPLICATION)
    id(Plugins.ANDROID_KOTLIN)
    id(Plugins.KAPT)
}

android {
    compileSdkVersion(Configs.COMPILE_VERSION)
    buildToolsVersion(Configs.BUILD_TOOLS_VERSION)

    defaultConfig {
        applicationId(Configs.APPLICATION_ID)
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

    buildTypes {
        getByName(Configs.RELEASE) {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile(Configs.PROGARD_OPTIMIZE), Configs.PROGARD_RULES)
        }

        getByName(Configs.DEBUG) {
            applicationIdSuffix = Configs.POINT_DEBUG
            isDebuggable = true
        }
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
    implementation(project(Modules.POKEMON_LIST))
    implementation(project(Modules.SHARE))
    implementation(project(Modules.FAVORITE))

    implementation(Dependencies.KOTLIN_STD_LIBRARY)
    implementation(Dependencies.AndroidLibrarys.APPCOMPAT)
    implementation(Dependencies.AndroidLibrarys.CONSTRAINT_LAYOUT)
    implementation(Dependencies.AndroidLibrarys.CORE_KTX)
    implementation(Dependencies.AndroidLibrarys.MATERIAL_DESIGN)

    //Testes
    testImplementation(Dependencies.UnitTests.JUNIT)
    androidTestImplementation(Dependencies.UnitTests.JUNIT_EXT)
    androidTestImplementation(Dependencies.UnitTests.ESPRESSO)

    //Retrofit - Auxiliar na comunicação com a API
    implementation(Dependencies.Retrofit.RETROFIT)

    //Gson - Acho que posso remover
    implementation(Dependencies.Gson.GSON_CONVERTER)
    implementation(Dependencies.Gson.GSON)

    //Glide - Gerenciamento de Imagens
    implementation(Dependencies.Glide.GLIDE)
    annotationProcessor(Dependencies.Glide.GLIDE_COMPILER)

    //Dagger2 - Injeção de Dependência
    implementation(Dependencies.Dagger.DAGGER)
    implementation(Dependencies.Dagger.DAGGER_ANDROID_SUP)
    kapt(Dependencies.Dagger.DAGGER_COMPILER)
    kapt(Dependencies.Dagger.DAGGER_ANDROID_PROCESSOR)

    //Room - Banco de dados
    implementation(Dependencies.Room.ROOM)
    kapt(Dependencies.Room.ROOM_COMPILER)
    testImplementation(Dependencies.Room.ROOM_TESTING)

    //RxJava - Gerenciamento de Threads
    implementation(Dependencies.RxJava.RXJAVA)
    implementation(Dependencies.RxJava.RXJAVA_ANDROID)
    implementation(Dependencies.RxJava.RXJAVA_KOTLIN)

    //Mockk - Testes unitários
    testImplementation(Dependencies.MOCKK)

    //ViewModel - Necessária para funcionar o 'by viewModels()'
    implementation(Dependencies.VIEW_MODEL)
}
