package com.Employee.service;

import com.Employee.entities.Employee;
import com.Employee.response.TaxDeductionResponse;

import java.util.List;

public interface EmployeeService {
        Employee createEmployee(Employee employee);
        List<TaxDeductionResponse> getTaxDeductions();
    }


