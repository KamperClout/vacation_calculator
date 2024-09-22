package com.example.vacation_calculator.model;


import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class VacationRequest {
    @NotNull(message = "Salary must be specified")
    @Min(value = 1, message = "Salary must be greater than 0")
    private double averageSalary;
    @NotNull(message = "Vacation days must be specified")
    @Min(value = 1, message = "Vacation days must be greater than 0")
    private int vacationDays;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate startDate;
}
