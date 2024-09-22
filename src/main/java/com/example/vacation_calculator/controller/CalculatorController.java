package com.example.vacation_calculator.controller;

import com.example.vacation_calculator.model.VacationRequest;
import com.example.vacation_calculator.service.calculator.CalculatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class CalculatorController {

    private final CalculatorService calculatorService;

    @GetMapping("/calculate")
    public ResponseEntity<?> calculateVacationPay(@Valid @RequestBody VacationRequest request) {
        try {
            double resultValue = calculatorService.calculateVacationPay(request);
            return ResponseEntity.ok(resultValue);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }
}
