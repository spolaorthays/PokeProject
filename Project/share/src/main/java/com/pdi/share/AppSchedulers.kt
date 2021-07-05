package com.pdi.share

import io.reactivex.Scheduler

class AppSchedulers(
        var main: Scheduler,
        var io: Scheduler,
        var computation: Scheduler
)
