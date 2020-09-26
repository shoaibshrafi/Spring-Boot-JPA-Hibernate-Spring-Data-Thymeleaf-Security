package com.jsoft.ams.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.jsoft.ams.exception.ApplicationException;
import com.jsoft.ams.master.repository.MasterTenantRepository;
import com.jsoft.ams.user.model.UserDetail;
import com.jsoft.ams.util.AppUtil;

@Controller
public class HomeController {

	@Autowired MasterTenantRepository masterTenantRepository;
	
	/*
	 * @GetMapping public String index() { return "index"; }
	 */
	
	@GetMapping("/login")
    public String login(ModelMap modelMap) {
		modelMap.put("tenants", masterTenantRepository.findAll());
        return "/login";
    }

	@GetMapping("/admin")
	public String admin() {
		return "/admin";
	}

	@GetMapping("/user")
	public String user() {
		return "/user";
	}

	@GetMapping("/guest")
	public String guest() {
		return "/about";
	}

	@GetMapping("/403")
    public String error403() {
        return "/error/403";
    }
	
	@GetMapping("/home")
    public String home() {
        return "redirect:/dashboard";
    }

	@GetMapping("/")
    public String dashboard() {
        return "dashboard";
    }

	@GetMapping("/branches/{branchId}")
    public String changeBranch(@PathVariable("branchId") Long branchId) {
		UserDetail userDetail = AppUtil.getCurrentLoggedInUser();
		boolean found = userDetail.getUserRoles().stream().filter(ur->ur.getBranchId().equals(branchId)).count() > 0;
		if(!found) {
			throw new ApplicationException(HttpStatus.BAD_REQUEST, "No Branch found");
		}
		userDetail.setSelectedBranchId(branchId);
        return "dashboard";
    }

}
