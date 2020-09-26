package com.jsoft.ams.user.controller;

import java.util.Collections;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jsoft.ams.user.repository.UserRepository;

@Controller
@RequestMapping("/roles")
public class RoleViewController {

	@Autowired UserRepository userRepository;
	
	@GetMapping
    public String list(ModelMap modelMap) {
        return "users/role-list";
    }

}
