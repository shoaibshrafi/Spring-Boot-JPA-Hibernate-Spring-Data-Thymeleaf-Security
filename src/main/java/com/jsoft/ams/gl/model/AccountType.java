package com.jsoft.ams.gl.model;

import java.util.List;

import lombok.Data;

@Data
public class AccountType {

	public enum Type {BS, IS};
	
	private Integer id;
	private String name;
	private Integer code;
	private Type type;
	
	private List<AccountType> subAccountTypes;

}
