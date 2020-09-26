package com.jsoft.ams.admin.service;

import java.time.LocalDateTime;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jsoft.ams.admin.enums.BranchStatus;
import com.jsoft.ams.admin.model.Branch;
import com.jsoft.ams.admin.repository.BranchRepository;
import com.jsoft.ams.exception.APIException;

@Service
public class BranchService {
 
    @Autowired
    private BranchRepository branchRepository;
 
    @Transactional
    public void save(Branch branch, String username, boolean edit) {
    	validateBranch(branch, edit);
    	if(edit) {
        	branch.setUpdatedBy(username);
        	branch.setUpdatedDate(LocalDateTime.now());
        	branchRepository.update(branch);
    	}else {
    		branch.setStatus(BranchStatus.Active);
    		branch.setCreatedBy(username);
    		branch.setCreatedDate(LocalDateTime.now());
    		branchRepository.insert(branch);
    	}
    }
    
    private void validateBranch(Branch branch, boolean edit) {
    	if(StringUtils.isBlank(branch.getName())) {
    		throw new APIException(HttpStatus.BAD_REQUEST, "Name is required");
    	}
    	
    	if(StringUtils.isBlank(branch.getTitle())) {
    		throw new APIException(HttpStatus.BAD_REQUEST, "Title is required");
    	}

    	if(StringUtils.isBlank(branch.getContactPerson1())) {
    		throw new APIException(HttpStatus.BAD_REQUEST, "Primary Contact Person is required");
    	}

    	if(StringUtils.isBlank(branch.getAddressLine1())) {
    		throw new APIException(HttpStatus.BAD_REQUEST, "Address Line 1 is required");
    	}

    	if(StringUtils.isBlank(branch.getArea())) {
    		throw new APIException(HttpStatus.BAD_REQUEST, "Area is required");
    	}

    	if(StringUtils.isBlank(branch.getCity())) {
    		throw new APIException(HttpStatus.BAD_REQUEST, "City is required");
    	}

    	if(StringUtils.isBlank(branch.getCountry())) {
    		throw new APIException(HttpStatus.BAD_REQUEST, "Country is required");
    	}
    
    	if(StringUtils.isBlank(branch.getPhone())) {
    		throw new APIException(HttpStatus.BAD_REQUEST, "Phone is required");
    	}

    	if(StringUtils.isBlank(branch.getMobileNo())) {
    		throw new APIException(HttpStatus.BAD_REQUEST, "Mobile No is required");
    	}

    }

}

