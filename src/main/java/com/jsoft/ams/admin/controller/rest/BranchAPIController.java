package com.jsoft.ams.admin.controller.rest;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsoft.ams.admin.model.Branch;
import com.jsoft.ams.admin.repository.BranchRepository;
import com.jsoft.ams.admin.service.BranchService;
import com.jsoft.ams.exception.APIException;
import com.jsoft.ams.util.AppUtil;

@RestController
@RequestMapping("/api/admin/branches")
public class BranchAPIController {

	@Autowired BranchService branchService;
	@Autowired BranchRepository branchRepository;
	
    @GetMapping
    public ResponseEntity<?> list(
    		@RequestParam (name="pageNo", defaultValue = "1") Integer pageNo,
			@RequestParam (name="pageSize", defaultValue = "10") Integer pageSize,
			@RequestParam Map<String, Object> params){

    	HashMap<String, Object> result = new HashMap<>();
    	result.put("count", branchRepository.count(params));
    	result.put("data", branchRepository.list(params, new RowBounds((pageNo-1)*pageSize, pageSize)));
    	return ResponseEntity.ok(result);
    			
    }

    @GetMapping("/{branchId}")
    public ResponseEntity<?> getBranch(@PathVariable("branchId") Long branchId){
    	Branch branch = branchRepository.get(branchId);
    	if(branch == null) {
    		throw new APIException(HttpStatus.BAD_REQUEST, "Branch not found");
    	}else {
    		return ResponseEntity.ok(branch);
    	}
    }

    @PostMapping
    public ResponseEntity<?> add(Branch branch){
    	branchService.save(branch, AppUtil.getCurrentLoggedInUser().getUsername(), false);
    	return ResponseEntity.ok(branch.getBranchId());
    }

    @PostMapping("/{branchId}")
    public ResponseEntity<?> edit(@PathVariable("branchId") Long branchId, Branch branch){
    	branch.setBranchId(branchId);
    	branchService.save(branch, AppUtil.getCurrentLoggedInUser().getUsername(), true);
    	return ResponseEntity.ok().build();
    }

    @GetMapping("/lookup")
    public ResponseEntity<?> lookup(
    		@RequestParam (name="pageNo", defaultValue = "1") Integer pageNo,
			@RequestParam (name="pageSize", defaultValue = "10") Integer pageSize,
			@RequestParam Map<String, Object> params){

    	return ResponseEntity.ok(branchRepository.list(params, new RowBounds((pageNo-1)*pageSize, pageSize)));
    			
    }


}
