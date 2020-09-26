package com.jsoft.ams.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="privileges")
@Data
public class Privilege extends BaseModel{

	@Id private String code;
	private String name;
	private String description;
}
