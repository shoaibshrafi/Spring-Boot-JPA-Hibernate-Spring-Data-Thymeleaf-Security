package com.jsoft.ams.gl.controller;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jsoft.ams.gl.repository.AccountRepository;
import com.jsoft.ams.util.AccountUtil;
import com.jsoft.ams.util.AppUtil;

@Controller
@RequestMapping("/accounts")
public class AccountViewController {

	@Autowired AccountRepository accountRepository;
	
	@GetMapping("/heads")
    public String coa(ModelMap modelMap) {
		modelMap.put("types", AccountUtil.convertIntoTree(accountRepository.list(AppUtil.map("groupAccount", 1), RowBounds.DEFAULT)));
        return "gl/coa";
    }

	@GetMapping
    public String list(ModelMap modelMap) {
		modelMap.put("types", AccountUtil.convertIntoTree(accountRepository.list(AppUtil.map("groupAccount", 1), RowBounds.DEFAULT)));
        return "gl/account-list";
    }
		
}
