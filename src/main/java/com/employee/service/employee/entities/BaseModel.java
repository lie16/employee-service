package com.employee.service.employee.entities;

import lombok.Data;

import javax.persistence.MappedSuperclass;
import java.util.Date;

@MappedSuperclass
@Data
public class BaseModel {
    private Date createdDate;
    private Date updatedDate;
}