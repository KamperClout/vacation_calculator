package com.example.vacation_calculator.service.holidays;

import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Component
public class HolidayCheckerImpl implements HolidayChecker {

    private Set<LocalDate> holidays;

    public HolidayCheckerImpl() {
        this.holidays = new HashSet<>();
        initHolidays();
    }

    private void initHolidays() {
        addHoliday(1, 1);
        addHoliday(3, 8);
        addHoliday(5, 1);
        addHoliday(5, 9);
        addHoliday(6, 12);
    }

    private void addHoliday(int month, int day) {
        holidays.add(LocalDate.of(0, month, day));
    }


    public boolean isHoliday(LocalDate date) {
        return holidays.contains(date.withYear(0));
    }


    public boolean isWeekend(LocalDate date) {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY;
    }
}
