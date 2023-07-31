package com.employee.service.employee.handler.dto.response;


import lombok.Data;

@Data
public class CreateEmployeeResponse {
  private String status;
  private int statusCode;
  private String resultMessage;
}
