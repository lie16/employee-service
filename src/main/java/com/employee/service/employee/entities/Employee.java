package com.employee.service.employee.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Employee extends BaseModel {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int employeeId;
  private String employeeName;
  private String email;
  private String phoneNumber;

//  @OneToOne(fetch = FetchType.LAZY)
//  private Department department;

}
