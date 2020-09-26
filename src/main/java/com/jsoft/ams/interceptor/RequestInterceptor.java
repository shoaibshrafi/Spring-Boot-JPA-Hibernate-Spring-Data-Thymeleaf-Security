package com.jsoft.ams.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.jsoft.ams.exception.ApplicationException;
import com.jsoft.ams.multitenancy.TenantContextHolder;
import com.jsoft.ams.user.model.UserDetail;
import com.jsoft.ams.util.AppUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class RequestInterceptor extends HandlerInterceptorAdapter {

	//public static final String TENANT_HEADER_NAME = "X-TENANT-ID";
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		UserDetail currentLoggedInUser = AppUtil.getCurrentLoggedInUser();

		log.info("User is logged in {}", currentLoggedInUser != null ? currentLoggedInUser.getUsername() : "N/A");

		if(currentLoggedInUser != null) {
		    TenantContextHolder.setTenantId(currentLoggedInUser.getTenant());
		}else if(request.getParameter("tenant")!=null) {
			log.info("User coming from parameter {}", request.getParameter("tenant"));
		    TenantContextHolder.setTenantId(request.getParameter("tenant"));
		}else {
			//throw new ApplicationException(HttpStatus.NOT_FOUND, "No tenant defined");
		}
				
		return super.preHandle(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);

		TenantContextHolder.clear();

		UserDetail currentLoggedInUser = AppUtil.getCurrentLoggedInUser();

		log.info("User {} model view {}", currentLoggedInUser != null ? currentLoggedInUser.getUsername() : "N/A", (modelAndView != null ? "Not null" : "null"));
		if(currentLoggedInUser != null) {
			if(modelAndView != null) {
				modelAndView.getModelMap().put("userObj", currentLoggedInUser);
				modelAndView.addObject("userObj", currentLoggedInUser);
			}
		}
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)throws Exception {
		TenantContextHolder.clear();
	}

	
}
