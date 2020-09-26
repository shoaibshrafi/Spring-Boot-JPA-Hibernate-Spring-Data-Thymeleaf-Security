package com.jsoft.ams.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping
	public String index() {
		return "index";
	}
	
	@GetMapping("/login")
    public String login() {
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
	
}
