package com.raahi.reddit.utils.rx

import io.reactivex.Scheduler

interface SchedulerProvider {

    fun ui(): Scheduler
    fun io(): Scheduler
}