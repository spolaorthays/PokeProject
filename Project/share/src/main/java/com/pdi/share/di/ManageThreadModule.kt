package com.pdi.share.di

import com.pdi.share.ManageThreads
import dagger.Module
import dagger.Provides
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

@Module

class ManageThreadModule {
    @Provides
    fun provideManageThreads() = ManageThreads(AndroidSchedulers.mainThread(), Schedulers.io())
}