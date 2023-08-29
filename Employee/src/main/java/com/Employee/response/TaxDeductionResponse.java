package com.Employee.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaxDeductionResponse {

        private String employeeCode;
        private String firstName;
        private String lastName;
        private double yearlySalary;
        private double taxAmount;
        private double cessAmount;

    }


