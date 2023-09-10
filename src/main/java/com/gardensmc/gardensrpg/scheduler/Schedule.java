package com.gardensmc.gardensrpg.scheduler;

public abstract class Schedule {

    public Schedule() {
        ScheduleHandler.schedules.add(this);
    }

    public abstract void schedule();
}
