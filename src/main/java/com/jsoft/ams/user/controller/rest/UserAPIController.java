package com.jsoft.ams.user.controller.rest;

import java.util.HashMap;
import java.util.Map;

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

import com.jsoft.ams.exception.APIException;
import com.jsoft.ams.user.model.Role;
import com.jsoft.ams.user.model.User;
import com.jsoft.ams.user.repository.UserRepository;
import com.jsoft.ams.user.service.UserService;
import com.jsoft.ams.util.AppUtil;

@RestController
@RequestMapping("/api/users")
public class UserAPIController {

	@Autowired UserService userService;
	@Autowired UserRepository userRepository;
	
    @GetMapping
    public ResponseEntity<?> listUsers(
    		@RequestParam (name="pageNo", defaultValue = "1") Integer pageNo,
			@RequestParam (name="pageSize", defaultValue = "10") Integer pageSize,
			@RequestParam Map<String, Object> params){

    	HashMap<String, Object> result = new HashMap<>();
    	result.put("count", userRepository.countUsers(params));
    	result.put("data", userRepository.listUsers(params, new RowBounds((pageNo-1)*pageSize, pageSize)));
    	return ResponseEntity.ok(result);
    			
    }

    @GetMapping("/{username}")
    public ResponseEntity<?> getUser(@PathVariable("username") String username){
    	User user = userRepository.getUserBasic(username);
    	if(user == null) {
    		throw new APIException(HttpStatus.BAD_REQUEST, "User not found");
    	}else {
    		user.setPassword("");
    		return ResponseEntity.ok(user);
    	}
    }

    @PostMapping
    public ResponseEntity<?> addUser(User user){
    	userService.saveUser(user, AppUtil.getCurrentLoggedInUser().getUsername(), false);
    	return ResponseEntity.ok(user.getUsername());
    }

    @PostMapping("/{username}")
    public ResponseEntity<?> editUser(@PathVariable("username") String username, User user){
    	user.setUsername(username);
    	userService.saveUser(user, AppUtil.getCurrentLoggedInUser().getUsername(), true);
    	return ResponseEntity.ok(user.getUsername());
    }

    @PostMapping("/{username}/branches/{branchId}/roles/{roleCode}")
    public ResponseEntity<?> addUserRole(@PathVariable("username") String username, @PathVariable("branchId") Long branchId, @PathVariable("roleCode") String roleCode){
    	userService.assignUserRole(username, branchId, roleCode);
    	return ResponseEntity.ok().build();
    }

    @PostMapping("/{username}/active")
    public ResponseEntity<?> setActive(@PathVariable("username") String username, @RequestParam("active") boolean active){
    	return null;
    }

    @GetMapping("/{username}/roles")
    public ResponseEntity<?> getUserRoles(@PathVariable("username") String username){
    	return ResponseEntity.ok(userRepository.getUserRoles(username));
    }

    @DeleteMapping("/{username}/branches/{branchId}/roles/{roleCode}")
    public ResponseEntity<?> deleteUserRoles(@PathVariable("username") String username, @PathVariable("branchId") Long branchId, @PathVariable("roleCode") String roleCode){
    	userRepository.deleteUserRole(username, branchId, roleCode);
    	return ResponseEntity.ok().build();
    }

}
