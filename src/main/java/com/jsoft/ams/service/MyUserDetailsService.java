package com.jsoft.ams.service;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jsoft.ams.model.User;
import com.jsoft.ams.model.UserPrivilege;
import com.jsoft.ams.repository.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {
 
    @Autowired
    private UserRepository userRepository;
 
    @Override
    public UserDetails loadUserByUsername(String username) {
		Optional<User> user = userRepository.findByUsername(username);
        
		if (!user.isPresent()) {
            throw new UsernameNotFoundException(username);
        }
		
        try {
			return new org.springframework.security.core.userdetails.User(user.get().getEmail(),
					user.get().getPassword(),
			        mapRolesToAuthorities(user.get().getUserPrivileges()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return null;
    }

    
    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<UserPrivilege> userPrivileges){
    	userPrivileges.stream().map(up -> System.out.format("Role Name {%s}", up.getPrivilegeCode()));
    	return userPrivileges.stream().map(up -> new SimpleGrantedAuthority(up.getPrivilegeCode())).collect(Collectors.toList());
    }

}

