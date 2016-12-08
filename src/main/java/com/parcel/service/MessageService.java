package com.parcel.service;

import java.util.List;

import com.parcel.entity.Message;
import com.parcel.util.Page;

public interface MessageService {

	List<Message> getMessageList(int idx);

	boolean checkMessage(int idx);
	
	List<Message> getMessageListForPaging(int idx, Page page);

}
