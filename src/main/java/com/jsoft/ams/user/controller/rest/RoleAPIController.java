package com.jsoft.ams.user.controller.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsoft.ams.enums.Module;
import com.jsoft.ams.exception.APIException;
import com.jsoft.ams.user.model.Privilege;
import com.jsoft.ams.user.model.Role;
import com.jsoft.ams.user.model.User;
import com.jsoft.ams.user.repository.UserRepository;
import com.jsoft.ams.user.service.UserService;
import com.jsoft.ams.util.AppUtil;

@RestController
@RequestMapping("/api/roles")
public class RoleAPIController {

	@Autowired UserService userService;
	@Autowired UserRepository userRepository;
	
    @GetMapping
    public ResponseEntity<?> listRoles(
    		@RequestParam (name="pageNo", defaultValue = "1") Integer pageNo,
			@RequestParam (name="pageSize", defaultValue = "10") Integer pageSize,
			@RequestParam Map<String, Object> params){

    	HashMap<String, Object> result = new HashMap<>();
    	result.put("count", userRepository.countRoles(params));
    	result.put("data", userRepository.listRoles(params, new RowBounds((pageNo-1)*pageSize, pageSize)));
    	return ResponseEntity.ok(result);
    			
    }

    @GetMapping("/{roleCode}")
    public ResponseEntity<?> getRole(@PathVariable("roleCode") String roleCode){
    	Role role = userRepository.getRoleBasic(roleCode);
    	if(role == null) {
    		throw new APIException(HttpStatus.BAD_REQUEST, "Role not found");
    	}else {
    		return ResponseEntity.ok(role);
    	}
    }

    @PostMapping
    public ResponseEntity<?> addRole(Role role){
    	userService.saveRole(role, AppUtil.getCurrentLoggedInUser().getUsername(), false);
    	return ResponseEntity.ok(role.getRoleCode());
    }

    @PostMapping("/{roleCode}")
    public ResponseEntity<?> editRole(@PathVariable("roleCode") String roleCode, Role role){
    	role.setRoleCode(roleCode);
    	userService.saveRole(role, AppUtil.getCurrentLoggedInUser().getUsername(), true);
    	return ResponseEntity.ok(role.getRoleCode());
    }

    @DeleteMapping("/{roleCode}")
    public ResponseEntity<?> deleteRole(@PathVariable("roleCode") String roleCode){
    	userService.deleteRole(roleCode, AppUtil.getCurrentLoggedInUser().getUsername());
    	return ResponseEntity.ok().build();
    }

    @GetMapping("/{roleCode}/privileges")
    public ResponseEntity<?> getRolePrivileges(@PathVariable("roleCode") String roleCode){
    	return ResponseEntity.ok(userRepository.getAppPrivilegesListForRole(roleCode));
    }

    @PostMapping("/{roleCode}/privileges")
    public ResponseEntity<?> saveRolePrivileges(@PathVariable("roleCode") String roleCode, @RequestParam("privilegeCode") List<String> privilegeCodes){
    	userService.saveRolePrivileges(roleCode, privilegeCodes);
    	return ResponseEntity.ok().build();
    }

}
