package com.pdi.network.di.annotations

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ChuckInterceptorQualifier

@Qualifier
@Retention(AnnotationRetention.BINARY) //TODO o que é Binary?
annotation class LoggingInterceptorQualifier

@Qualifier
@Retention(AnnotationRetention.BINARY) //TODO o que é Binary?
annotation class BaseUrlQualifier