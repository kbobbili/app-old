package com.kalbob.app.data.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.kalbob.app.data.model.Department;
import com.kalbob.app.data.model.DepartmentMother;
import com.kalbob.app.data.model.Employee;
import com.kalbob.app.data.model.EmployeeMother;
import com.kalbob.app.data.model.Project;
import com.kalbob.app.data.model.ProjectMother;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

public class EmployeeRepositoryTest extends AbstractRepositoryTest {

  @Autowired
  private EmployeeRepository employeeRepository;

  @Test
  public void saveEmployee() {
    Employee employee = EmployeeMother.complete().build();
    employee = employeeRepository.saveAndFlush(employee);
    assertTrue(employee.getId() != null);
  }

  @Test
  @Transactional//(propagation = Propagation.REQUIRED, noRollbackFor = Exception.class)
  @Rollback(false)
  public void deleteEmployeeById() {
    Employee employee = EmployeeMother.simple().build();
    employee.setDepartment(DepartmentMother.simple().build());
    employee = employeeRepository.saveAndFlush(employee);
    assertTrue(employeeRepository.findById(employee.getId()).isPresent());
    employeeRepository.deleteById(employee.getId());
    assertTrue(!employeeRepository.findById(employee.getId()).isPresent());
  }

  //Rest all cases are straight-forward, because they are all owning sides.
  //In Employee -> removing Department, Project
  //In Project -> removing Employee
  //In Address -> removing Department

  @Test
  @Transactional
  @Rollback(false)
  public void removeDepartment() {//Employee deletes its association with department by setting department_id to null
    Employee employee = EmployeeMother.complete().build();
    employee = employeeRepository.saveAndFlush(employee);
    assertTrue(employeeRepository.findById(employee.getId()).get().getDepartment() != null);
    employee.setDepartment(null);
    employee = employeeRepository.saveAndFlush(employee);
    assertTrue(employeeRepository.findById(employee.getId()).get().getDepartment() == null);
  }

  @Test
  @Transactional//(propagation = Propagation.REQUIRED, noRollbackFor = Exception.class)
  @Rollback(false)
  public void removeProject() {//Employee deletes its association with department by setting department_id to null
    Employee employee = EmployeeMother.complete().build();
    employee.addProject(ProjectMother.simpleRandom()
        .build());//if i use simple, then both projects will be equal so only 1 record will get stored.
    employee = employeeRepository.saveAndFlush(employee);
    assertTrue(employee.getProjects().size() != 0);

    Project project = employee.getProjects().get(0);
    employee.removeProject(project);
    //employee.setProjects(null);//works without the concept of orphan removal, because both are owning sides

    employee = employeeRepository.saveAndFlush(employee);
    assertTrue(employeeRepository.findById(employee.getId()).get().getProjects().size() == 1);
  }


  @Test
  public void findByDepartment_NameIgnoreCase() {

    clearDB();

    Employee employee1 = EmployeeMother.completeRandom().build();
    Department department = DepartmentMother.simple().build();
    employee1.setDepartment(department);

    Employee employee2 = EmployeeMother.completeRandom().build();

    Employee employee3 = EmployeeMother.completeRandom().build();
    employee3.setDepartment(department);

    List<Employee> employees = employeeRepository
        .saveAll(Arrays.asList(employee1, employee2, employee3));
    assertEquals(2,
        employeeRepository.findByDepartment_NameIgnoreCase(department.getName()).size());
  }

}
