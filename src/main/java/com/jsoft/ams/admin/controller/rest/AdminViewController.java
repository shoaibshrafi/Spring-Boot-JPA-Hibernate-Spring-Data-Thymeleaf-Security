package com.jsoft.ams.admin.controller.rest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminViewController {

	@GetMapping("/branches")
    public String listBranches(ModelMap modelMap) {
        return "admin/branch-list";
    }

	
}
