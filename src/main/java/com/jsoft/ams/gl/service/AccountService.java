package com.jsoft.ams.gl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jsoft.ams.gl.repository.AccountRepository;

@Service
public class AccountService {
 
    @Autowired
    private AccountRepository accountRepository;
 
}

