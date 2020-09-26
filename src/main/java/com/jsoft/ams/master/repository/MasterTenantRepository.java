package com.jsoft.ams.master.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.jsoft.ams.master.model.MasterTenant;

@Mapper
public interface MasterTenantRepository {

    /**
     * Using a custom named query
     * @param tenantId
     * @return
     */
    @Select("select p from master_tenant mt where mt.id = #{id}")
    MasterTenant findByTenantId(@Param("id") Long id);

    @Select("select * from master_tenant")
    List<MasterTenant> findAll();

}