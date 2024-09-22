package com.example.vacation_calculator.service.calculator;

import com.example.vacation_calculator.model.VacationRequest;
import com.example.vacation_calculator.service.holidays.HolidayChecker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class CalculatorServiceImpl implements CalculatorService {

    private static final double AVG_MONTH_DAYS = 29.3;
    private final HolidayChecker checker;

    private double calculateWithoutDate(VacationRequest request) {
        return request.getAverageSalary() / AVG_MONTH_DAYS * request.getVacationDays();
    }

    private double calculateWithDate(VacationRequest request) {
        LocalDate startDate = request.getStartDate();
        int vacationDays = request.getVacationDays();

        for (int i = 0; i < request.getVacationDays(); i++) {
            if (isHolidayOrWeekendDay(startDate)) {
                vacationDays--;
            }
            startDate = startDate.plusDays(1);
        }

        return vacationDays * request.getAverageSalary() / AVG_MONTH_DAYS;
    }

    private boolean isHolidayOrWeekendDay(LocalDate date) {
        return checker.isWeekend(date) || checker.isHoliday(date);
    }

    @Override
    public double calculateVacationPay(VacationRequest request) {
        if (request.getStartDate() != null) {
            return calculateWithDate(request);
        } else {
            return calculateWithoutDate(request);
        }
    }
}
