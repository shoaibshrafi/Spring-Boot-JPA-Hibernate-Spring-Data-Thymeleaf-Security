package com.jsoft.ams.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="user_privileges")
@Data
public class UserPrivilege extends BaseModel{

	@Id private String username;
	@Id private String privilegeCode;
	private boolean enabled;
}
