package com.jsoft.ams.gl.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

@Data
public class Account {

	private Long id;
    private Integer type;
    private String typeName;
    private Long code;
    private String name;
    private String title;
    private String description;
    private Long parentCode; 
    private Double openingBalance;
    private int level;
	private Integer sequence;
    private int status;
    private boolean systemAccount;
    private boolean groupAccount;
	private LocalDate openingDate;
	
	private String createdBy;
	private LocalDateTime createdDate;
	private String updatedBy;
	private LocalDateTime updatedDate;

	List<Account> subAccounts;
}
