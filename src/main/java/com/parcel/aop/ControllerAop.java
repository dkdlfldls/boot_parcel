package com.parcel.aop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.jboss.logging.Logger;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;

@Aspect
@Component
public class ControllerAop {
	private Logger logger = Logger.getLogger(getClass());
	
	private ServletWebRequest servletContainer;
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	private final Class BINDING_RESULT;
	
	public ControllerAop() {
		BINDING_RESULT = BeanPropertyBindingResult.class;
	}
	
	@Before("execution(* *..*Controller.*(..))")
	public void traceMethod(JoinPoint point) {
		String className = point.getSignature().getDeclaringTypeName();
		String name = point.getSignature().getName();
		Object[] newValues = point.getArgs();
		
		logger.info("className : " + className + "    " + "name : " + name);
		for (Object o : newValues) {//파라미터가 나오긴 해야 좋을꺼 같은데 null 처리해준다고 하면 비싸지지 않을까?
			if (o != null) {
				logger.info("value : " + o.toString());
			} else {
				logger.info("value : null");
			}
		}
	}
	
	
	@Around("execution(* *..*Controller.*AndValidate(..))")
	public String checkValid(ProceedingJoinPoint point) throws Throwable {
		logger.info("validation aop");
		Object[] newValues = point.getArgs();
		
		BindingResult result = (BindingResult)findObjectByClass(newValues, BINDING_RESULT);
		if (result == null) {
			logger.info("validation aop bindResult null");
			return (String)point.proceed();
		} 
		if(!result.hasErrors()){
			logger.info("validation aop no error");
		    return (String)point.proceed();
		} else {
			//에러가 있는경우
			logger.info("validation aop has error : " + result.getAllErrors().toString());
			request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
			response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
			
			String prePage = request.getHeader("Referer");
			String contentType = request.getHeader("Content-Type");
			//ajax요청인경우
			if (contentType.equals("application/json")) {
				return "false";
			}
			//ajax가 아닌경우
			if (prePage == null) {
				return "redircet:/";
			} else {
				
				String[] prePageParse = prePage.split("/");
				if (prePageParse.length > 3) {// http://localhost:8080/ 여기까지가 길이가3 이 이상 있는경우
					logger.info("validation aop pre page is exist : " + makeUrl(prePageParse));
					return "redirect:" + makeUrl(prePageParse);
				} else {
					logger.info("validation aop pre page is not exist");
					return "redirect:/";
				}
				
			}
		}
		
	}
	public String makeUrl(String[] preUrl) {
		StringBuilder sb = new StringBuilder();
		for (int i = 3; i < preUrl.length; i++) {
			sb.append("/").append(preUrl[i]);
		}
		return sb.toString();
	}
	
	public Object findObjectByClass(Object[] o, Class c) {
		for (int i = 0; i < o.length; i++) {
			if (o[i].getClass() == c) {
				return o[i];
			}
		}
		return null;
		
	}
}
