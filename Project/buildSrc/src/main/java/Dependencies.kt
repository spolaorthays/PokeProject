object Dependencies {

    const val kotlinStandardLibrary = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin_version}"

    object AndroidLibrarys {
        const val coreKtx = "androidx.core:core-ktx:${Versions.ktx_version}"
        const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat_version}"
        const val materialDesign = "com.google.android.material:material:1.3.0"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:2.0.4"
    }

    object Retrofit {
        const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit_version}"
    }
}