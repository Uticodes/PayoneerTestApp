package com.uticodes.payoneerandroidtest.di;


import com.uticodes.payoneerandroidtest.common.AppScheduler;
import com.uticodes.payoneerandroidtest.common.Scheduler;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public abstract class SchedulerModule {

    @Binds
    public abstract Scheduler bindScheduler(AppScheduler appScheduler);
}