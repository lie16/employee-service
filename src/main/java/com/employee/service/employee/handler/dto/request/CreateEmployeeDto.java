package com.employee.service.employee.handler.dto.request;


import lombok.Data;

@Data
public class  CreateEmployeeDto {
  private String employeeName;
  private String email;
  private String phoneNumber;
  private int departmentId;
}
