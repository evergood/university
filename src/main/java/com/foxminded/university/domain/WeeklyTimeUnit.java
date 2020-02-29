package com.foxminded.university.domain;

import lombok.Builder;
import lombok.Data;

import java.time.DayOfWeek;
import java.time.LocalTime;

@Data
@Builder
public class WeeklyTimeUnit {
    private final Integer id;
    private final LocalTime startTime;
    private final LocalTime endTime;
    private final DayOfWeek dayOfWeek;
}
