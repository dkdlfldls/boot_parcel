package com.parcel.service;

import java.util.List;

import com.parcel.entity.MainPageEntity;
import com.parcel.entity.User;


public interface UserService {
	public boolean join(User user);
	public User login(User user);
	public User getUser(int idx);
	public List<MainPageEntity> getMainPageEntityList(int idx);
	public MainPageEntity getMainPageEntityForUserInfo(int idx);
	public boolean modifyUserInfo(User user);
}
