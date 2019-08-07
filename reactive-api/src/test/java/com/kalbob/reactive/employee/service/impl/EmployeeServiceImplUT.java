package com.kalbob.reactive.employee.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.kalbob.reactive.config.service.BaseServiceUT;
import com.kalbob.reactive.employee.Employee;
import com.kalbob.reactive.employee.EmployeeMother;
import com.kalbob.reactive.employee.repository.EmployeeRepository;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

public class EmployeeServiceImplUT extends BaseServiceUT {

  private EmployeeServiceImpl employeeServiceImpl;

  @Mock
  private EmployeeRepository employeeRepository;

  @BeforeEach
  public void beforeEach() {
    employeeServiceImpl = new EmployeeServiceImpl(employeeRepository);
  }

  @Test
  public void getAllEmployees() {
    when(employeeRepository.findAll()).thenReturn(
        Arrays.asList(EmployeeMother.simple(), EmployeeMother.complete()));
    List<Employee> employeeList = employeeServiceImpl.getAllEmployees();
    assertEquals(2, employeeList.size());
  }

}
