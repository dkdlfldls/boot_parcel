package com.parcel.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

/**
 * 요청 한 request에 대한 ip주소를 수집한다.
 * @author user
 *
 */
@Component
public class IpGather {
	
	public String ip(HttpServletRequest req) {
		//HttpServletRequest req = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();

        String ip = req.getHeader("X-FORWARDED-FOR");

        if (ip == null)
            ip = req.getRemoteAddr();
        
        return ip;
	}
}
