package com.jsoft.ams.admin.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.session.RowBounds;

import com.jsoft.ams.admin.model.Branch;

@Mapper
public interface BranchRepository{


    @Select("select * from branches where branch_id = #{branchId}")
    Branch get(@Param("branchId") Long branchId);

	public List<Branch> list(Map<String, Object> params, RowBounds rowBounds);
	public Long count(Map<String, Object> params);

	@Insert("INSERT INTO branches(branch_id, name, title, description, sales_tax_reg_no, ntn, status, contact_person_1, contact_person_2, "
			+ "address_line_1, address_line_2, area, city, country, phone, mobile_no, email, website, head, created_by, created_date) "
            + "VALUES(null, #{name}, #{title}, #{description}, #{salesTaxRegNo}, #{ntn}, #{status}, #{contactPerson1}, #{contactPerson2}, "
            + "#{addressLine1}, #{addressLine2}, #{area}, #{city}, #{country}, #{phone}, #{mobileNo}, #{email}, #{website}, #{head}, #{createdBy}, #{createdDate})")
	@Options(useGeneratedKeys = true, keyProperty = "branchId")
	public void insert(Branch branch);

	@Update("UPDATE branches set "
			+ "name = #{name}, title = #{title}, description = #{description}, sales_tax_reg_no = #{salesTaxRegNo}, ntn=#{ntn},"
			+ "contact_person_1 = #{contactPerson1}, contact_person_2 = #{contactPerson2}, address_line_1 = #{addressLine1}, address_line_2 = #{addressLine2}, "
			+ "area = #{area}, city = #{city}, country = #{country}, phone = #{phone}, mobile_no = #{mobileNo}, "
			+ "email = #{email}, website = #{website}, updated_by = #{updatedBy}, updated_date = #{updatedDate} "
			+ "WHERE branch_id = #{branchId}"
            )
	public void update(Branch branch);

}
