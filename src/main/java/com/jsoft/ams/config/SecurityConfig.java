package com.jsoft.ams.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.jsoft.ams.util.CustomAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    
	@Autowired
	private UserDetailsService userDetailsService;

	
	@Override
    public void configure(WebSecurity web) throws Exception {
        web
            .ignoring()
                .antMatchers("/static/**, /js/**","/css/**","/img/**","/webjars/**");
    }
    
	
	@Override
	  protected void configure(HttpSecurity http) throws Exception {
		http.addFilterBefore(authenticationFilter(), UsernamePasswordAuthenticationFilter.class);
		http.csrf().disable()
        .authorizeRequests()
        	//.antMatchers(HttpMethod.GET, "/js/**").permitAll()
        	//.antMatchers(HttpMethod.GET, "/css/**").permitAll()
        	//.antMatchers(HttpMethod.GET, "/login").permitAll()
			//.antMatchers("/user/**").hasAnyAuthority("user_view")
			.anyRequest().authenticated()
        .and()
        .formLogin()
			.loginPage("/login")
			.defaultSuccessUrl("/home")
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
	  
	  @Bean(name = "passwordEncoder")
	  public PasswordEncoder passwordEncoder() {
	  	return new BCryptPasswordEncoder();
	  }	 
	  
	  /*@Bean
	  public ViewResolver viewResolver() {
	      InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
	      viewResolver.setSuffix(".jsp");
	      viewResolver.setPrefix("/templates/common/");
	      return viewResolver;
	  }*/	  
	  
	  public CustomAuthenticationFilter authenticationFilter() throws Exception {
		  CustomAuthenticationFilter filter = new CustomAuthenticationFilter();
		  filter.setAuthenticationManager(authenticationManagerBean());
		  return filter;
	  }
	  
	  @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
	  @Override
	  public AuthenticationManager authenticationManagerBean() throws Exception {
		  return super.authenticationManagerBean();
	  }
	  
	  /*
	 * @Bean public StaticApplicationContext staticApplicationContext() { return new
	 * StaticApplicationContext(); }
	 */
}