package com.parcel.service;

import java.util.List;

import com.parcel.entity.Log;
import com.parcel.entity.Machine;
import com.parcel.entity.Product;
import com.parcel.util.Page;

/**
 * 관리자 서비스 인터페이스
 *
 */
public interface AdminService {
	
	/**
	 * 로그리스트를 페이징하여 불러오는 작업
	 * @param page
	 * @return
	 */
	public List<Log> getLogList(Page page);

	/**
	 * 검색된 로그리스트를 페이징하여 불러오는 작업
	 * @param page
	 * @return
	 */
	public List<Log> getSearchResultOfLogList(Page page);

	/**
	 * 기종 리스트를 페이징하여 불러오는 작업
	 * @param page
	 * @return
	 */
	public List<Machine> getMachineListByPage(Page page);

	/**
	 * 기종 리스트를 전부 불러오는 작업
	 * @return
	 */
	public List<Machine> getMachineList();

	/**
	 * 기종에 대한 정보를 수정하는 작업
	 * @param machine
	 * @return
	 */
	public boolean modifyMachineName(Machine machine);

	/**
	 * 기종을 추가하는 작업
	 * @param machine
	 * @return
	 */
	public boolean addMachine(Machine machine);

	/**
	 * 기종을 삭제하는 작업
	 * @param machine
	 * @return
	 */
	public boolean deleteMachine(Machine machine);

	/**
	 * 제품을 페이징하여 불러오는 작업
	 * @param page
	 * @return
	 */
	public List<Product> getProductList(Page page);

}
