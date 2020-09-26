package com.jsoft.ams.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.jsoft.ams.gl.model.Account;

@Component
public class AccountUtil {

	public static List<Account> loadSubAccounts(Account account, List<Account> accounts){
		List<Account> subAccounts = new ArrayList<>();
		for(Account a : accounts) {
			if(a.getParentCode() != null && a.getParentCode().equals(account.getCode())) {
				subAccounts.add(a);
				a.setSubAccounts(loadSubAccounts(a, accounts));
			}
		}
		return subAccounts;
	}
	
	public static List<Account> convertIntoTree(List<Account> accounts){
		List<Account> headAccounts = new ArrayList<>();
		for(Account a : accounts) {
			if(a.getParentCode() == null) {
				a.setSubAccounts(loadSubAccounts(a, accounts));
				headAccounts.add(a);
			}
		}
		return headAccounts;
	}

}
