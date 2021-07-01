package com.pdi.network.di.annotations

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ChuckIntercetorQualifier

@Qualifier
@Retention(AnnotationRetention.BINARY) //TODO o que é Binary?
annotation class LoggingIntercetorQualifier