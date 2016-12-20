package com.parcel.service;

/**
 * 로그를 등록하는 서비스 인터페이스
 *
 */
public interface LogService {
	
	/**
	 * 그룹을 등록하는 작업
	 * @param log 로그 내용
	 * @param obj 로그와 관련된 객체
	 * @param detail 로그의 상세 카테고리
	 * @return
	 */
	public boolean addLog(String log, Object obj, int detail);
}
