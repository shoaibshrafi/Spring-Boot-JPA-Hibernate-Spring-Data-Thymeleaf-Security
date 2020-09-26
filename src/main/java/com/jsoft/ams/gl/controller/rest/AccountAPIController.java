package com.jsoft.ams.gl.controller.rest;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsoft.ams.gl.service.AccountService;
import com.jsoft.ams.util.AccountUtil;
import com.jsoft.ams.util.AppUtil;
import com.jsoft.ams.gl.repository.AccountRepository;

@RestController
@RequestMapping("/api/accounts")
public class AccountAPIController {

	@Autowired AccountService accountService;
	@Autowired AccountRepository accountRepository;
	
    @GetMapping
    public ResponseEntity<?> list(
    		@RequestParam (name="pageNo", defaultValue = "1") Integer pageNo,
			@RequestParam (name="pageSize", defaultValue = "10") Integer pageSize,
			@RequestParam Map<String, Object> params){

    	HashMap<String, Object> result = new HashMap<>();
    	params.put("groupAccount", 0);
    	result.put("count", accountRepository.count(params));
    	result.put("data", accountRepository.list(params, new RowBounds((pageNo-1)*pageSize, pageSize)));
    	return ResponseEntity.ok(result);
    			
    }

    @GetMapping("/heads")
    public ResponseEntity<?> heads(){
    	return ResponseEntity.ok(AccountUtil.convertIntoTree(accountRepository.list(AppUtil.map("groupAccount", 1), RowBounds.DEFAULT)));
    			
    }

    @GetMapping("/lookup")
    public ResponseEntity<?> lookup(
    		@RequestParam (name="pageNo", defaultValue = "1") Integer pageNo,
			@RequestParam (name="pageSize", defaultValue = "10") Integer pageSize,
			@RequestParam Map<String, Object> params){

    	return ResponseEntity.ok(accountRepository.list(params, new RowBounds((pageNo-1)*pageSize, pageSize)));
    			
    }
    

}
