package com.parcel.repository;

import java.util.List;

import com.parcel.entity.MainPageEntity;
import com.parcel.entity.User;

public interface UserRepository {
	
	public boolean join(User user);
	public int login(User user);
	
	public User getUser(int idx);
	public List<MainPageEntity> getMainPageEntityList(int idx);
	public MainPageEntity getMainPageEntityForUserInfo(int idx);
	public User findUserById(String id);
	public int updateUser(User user);
}
