package com.employee.service.employee.handler.dto.response;

import lombok.Data;

@Data
public class EmployeesResponse {
  private int employeeId;
  private String employeeName;
  private String email;
  private String phoneNumber;

//  private DepartmentDto departmentDto;

  private long totalData;

  private int totalHalaman;

}
