package com.jsoft.ams.admin.model;

import java.time.LocalDateTime;

import com.jsoft.ams.admin.enums.BranchStatus;
import com.jsoft.ams.model.BaseModel;
import com.jsoft.ams.user.model.User;

import lombok.Data;

@Data
public class Branch extends BaseModel{

	private Long branchId;

	private String code;
	private String name;
	private String title;
	private String description;	
	
	private BranchStatus status;
	
	private String salesTaxRegNo;

	private String ntn;
	
	private String contactPerson1;

	private String contactPerson2;
	
	private String addressLine1;

	private String addressLine2;

	private String area;
	private String city;
	private String country;
	private String phone;
	
	private String mobileNo;

	private String email;
	
	private String website;

	private boolean head;

	private String createdBy;
	LocalDateTime createdDate;

	private String updatedBy;
	LocalDateTime updatedDate;
	
}
