package com.parcel.service;

import java.util.List;

import com.parcel.entity.Message;
import com.parcel.util.Page;

/**
 * 메시지 관련 작업을 하는 서비스
 *
 */
public interface MessageService {

	/**
	 * 메시지 리스트를 불러오는 작업
	 * @param idx
	 * @return
	 */
	List<Message> getMessageList(int idx);

	/**
	 * 메시지를 확인하는 작업
	 * @param idx 확인한 메시지의 인덱스
	 * @return
	 */
	boolean checkMessage(int idx);
	
	/**
	 * 메시지 리스트를 페이징하여 불러오는 작업
	 * @param idx
	 * @param page
	 * @return
	 */
	List<Message> getMessageListForPaging(int idx, Page page);

}
