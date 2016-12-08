package com.parcel.service;

import java.lang.reflect.Method;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parcel.entity.Log;
import com.parcel.repository.LogRepository;

@Service
public class LogServiceImpl implements LogService {
	
	@Autowired
	private LogRepository logReposityry;

	private final String GET_IDX_METHOD = "getIdx";


	@Override
	public boolean addLog(String log, Object obj, int detail) {
		// TODO Auto-generated method stub
		Class c;
		
		Log logObj = new Log();
		logObj.setLog(log);
		logObj.setDetail(detail);
		if (obj == null) {
			if (logReposityry.insertLog(logObj) > 0) {
				return true;
			} else {
				return false;
			}
		} else {
			try {
				c = obj.getClass();
				//System.out.println("class : " + identifyClassName(c.getName()));
				//Obj에서 idx 가져올 메서드
				Method getIdxInObj = c.getMethod(GET_IDX_METHOD);
				//logObj의 idx값을 저장할 어떤 메서드
				Method setIdxForLog = logObj.getClass().getMethod("set" + identifyClassName(c.getName()), Integer.TYPE);
				//obj에서 idx값을 get
				int idx = (int) getIdxInObj.invoke(obj, null);
				//logObj에 idx값을 set
				setIdxForLog.invoke(logObj, idx);
				
				
				if (logReposityry.insertLog(logObj) > 0) {
					return true;
				} else {
					return false;
				}
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
			
		}
		
	}
	
	//패키지명 빼고 클래스 이름만 
	public String identifyClassName(String str) {
		String[] temp = str.split("\\.");
		
		return temp[temp.length - 1];
	}

}
