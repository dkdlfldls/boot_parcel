package com.parcel.service;

import java.util.List;

import com.parcel.entity.MainPageEntity;
import com.parcel.entity.User;

/**
 * 사용자 관련 작업을 진행하는 서비스
 *
 */
public interface UserService {
	/**
	 * 회원가입
	 * @param user 회원가입 하려는 사용자의 정보
	 * @return
	 */
	public boolean join(User user);
	
	/**
	 * 로그인 
	 * @param user 로그인 하려는 사용자의 정보
	 * @return
	 */
	public User login(User user);
	
	/**
	 * 사용자 정보를 불러오는 작업
	 * @param idx 불려오는 사용자 정보의 인덱스
	 * @return
	 */
	public User getUser(int idx);
	
	/**
	 * 메인페이지에서 사용될 데이터 리스트를 불러오는 작업
	 * @param idx 사용자의 인덱스
	 * @return
	 */
	public List<MainPageEntity> getMainPageEntityList(int idx);
	
	/**
	 * 메인페이지 에서 사용될 정보를 불러오는 작업
	 * @param idx 사용자의 인덱스
	 * @return
	 */
	public MainPageEntity getMainPageEntityForUserInfo(int idx);
	
	/**
	 * 사용자 정보를 수정하는 낙업
	 * @param user 수정될 사용자에 대한 정보
	 * @return
	 */
	public boolean modifyUserInfo(User user);
}
