package com.Employee.service.impl;

import com.Employee.entities.Employee;
import com.Employee.repository.EmployeeRepository;
import com.Employee.response.TaxDeductionResponse;
import com.Employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public List<TaxDeductionResponse> getTaxDeductions() {
        List<Employee> employees = employeeRepository.findAll();
        List<TaxDeductionResponse> taxDeductions = new ArrayList<>();

        for (Employee employee : employees) {
            double yearlySalary = calculateYearlySalary(employee);
            double taxAmount = calculateTaxAmount(yearlySalary);
            double cessAmount = calculateCessAmount(yearlySalary);

            TaxDeductionResponse response = new TaxDeductionResponse();
            response.setEmployeeCode(employee.getEmployeeId());
            response.setFirstName(employee.getFirstName());
            response.setLastName(employee.getLastName());
            response.setYearlySalary(yearlySalary);
            response.setTaxAmount(taxAmount);
            response.setCessAmount(cessAmount);

            taxDeductions.add(response);
        }

        return taxDeductions;
    }

    private double calculateYearlySalary(Employee employee) {
        LocalDate today = LocalDate.now();

        int fullMonthsWorked = Period.between(employee.getDoj(), today).getYears() * 12 +
                Period.between(employee.getDoj(), today).getMonths();
        int daysInCurrentMonth = today.getMonth().length(today.isLeapYear());

        if (daysInCurrentMonth - today.getDayOfMonth() < 15) {
            fullMonthsWorked--; // Reduce a month if less than 15 days remain in the current month
        }

        double totalSalary = employee.getSalary() * fullMonthsWorked;
        return totalSalary;
    }

    private double calculateTaxAmount(double yearlySalary) {
        double taxAmount = 0.0;

        if (yearlySalary > 1000000) {
            taxAmount = yearlySalary * 0.20;
        } else if (yearlySalary > 500000) {
            taxAmount = (yearlySalary - 500000) * 0.10 + 25000;
        } else if (yearlySalary > 250000) {
            taxAmount = (yearlySalary - 250000) * 0.05;
        }

        return taxAmount;
    }

    private double calculateCessAmount(double yearlySalary) {
        double cessAmount = 0.0;

        if (yearlySalary > 2500000) {
            cessAmount = (yearlySalary - 2500000) * 0.02;
        }

        return cessAmount;
    }
}
