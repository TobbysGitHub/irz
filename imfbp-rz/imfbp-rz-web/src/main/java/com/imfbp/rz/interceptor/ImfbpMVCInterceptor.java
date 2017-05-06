package com.imfbp.rz.interceptor;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.imfbp.validate.bootstrap.ValidateBootstrap;
import com.imfbp.validate.datasource.SpringMVCRequestDataSource;
import com.imfbp.validate.result.DealValidateResultInterface;
import com.imfbp.validate.result.ValidateResult;
import com.imfbp.validate.result.ValidateResults;

public class ImfbpMVCInterceptor implements HandlerInterceptor {
	
	private final static Logger log = Logger.getLogger(ImfbpMVCInterceptor.class);
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		if(handler instanceof HandlerMethod){
			HandlerMethod hm = (HandlerMethod)handler;
			RequestMapping rm = hm.getMethodAnnotation(RequestMapping.class);
			String[] urlPaths = rm.value();
			if(urlPaths == null || urlPaths.length == 0){
				return true;
			}
			
			final HttpServletResponse outMessage = response;
			SpringMVCRequestDataSource srd = new SpringMVCRequestDataSource(request,hm);
			return ValidateBootstrap.isValidate(srd, new DealValidateResultInterface(){

				@Override
				public void dealValidateResult(ValidateResults vrs) {
					
					try {
						if( vrs !=null)
						for(ValidateResult vr : vrs.getErrorResults()){
							log.debug(vr.getMessage());
						}
						outMessage.getOutputStream().write("{success:'false',errorMessage:'Data format is not correct}".getBytes());
						outMessage.getOutputStream().close();
					} catch (IOException e) {
					}
				}
				
				
			});
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

	}
	
	
	public final static void main(String[] args){
		
		System.out.println(String.class.getSimpleName());
	}
}
