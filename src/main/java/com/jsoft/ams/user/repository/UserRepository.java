package com.jsoft.ams.user.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.session.RowBounds;

import com.jsoft.ams.user.model.Privilege;
import com.jsoft.ams.user.model.Role;
import com.jsoft.ams.user.model.User;
import com.jsoft.ams.user.model.UserRole;

@Mapper
public interface UserRepository{

	public User findByUsername(@Param("username") String username);
	
	public List<User> listUsers(Map<String, Object> params, RowBounds rowBounds);
	public Long countUsers(Map<String, Object> params);

	@Select("SELECT count(u.username) > 0 FROM users u WHERE u.username=#{username}")
	public boolean existsUsername(@Param("username") String username);
	
	@Insert("INSERT INTO users(username, password, firstname, lastname, mobile_no, email, enabled, ip_address, mac_address, role_code, created_by, created_date) "
            + "VALUES(#{username}, #{password}, #{firstName}, #{lastName}, #{mobileNo}, #{email}, #{enabled}, #{ipAddress}, #{macAddress}, #{roleCode}, #{createdBy}, #{createdDate})")
	public void insertUser(User user);

	@Insert("INSERT INTO user_roles (username, branch_id, role_code) VALUES (#{username}, #{branchId}, #{roleCode})")
	public void assignUserRole(@Param("username") String username, @Param("branchId") Long branchId, @Param("roleCode") String roleCode);

	public List<Role> listRoles(Map<String, Object> params, RowBounds rowBounds);
	public Long countRoles(Map<String, Object> params);

	@Insert("INSERT INTO roles(role_code, role_name, admin, created_by, created_date) "
            + "VALUES(#{roleCode}, #{roleName}, #{admin}, #{createdBy}, #{createdDate})")
	public void insertRole(Role role);

	@Select("SELECT * FROM roles WHERE role_code=#{roleCode}")
	public Role getRoleBasic(@Param("roleCode") String roleCode);

	@Select("SELECT * FROM users WHERE username=#{username}")
	public User getUserBasic(@Param("username") String username);

	@Update("UPDATE users set "
			+ "firstname = #{firstName}, lastname = #{lastName}, mobile_no = #{mobileNo}, email = #{email}, "
			+ "ip_address = #{ipAddress}, mac_address = #{macAddress}, role_code = #{roleCode}, updated_by = #{updatedBy}, updated_date = #{updatedDate} "
			+ "WHERE username = #{username}"
            )
	public void updateUser(User user);

	@Update("UPDATE roles set "
			+ "role_name = #{roleName}, admin = #{admin}, "
			+ "updated_by = #{updatedBy}, updated_date = #{updatedDate}"
			+ "WHERE role_code = #{roleCode}"
            )
	public void updateRole(Role role);

	@Select("SELECT count(ur.username) > 0 FROM user_roles ur WHERE ur.role_code=#{roleCode}")
	public boolean isRoleInUsed(@Param("roleCode") String roleCode);

	@Delete("DELETE from roles where role_code = #{roleCode}")
	public void deleteRole(@Param("roleCode") String roleCode);

	public List<Privilege> getAppPrivilegesListForRole(@Param("roleCode") String roleCode);

	@Delete("DELETE from role_privileges where role_code = #{roleCode}")
	public void clearRolePrivileges(@Param("roleCode") String roleCode);
	
	public void saveRolePrivileges(@Param("roleCode") String roleCode, @Param("privilegeCodes") List<String> privilegeCodes);

	public List<UserRole> getUserRoles(@Param("username") String username);

	@Delete("DELETE from user_roles where username=#{username} and branch_id = #{branchId} and role_code = #{roleCode}")
	public void deleteUserRole(@Param("username") String username, @Param("branchId") Long branchId, @Param("roleCode") String roleCode);

	@Select("SELECT count(ur.role_code) > 0 FROM user_roles ur WHERE ur.username = #{username} and branch_id = #{branchId} and ur.role_code= #{roleCode}")
	public boolean hasUserRole(@Param("username") String username, @Param("branchId") Long branchId, @Param("roleCode") String roleCode);

}
