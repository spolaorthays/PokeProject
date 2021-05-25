package com.pdi.share

import dagger.Module
import dagger.Provides
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

@Module

class ManageThreadModule {
    @Provides
    fun provideManageThreads() = ManageThreads(AndroidSchedulers.mainThread(), Schedulers.io())
}