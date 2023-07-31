package com.employee.service.employee.controller;


import com.employee.service.employee.handler.dto.request.CreateEmployeeDto;
import com.employee.service.employee.handler.dto.response.CreateEmployeeResponse;
import com.employee.service.employee.handler.dto.response.EmployeesResponse;
import com.employee.service.employee.services.EmployeeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api-v1/employee")
public class EmployeeController {

  @Autowired
  private EmployeeServices employeeService;

  @PostMapping("create")
  public ResponseEntity createEmployee(@RequestBody CreateEmployeeDto createEmployeeDto) {
      String result = employeeService.createEmployee(createEmployeeDto);
      CreateEmployeeResponse response = new CreateEmployeeResponse();
      response.setStatus("Created");
      response.setStatusCode(HttpStatus.CREATED.value());
      response.setResultMessage(result);
      return ResponseEntity.status(response.getStatusCode()).body(response);
  }

  @GetMapping
  public ResponseEntity getEmployees() {
    List<EmployeesResponse> employees = employeeService.getEmployees();
    return ResponseEntity.ok(employees);
  }

  @PutMapping("update")
  public ResponseEntity updateEmployee(@RequestBody CreateEmployeeDto createEmployeeDto, @RequestParam("employeeid") int employeeId) {
    try {
      String response = employeeService.updateEmployees(createEmployeeDto, employeeId);
      return ResponseEntity.ok(response);
    }catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

}
