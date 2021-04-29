package com.pdi.pokeproject

import io.reactivex.Scheduler

class ManageThreads (
    var main: Scheduler,
    var io: Scheduler
)