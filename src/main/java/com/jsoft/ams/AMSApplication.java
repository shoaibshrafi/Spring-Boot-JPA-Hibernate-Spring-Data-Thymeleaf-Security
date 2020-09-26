package com.jsoft.ams;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.jsoft.ams.config.DBConfiguration;
import com.jsoft.ams.master.repository.MasterTenantRepository;	

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class})
@EnableCaching(proxyTargetClass = true)
@ComponentScan(basePackages = "com.jsoft.ams") 
public class AMSApplication {

	@Autowired DBConfiguration dbConfiguration;
	
	public static void main(String[] args) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode("admin");
         
        System.out.println("***********"+encodedPassword);
		SpringApplication.run(AMSApplication.class, args);
	}
	
	@Autowired MasterTenantRepository masterTenantRepository;
	
	@EventListener(ApplicationReadyEvent.class)
    public void startApp() {
      System.out.println("Application is ready please do something");
      masterTenantRepository.findAll().stream().forEach(mt->{
    	  try {
			dbConfiguration.addTenant(mt.getId(), mt.getDb(), null, mt.getUsername(), mt.getPassword());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	  
      });
    }
}
