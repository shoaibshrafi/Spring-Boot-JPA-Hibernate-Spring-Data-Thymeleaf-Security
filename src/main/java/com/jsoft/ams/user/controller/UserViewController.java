package com.jsoft.ams.user.controller;

import java.util.Collections;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jsoft.ams.admin.repository.BranchRepository;
import com.jsoft.ams.user.repository.UserRepository;

@Controller
@RequestMapping("/users")
public class UserViewController {

	@Autowired BranchRepository branchRepository;
	@Autowired UserRepository userRepository;
	
	@GetMapping
    public String list(ModelMap modelMap) {
		modelMap.put("roles", userRepository.listRoles(Collections.EMPTY_MAP, RowBounds.DEFAULT));
		modelMap.put("branches", branchRepository.list(Collections.EMPTY_MAP, RowBounds.DEFAULT));
        return "users/user-list";
    }

}
