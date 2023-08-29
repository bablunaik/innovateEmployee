package com.Employee.controller;

import com.Employee.entities.Employee;
import com.Employee.response.TaxDeductionResponse;
import com.Employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@RestController
@RequestMapping("/employees")
public class EmployeeController {

        @Autowired
        private EmployeeService employeeService;

        @PostMapping
        public ResponseEntity<String> createEmployee(@Valid @RequestBody Employee employee) {
            employeeService.createEmployee(employee);
            return ResponseEntity.ok("Employee created successfully");
        }

        @GetMapping("/tax-deduction")
        public ResponseEntity<List<TaxDeductionResponse>> getTaxDeductions() {
            List<TaxDeductionResponse> taxDeductions = employeeService.getTaxDeductions();
            return ResponseEntity.ok(taxDeductions);
        }
    }
