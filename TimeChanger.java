package com.example.dm1;


import javafx.animation.Timeline;


import java.time.LocalTime;
import java.util.Calendar;

public class TimeChanger  {
        private Timeline timeline;
        private String getColorForTime() {
        Calendar calendar = Calendar.getInstance();
        LocalTime time = LocalTime.of(calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE));
            if (time.isAfter(LocalTime.of(6, 0)) && time.isBefore(LocalTime.of(20, 0))) {

            } else {

            }
            return null;
    }
}
