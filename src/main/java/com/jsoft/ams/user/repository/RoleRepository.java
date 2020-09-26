package com.jsoft.ams.user.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.jsoft.ams.user.model.Role;

@Mapper
public interface RoleRepository{

	@Select("SELECT r FROM Role r")
	public List<Role> findAll();
}
