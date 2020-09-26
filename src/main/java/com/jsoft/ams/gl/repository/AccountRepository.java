package com.jsoft.ams.gl.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import com.jsoft.ams.gl.model.Account;
import com.jsoft.ams.gl.model.AccountType;

@Mapper
public interface AccountRepository {

	public List<Account> listAccountTypesGroupByParent();
	
	public List<Account> list(Map<String, Object> params, RowBounds rowBounds);
	public Long count(Map<String, Object> params);

}
