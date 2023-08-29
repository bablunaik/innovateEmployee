package com.Employee.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Employee {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @NotBlank
        @Column(name = "employee_id")
        private String employeeId;

        @NotBlank
        private String firstName;

        @NotBlank
        private String lastName;

        @NotBlank
        @Email
        private String email;

        @ElementCollection
        @CollectionTable(name = "phone_numbers", joinColumns = @JoinColumn(name = "employee_id"))
        @Column(name = "phone_number")
        private Set<String> phoneNumbers = new HashSet<>();

        @NotNull
        private LocalDate doj;

        @NotNull
        private Double salary;


}
