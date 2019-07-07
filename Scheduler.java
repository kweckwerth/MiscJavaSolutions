package com.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Scheduler {
    List<ScheduleInterface> list = new ArrayList();
    Timer timer = new Timer();

    private class TimerTaskImpl extends TimerTask {
        @Override
        public void run() {
            for (ScheduleInterface si : list) {
                si.Notify();

            }
            timer.cancel();
            // timer.purge();
        }


    }

    public void ScheduleMe(ScheduleInterface schedule, long delay) {
        // schedule.Notify();

        list.add(schedule);


        TimerTask timerTask = new TimerTaskImpl();

        timer.schedule(timerTask, delay);

    }


}
