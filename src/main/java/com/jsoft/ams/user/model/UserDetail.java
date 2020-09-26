package com.jsoft.ams.user.model;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;

public class UserDetail extends org.springframework.security.core.userdetails.User{

	private String tenant;
	private List<UserRole> userRoles;
	private Long selectedBranchId;
	public UserDetail(String username, String password, List<UserRole> userRoles, Collection<? extends GrantedAuthority> authorities, String tenant) {
		super(username, password, authorities);
		this.tenant = tenant;
		this.userRoles = userRoles;
		this.selectedBranchId = userRoles != null && !userRoles.isEmpty() ? userRoles.get(0).getBranchId() : null;
	}

	public String getTenant() {
		return tenant;
	}

	public void setTenant(String tenant) {
		this.tenant = tenant;
	}

	public Long getSelectedBranchId() {
		return selectedBranchId;
	}

	public void setSelectedBranchId(Long selectedBranchId) {
		this.selectedBranchId = selectedBranchId;
	}

	public List<UserRole> getUserRoles() {
		return userRoles;
	}
		
}
