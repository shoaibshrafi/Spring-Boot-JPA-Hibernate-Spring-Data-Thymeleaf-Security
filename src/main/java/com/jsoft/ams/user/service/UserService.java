package com.jsoft.ams.user.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jsoft.ams.user.model.Privilege;
import com.jsoft.ams.user.model.Role;
import com.jsoft.ams.user.model.User;
import com.jsoft.ams.user.model.UserDetail;
import com.jsoft.ams.user.model.UserRole;
import com.jsoft.ams.user.repository.UserRepository;
import com.jsoft.ams.util.AppUtil;
import com.jsoft.ams.exception.APIException;
import com.jsoft.ams.multitenancy.TenantContextHolder;

@Service
public class UserService implements UserDetailsService {
 
    @Autowired
    private UserRepository userRepository;
 
    @Override
    public UserDetails loadUserByUsername(String username) {
    	User user = userRepository.findByUsername(username);
        
		if (user == null) {
            throw new UsernameNotFoundException(username);
        }
		
        try {
			return new UserDetail(user.getUsername(), user.getPassword(), user.getUserRoles(), mapRolesToAuthorities(user.getUserRoles()), TenantContextHolder.getTenant());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return null;
    }

    
    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<UserRole> userRoles){
    	return new ArrayList<GrantedAuthority>(0);
    	/*
    	if(userRoles == null || userRoles.isEmpty()) {
    		return new ArrayList<GrantedAuthority>(0);
    	}
    	
    	List<Privilege> privileges = roles.stream().flatMap(r->r.getPrivileges().stream()).distinct().collect(Collectors.toList());
    	return privileges.stream().map(up -> new SimpleGrantedAuthority()).collect(Collectors.toList());
    	*/
    }
    
    @Transactional
    public void saveUser(User user, String username, boolean edit) {
    	validateUser(user, edit);
    	if(edit) {
        	user.setUpdatedBy(username);
        	user.setUpdatedDate(LocalDateTime.now());
        	userRepository.updateUser(user);
    	}else {
        	user.setPassword(AppUtil.encodePassword(user.getPassword()));
        	user.setCreatedBy(username);
        	user.setCreatedDate(LocalDateTime.now());
        	userRepository.insertUser(user);
    	}
    }
    
    private void validateUser(User user, boolean edit) {
    	if(StringUtils.isBlank(user.getUsername())) {
    		throw new APIException(HttpStatus.BAD_REQUEST, "Username is required");
    	}else if(!edit && userRepository.existsUsername(user.getUsername())) {
    		throw new APIException(HttpStatus.BAD_REQUEST, "Username already exists");
    	}
    	
    	if(!edit && StringUtils.isBlank(user.getPassword())) {
    		throw new APIException(HttpStatus.BAD_REQUEST, "Password is required");
    	}

    	if(StringUtils.isBlank(user.getRoleCode())) {
    		throw new APIException(HttpStatus.BAD_REQUEST, "Role is required");
    	}

    	if(StringUtils.isBlank(user.getFirstName())) {
    		throw new APIException(HttpStatus.BAD_REQUEST, "First Name is required");
    	}

    	if(StringUtils.isBlank(user.getLastName())) {
    		throw new APIException(HttpStatus.BAD_REQUEST, "Last Name is required");
    	}

    	if(StringUtils.isBlank(user.getMobileNo())) {
    		throw new APIException(HttpStatus.BAD_REQUEST, "Mobile No is required");
    	}
    }

    @Transactional
    public void assignUserRole(String username, Long branchId, String roleCode) {
    	
    	if(!userRepository.existsUsername(username)) {
    		throw new APIException(HttpStatus.BAD_REQUEST, "User not found");
    	}
    	
    	if(userRepository.hasUserRole(username, branchId, roleCode)) {
    		throw new APIException(HttpStatus.BAD_REQUEST, "Role is already assigned to the user");
    	}else {
        	userRepository.assignUserRole(username, branchId,roleCode);
    	}
    	
    }

    @Transactional
    public void saveRole(Role role, String username, boolean edit) {
    	validateRole(role);
    	if(edit) {
        	role.setUpdatedBy(username);
        	role.setUpdatedDate(LocalDateTime.now());
        	userRepository.updateRole(role);
    	}else {
        	role.setCreatedBy(username);
        	role.setCreatedDate(LocalDateTime.now());
        	userRepository.insertRole(role);
    	}
    }

    private void validateRole(Role role) {
    	if(StringUtils.isBlank(role.getRoleName())) {
    		throw new APIException(HttpStatus.BAD_REQUEST, "Role Name is required");
    	}
    }

    @Transactional
    public void deleteRole(String roleCode, String username) {

    	if(userRepository.isRoleInUsed(roleCode)) {
    		throw new APIException(HttpStatus.BAD_REQUEST, "Role in used, could not be deleted");
    	}
    	
    	userRepository.deleteRole(roleCode);
    	
    }

    @Transactional
    public void saveRolePrivileges(String roleCode, List<String> privilegeCodes) {
    	userRepository.clearRolePrivileges(roleCode);
    	userRepository.saveRolePrivileges(roleCode, privilegeCodes);
    }


}

