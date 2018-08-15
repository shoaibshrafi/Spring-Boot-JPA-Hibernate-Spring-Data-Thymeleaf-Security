package com.jsoft.ams.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;




@Entity
@Table (name="users")
@Data
public class User extends BaseModel{

	@Id private String username;
	private String password;
	private String firstname;
	private String lastname;
	private String email;
	private boolean enabled;
	private LocalDate createdDate;
	private String createdBy;
	private LocalDate updatedDate;
	private String updatedBy;
	@OneToMany 
	@JoinColumn(name="username")
	private List<UserPrivilege> userPrivileges;	
	
}
