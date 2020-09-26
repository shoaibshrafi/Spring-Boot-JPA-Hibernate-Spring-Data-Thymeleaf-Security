package com.jsoft.ams.user.model;

import java.time.LocalDateTime;
import java.util.List;

import com.jsoft.ams.model.BaseModel;

import lombok.Data;


@Data
public class User extends BaseModel{

	private String username;

	private String password;
	
	private String firstName;
	
	private String lastName;
	
	private String mobileNo;

	private String email;
	
	private boolean enabled;
	
	private String ipAddress;

	private String macAddress;

	private LocalDateTime createdDate;

	private String createdBy;

	private LocalDateTime updatedDate;
	
	private String updatedBy;
	
	private String roleCode;
	
	private List<UserRole> userRoles;

	public String toString() {
		return this.username;
	}
}
