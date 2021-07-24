object Dependencies {

    const val KOTLIN_STD_LIBRARY = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.KOTLIN}"
    const val MOCKK = "io.mockk:mockk:${Versions.MOCKK}"
    const val VIEW_MODEL = "androidx.fragment:fragment-ktx:${Versions.FRAG_KTX}"
    const val PALETTE = "androidx.palette:palette-ktx:${Versions.PALETTE}"

    object AndroidLibrarys {
        const val CORE_KTX = "androidx.core:core-ktx:${Versions.KTX}"
        const val APPCOMPAT = "androidx.appcompat:appcompat:${Versions.APPCOMPAT}"
        const val MATERIAL_DESIGN = "com.google.android.material:material:${Versions.MATERIAL}"
        const val CONSTRAINT_LAYOUT = "androidx.constraintlayout:constraintlayout:${Versions.CONSTRAINT}"
    }

    object Retrofit {
        const val RETROFIT = "com.squareup.retrofit2:retrofit:${Versions.RETROFIT}"
    }

    object Gson {
        const val GSON_CONVERTER = "com.squareup.retrofit2:converter-gson:${Versions.GSON_CONVERTER}"
        const val GSON = "com.google.code.gson:gson:${Versions.GSON}"
    }

    object UnitTests {
        const val JUNIT = "junit:junit:${Versions.JUNIT}"
        const val JUNIT_EXT = "androidx.test.ext:junit:${Versions.JUNIT_EXT}"
        const val ESPRESSO = "androidx.test.espresso:espresso-core:${Versions.ESPRESSO}"
    }

    object Glide {
        const val GLIDE = "com.github.bumptech.glide:glide:${Versions.GLIDE}"
        const val GLIDE_COMPILER = "com.github.bumptech.glide:compiler:${Versions.GLIDE}"
    }

    object Dagger {
        const val DAGGER = "com.google.dagger:dagger:${Versions.DAGGER}"
        const val DAGGER_ANDROID_SUP = "com.google.dagger:dagger-android-support:${Versions.DAGGER}"
        const val DAGGER_COMPILER = "com.google.dagger:dagger-compiler:${Versions.DAGGER}"
        const val DAGGER_ANDROID_PROCESSOR = "com.google.dagger:dagger-android-processor:${Versions.DAGGER}"
    }

    object Room {
        const val ROOM = "androidx.room:room-runtime:${Versions.ROOM}"
        const val ROOM_COMPILER = "androidx.room:room-compiler:${Versions.ROOM}"
        const val ROOM_TESTING = "androidx.room:room-testing:${Versions.ROOM}"
    }

    object RxJava {
        const val RXJAVA = "io.reactivex.rxjava2:rxjava:${Versions.RXJAVA}"
        const val RXJAVA_ANDROID = "io.reactivex.rxjava2:rxandroid:${Versions.RXJAVA_ANDROID}"
        const val RXJAVA_KOTLIN = "io.reactivex.rxjava2:rxkotlin:${Versions.RXJAVA_KOTLIN}"
        const val RXJAVA_ADAPTER = "com.squareup.retrofit2:adapter-rxjava2:${Versions.RXJAVA_ADAPTER}"
        const val RXJAVA_ADAPTER_JAKE = "com.jakewharton.retrofit:retrofit2-rxjava2-adapter:${Versions.RXJAVA_ADAPTER_JAKE}"
    }

    object Interceptors {
        const val CHUCK = "com.readystatesoftware.chuck:library:${Versions.CHUCK}"
        const val LOGGING = "com.squareup.okhttp3:logging-interceptor:${Versions.LOGGING}"
    }
}