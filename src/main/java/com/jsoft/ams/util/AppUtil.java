package com.jsoft.ams.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.jsoft.ams.user.model.UserDetail;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class AppUtil {

	
	public static UserDetail getCurrentLoggedInUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth == null) {
			log.info("User authentication is null");
			return null;
		}else {
			if(auth.getPrincipal() instanceof UserDetail) {
				UserDetail userDetail = (UserDetail)auth.getPrincipal();
				log.info("User authentication is not null, username {}, tenant {}", userDetail.getUsername(), userDetail.getTenant());
				return userDetail;
			}else {
				return null;
			}
		}
	}

	public static String encodePassword(String password) {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder.encode(password);
	}

	public static Map<String, Object> map(Object... kv) {
		
		Map<String, Object> map = new HashMap< String, Object>();
		for(int cnt = 0; cnt < kv.length  ; cnt+=2){
			map.put((String)kv[cnt], kv[cnt+1]);
		}
		return map;
	}

}
