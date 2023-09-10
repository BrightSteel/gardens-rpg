package com.gardensmc.gardensrpg.scheduler;

import com.gardensmc.gardensrpg.scheduler.schedules.AbilityCooldownsSchedule;
import com.gardensmc.gardensrpg.scheduler.schedules.NecroMobDespawnSchedule;

import java.util.ArrayList;
import java.util.List;

public class ScheduleHandler {

    public static final List<Schedule> schedules = new ArrayList<>();

    public void scheduleAll() {
        createSchedules();
        for (Schedule schedule : schedules) {
            schedule.schedule();
        }
    }

    private void createSchedules() {
        new AbilityCooldownsSchedule();
        new NecroMobDespawnSchedule();
//        new SpawnActiveParticlesSchedule();
    }
}
