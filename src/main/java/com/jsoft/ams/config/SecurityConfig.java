package com.jsoft.ams.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.jsoft.ams.service.MyUserDetailsService;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Override
	  protected void configure(HttpSecurity http) throws Exception {
		
		http.csrf().disable()
        .authorizeRequests()
			.antMatchers("/", "/home", "/about").permitAll()
			.antMatchers("/admin/**").hasAnyAuthority("ADMIN")
			.antMatchers("/user/**").hasAnyAuthority("USER")
			.anyRequest().authenticated()
        .and()
        .formLogin()
			.loginPage("/login")
			.permitAll()
			.and()
        .logout()
			.permitAll();
	}

	  @Override
	  public void configure(AuthenticationManagerBuilder builder)
	          throws Exception {
	      builder.userDetailsService(userDetailsService);
	  }
	  
	  @Bean
	  public PasswordEncoder passwordEncoder() {
		  return NoOpPasswordEncoder.getInstance();
	  }
	  /*@Bean
	  public ViewResolver viewResolver() {
	      InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
	      viewResolver.setSuffix(".jsp");
	      viewResolver.setPrefix("/templates/common/");
	      return viewResolver;
	  }*/
	  
}