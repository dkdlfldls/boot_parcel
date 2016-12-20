package com.parcel.repository;

import java.util.List;

import com.parcel.entity.Log;
import com.parcel.util.Page;

/**
 * 사용이력 관련 DB작업 인터페이스
 */
public interface LogRepository {
	
	//로그 입력
	public int insertLog(Log log);

	public int findCountAllLog();

	public List<Log> findLogByPage(Page page);

	public int findCountLogBySearch(Page page);

	public List<Log> findLogByPageAndSearch(Page page);

	public int findCountLog(Page page);

	public List<Log> findLogByPageAndCategory(Page page);

	public int findCountLogBySearchAndCategory(Page page);

	public List<Log> findLogByPageAndSearchAndCategory(Page page);
}
