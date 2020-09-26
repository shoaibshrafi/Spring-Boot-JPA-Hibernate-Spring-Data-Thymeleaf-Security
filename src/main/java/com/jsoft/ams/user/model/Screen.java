package com.jsoft.ams.user.model;


import java.util.List;

import com.jsoft.ams.model.BaseModel;

import lombok.Data;

@Data
public class Screen extends BaseModel{

	String screenCode;
	String screenName;
	
	List<Privilege> privileges;
}
