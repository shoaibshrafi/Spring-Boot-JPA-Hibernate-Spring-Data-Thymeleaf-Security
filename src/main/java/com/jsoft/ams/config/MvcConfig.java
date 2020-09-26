package com.jsoft.ams.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.jsoft.ams.interceptor.RequestInterceptor;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new RequestInterceptor()).addPathPatterns("", "/", "/**");
	}


	@Override
	public void configurePathMatch(PathMatchConfigurer configurer) {
		configurer.setUseRegisteredSuffixPatternMatch(true);
	}
	
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/js/**").addResourceLocations("classpath:/static/js/");
        registry.addResourceHandler("/css/**").addResourceLocations("classpath:/static/css/");
    }
}
