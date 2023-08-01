package com.employee.service.employee.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Department extends BaseModel {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int departmentId;
  private String departmentName;

}
