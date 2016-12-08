package com.parcel.repository;

import java.util.List;

import com.parcel.entity.Message;
import com.parcel.util.Page;

public interface MessageRepository {

	public int insertMessageForGroupByProductIdx(Message message, int productIdx);

	public int insertMessageByUserIdx(Message message, int idx);

	public List<Message> findMessageListByReceiver(int idx);

	public int updateShowByIdx(int idx, int show);
	
	public Message findMEssageByIdx(int idx);

	public List<Message> findMessageListByReceiverAndPage(int idx, Page page);

	public int countMessageListByReceiver(int idx);

}
