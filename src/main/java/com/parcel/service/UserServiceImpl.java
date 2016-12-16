package com.parcel.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.parcel.entity.MainPageEntity;
import com.parcel.entity.User;
import com.parcel.repository.UserRepository;
import com.parcel.util.DataSecurity;
import com.parcel.util.TextMaker;
import com.parcel.util.LogProperties;

@Service
public class UserServiceImpl implements UserService{
	
	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private DataSecurity dataSecurity;
	
	@Autowired
	private LogService logService;
	
	@Autowired
	private LogProperties prop;
	
	@Autowired
	private TextMaker logMaker;
	
	@Override
	@Transactional
	public boolean join(User user) {
		logService.addLog(logMaker.join(user.getId()), user, prop.getInt("join"));
		//비밀번호 암호화
		user.setPw(dataSecurity.encrypt(user.getPw()));
		if (userRepository.join(user)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	@Transactional
	public User login(User user) {
		User tempUser = userRepository.findUserById(user.getId());
		
		if (tempUser != null && dataSecurity.check(user.getPw(), tempUser.getPw())) {
			logService.addLog(logMaker.login(tempUser.getIdx(), tempUser.getId()), tempUser, prop.getInt("login"));
			return tempUser;
		} else {
			//security에서 걸러져서 여긴 못들어옴
			//msg = tempUser.getId() + properties.getProperty("user.login.fail");
			//logService.addLog(msg, tempUser);
			return null;
		}
	}

	@Override
	public User getUser(int idx) {
		
		return userRepository.findUserByIdx(idx);
	}

	@Override
	public List<MainPageEntity> getMainPageEntityList(int idx) {
		return userRepository.findMainPageEntityListByIdx(idx);
	}

	@Override
	public MainPageEntity getMainPageEntityForUserInfo(int idx) {
		
		return userRepository.findMainPageEntityByIdx(idx);
	}

	@Override
	public boolean modifyUserInfo(User user) {
		// TODO Auto-generated method stub
		user.setPw(dataSecurity.encrypt(user.getPw()));
		
		if (userRepository.updateUser(user) > 0 ) {
			return true;
		} else {
			return false;
		}
		
	}

}
