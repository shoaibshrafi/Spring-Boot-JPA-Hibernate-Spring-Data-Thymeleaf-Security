package com.jsoft.ams.user.model;

import java.util.List;

import com.jsoft.ams.model.BaseModel;

import lombok.Data;


@Data
public class UserRole extends BaseModel{

	private String username;

	private Long branchId;
	private String branchName;
	private String branchTitle;
	
	private List<Role> roles;

}
