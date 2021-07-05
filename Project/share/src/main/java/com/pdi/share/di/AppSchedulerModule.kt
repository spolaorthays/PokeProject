package com.pdi.share.di

import com.pdi.share.AppSchedulers
import com.pdi.share.annotations.ComputationThreadQualifier
import com.pdi.share.annotations.IOThreadQualifier
import com.pdi.share.annotations.MainThreadQualifier
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Singleton

@Module
class AppSchedulerModule {

    @Singleton
    @Provides
    fun provideAppSchedulers(
            @MainThreadQualifier mainThread: Scheduler,
            @IOThreadQualifier ioThread: Scheduler,
            @ComputationThreadQualifier computation: Scheduler
    ) = AppSchedulers(
            mainThread,
            ioThread,
            computation)

    @MainThreadQualifier
    @Provides
    fun provideMainThread(): Scheduler = AndroidSchedulers.mainThread()

    @IOThreadQualifier
    @Provides
    fun provideIOThread(): Scheduler = Schedulers.io()

    @ComputationThreadQualifier
    @Provides
    fun provideComputationThread(): Scheduler = Schedulers.computation()
}