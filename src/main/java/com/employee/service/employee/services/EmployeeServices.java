package com.employee.service.employee.services;

import com.employee.service.employee.entities.Department;
import com.employee.service.employee.entities.DepartmentEntitiesDto;
import com.employee.service.employee.entities.Employee;
import com.employee.service.employee.handler.dto.request.CreateEmployeeDto;
import com.employee.service.employee.handler.dto.response.DepartmentDto;
import com.employee.service.employee.handler.dto.response.EmployeesResponse;
import com.employee.service.employee.repository.EmployeeRepo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServices {

  @Autowired
  private EmployeeRepo employeeRepo;

  @Transactional
  public String createEmployee(CreateEmployeeDto createEmployeeDto) {
    Employee employee = new Employee();
    employee.setEmployeeName(createEmployeeDto.getEmployeeName());
    employee.setEmail(createEmployeeDto.getEmail());
    employee.setPhoneNumber(createEmployeeDto.getPhoneNumber());
    employee.setCreatedDate(new Date());
    employee.setUpdatedDate(new Date());

//    ambil data department dari department service --------------------------------
    String url = "http://localhost:8882/api-v1/department/detail?departmentId=" + createEmployeeDto.getDepartmentId();
    RestTemplate restTemplate = new RestTemplate();
    Department department = restTemplate.getForObject(url, Department.class);
//    ---------------------------------------------------------------

//    employee.setDepartment(department);
    employeeRepo.save(employee);
    return "employee created successfully";
  }

  public List<EmployeesResponse> getEmployees() {
//    pagination
    Pageable pageable = PageRequest.of(0, 10);
    Page<Employee> page = employeeRepo.findAll(pageable);

    List<EmployeesResponse> responses = new ArrayList<>();
    for (Employee employee : page) {
      EmployeesResponse employeesResponse = new EmployeesResponse();
      employeesResponse.setEmployeeId(employee.getEmployeeId());
      employeesResponse.setEmail(employee.getEmail());
      employeesResponse.setPhoneNumber(employee.getPhoneNumber());
      employeesResponse.setEmployeeName(employee.getEmployeeName());
//      System.out.println("dept id = " + employee.getDepartment_department_id());

//      get department data
//      Department department = employee.getDepartment(); //call api dept service

      String url = "http://localhost:8882/api-v1/department/detail?departmentId=" + employee.getDepartment_department_id();
      RestTemplate restTemplate = new RestTemplate();
      //      using String
//      ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
//      Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
//      ObjectMapper mapper = new ObjectMapper();
//      JsonNode root = null;
//      try {
//        root = mapper.readTree(response.getBody());
//      } catch (JsonProcessingException e) {
//        throw new RuntimeException(e);
//      }
//      JsonNode departmentName = root.path("departmentName");
//      Assertions.assertNotNull(departmentName.asText());
      // Department department = restTemplate.getForObject(url, Department.class);
//      employeesResponse.setDepartmentDto();
//      System.out.println("Dept name text = " + departmentName.asText());
//      DepartmentDto departmentDto = new DepartmentDto();
//      departmentDto.setDepartmentName(departmentName.asText());
//
//      employeesResponse.setDepartmentDto(departmentDto);

//      using Pojo
      DepartmentEntitiesDto deptDto = restTemplate.getForObject(url, DepartmentEntitiesDto.class);
      DepartmentDto departmentDto = new DepartmentDto();
      departmentDto.setDepartmentName(deptDto.getDepartmentName());

      employeesResponse.setDepartmentDto(departmentDto);
      employeesResponse.setDepartmentName(deptDto.getDepartmentName());


      employeesResponse.setTotalData(page.getTotalElements());

      employeesResponse.setTotalHalaman(page.getTotalPages());

      responses.add(employeesResponse);
    }
    return responses;
  }

  @Transactional
  public String updateEmployees(CreateEmployeeDto createEmployeeDto, int employeeId)
     throws Exception {
    Optional<Employee> employee = employeeRepo.findById(employeeId);

    if (!employee.isPresent()) {
      throw new Exception("Employee " + employeeId + " not found");
    }
    //    utk delete (HTTP METHOD DeleteMapping)
    //    employeeRepo.delete(employee.get());
    //    employee.get().setIsdeleted(true);
    employee.get().setEmployeeName(createEmployeeDto.getEmployeeName());
    employee.get().setEmail(createEmployeeDto.getEmail());

    employeeRepo.save(employee.get());

    return "Employees updated";
  }
}
