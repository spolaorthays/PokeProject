package com.pdi.share

import io.reactivex.Scheduler

class ManageThreads(
    var main: Scheduler,
    var io: Scheduler
)
