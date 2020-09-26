package com.jsoft.ams.user.model;

import org.apache.commons.lang3.StringUtils;

import com.jsoft.ams.model.BaseModel;

import lombok.Data;

@Data
public class Privilege extends BaseModel{

	private String privilegeCode;
	private String name;
	private String description;

	//For view purpose
	private boolean checked;

	@Override
	public int hashCode() {
		if(StringUtils.isNotBlank(privilegeCode)) {
			return privilegeCode.hashCode();
		}else {
			return 0;
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Privilege other = (Privilege) obj;
		
		return getPrivilegeCode().equals(other.getPrivilegeCode());
	}
}
