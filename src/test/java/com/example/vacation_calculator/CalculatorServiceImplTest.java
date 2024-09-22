package com.example.vacation_calculator;

import com.example.vacation_calculator.model.VacationRequest;
import com.example.vacation_calculator.service.calculator.CalculatorService;
import com.example.vacation_calculator.service.calculator.CalculatorServiceImpl;
import com.example.vacation_calculator.service.holidays.HolidayChecker;
import com.example.vacation_calculator.service.holidays.HolidayCheckerImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.time.Month;

public class CalculatorServiceImplTest {
    @Test
    void calculateVacationPay_WithoutStartDate_ShouldReturnCorrectValue() {
        CalculatorService calculatorService = new CalculatorServiceImpl(Mockito.mock(HolidayChecker.class));
        VacationRequest request = new VacationRequest(16000.0, 14, null);
        double expectedResult = 16000.0 * 14 / 29.3;

        double result = calculatorService.calculateVacationPay(request);

        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    void calculateVacationPay_WithStartDate_ShouldReturnCorrectValue() {
        HolidayCheckerImpl checker = Mockito.mock(HolidayCheckerImpl.class);
        CalculatorService calculatorService = new CalculatorServiceImpl(checker);

        Mockito.when(checker.isHoliday(LocalDate.of(2022, Month.JANUARY, 1))).thenReturn(true);
        Mockito.when(checker.isWeekend(LocalDate.of(2022, Month.JANUARY, 2))).thenReturn(true);
        Mockito.when(checker.isWeekend(LocalDate.of(2022, Month.JANUARY, 8))).thenReturn(true);
        Mockito.when(checker.isWeekend(LocalDate.of(2022, Month.JANUARY, 9))).thenReturn(true);


        VacationRequest request = new VacationRequest(16000.0, 14, LocalDate.of(2022, Month.JANUARY, 1));
        double expectedResult = 16000.0 * (14 - 4) / 29.3;

        double result = calculatorService.calculateVacationPay(request);

        Assertions.assertEquals(expectedResult, result);
    }
}
