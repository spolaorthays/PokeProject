package com.pdi.share.annotations

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class IOThreadQualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class MainThreadQualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ComputationThreadQualifier