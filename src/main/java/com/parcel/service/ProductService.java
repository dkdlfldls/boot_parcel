package com.parcel.service;

import java.util.List;

import com.parcel.entity.Machine;
import com.parcel.entity.Product;
import com.parcel.entity.User;

/**
 * 제품관련 작업을 하는 서비스
 *
 */
public interface ProductService {
	//public
	
	/**
	 * 기종리스트를 불러오는 작업
	 * @return
	 */
	public List<Machine> getMachineList();
	
	/**
	 * 기종을 등록하는 작업
	 * @param product 등록될 제품 정보
	 * @return
	 */
	public boolean registerProductByUser(Product product);
	
	/**
	 * 제품정보를 불러오는 작업
	 * @param pidx 불려질 제품정보의 인덱스
	 * @return
	 */
	public Product getProductInfo(int pidx);
	
	/**
	 * 제품을 잠그는 작업
	 * @param user 잠그려는 사용자에 대한 정보
	 * @return
	 */
	public boolean lock(User user);
	
	/**
	 * 제품을 여는 작업
	 * @param user 제품을 열려고 하는 사용자에 대한 정보
	 * @return
	 */
	public boolean open(User user);
}
