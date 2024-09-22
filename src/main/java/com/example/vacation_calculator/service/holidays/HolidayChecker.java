package com.example.vacation_calculator.service.holidays;

import java.time.LocalDate;

public interface HolidayChecker {

    boolean isHoliday(LocalDate date);

    boolean isWeekend(LocalDate date);
}
