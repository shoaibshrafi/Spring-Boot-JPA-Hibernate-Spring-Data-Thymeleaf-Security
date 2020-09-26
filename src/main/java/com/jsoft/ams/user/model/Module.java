package com.jsoft.ams.user.model;


import java.util.List;

import com.jsoft.ams.model.BaseModel;

import lombok.Data;

@Data
public class Module extends BaseModel{

	String moduleCode;
	String moduleName;
	
	List<Screen> screens;
}
