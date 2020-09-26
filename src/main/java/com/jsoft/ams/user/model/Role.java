package com.jsoft.ams.user.model;

import java.time.LocalDateTime;
import java.util.List;

import com.jsoft.ams.model.BaseModel;

import lombok.Data;

@Data
public class Role extends BaseModel{

	private String roleCode;
	
	private String roleName;

	private LocalDateTime createdDate;
	
	private String createdBy;
	
	private LocalDateTime updatedDate;
	
	private String updatedBy;
	
	private List<Privilege> privileges;	
	
	boolean admin;
	
	public Role() {
		
	}
	
	public Role(String roleCode) {
		this.roleCode = roleCode;
	}
}
