package com.model2.mvc.service.user.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.user.UserDao;
import com.model2.mvc.service.user.UserService;
import com.model2.mvc.service.domain.User;

@Service("userServiceImpl")
public class UserServiceImpl implements UserService{
	
	///Field
	@Autowired
	@Qualifier("userDaoImpl")
	private UserDao userDao;
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	///Constructor
	public UserServiceImpl() {
		System.out.println("::" + getClass() + " default Constructor..");
	}

	///Method
	public int addUser(User user) throws Exception {
		return userDao.insertUser(user);
	}
	
	public boolean checkDuplication(String userId) throws Exception {
		boolean result=true;
		User user=userDao.findUser(userId);
		if(user != null) {
			result=false;
		}
		return result;
	}

	public User loginUser(User user) throws Exception {
			User dbUser=userDao.findUser(user.getUserId());

			if(! dbUser.getPassword().equals(user.getPassword())){
				throw new Exception("로그인에 실패했습니다.");
			}
			
			return dbUser;
	}

	public User getUser(String userId) throws Exception {
		return userDao.findUser(userId);
	}
	
	public int updateUser(User user) throws Exception {
		return userDao.updateUser(user);
	}
	
	public int deleteUser(String userId) throws Exception{
		return userDao.deleteUser(userId);
	}

	public Map<String,Object> getUserList(Search search) throws Exception {
		return userDao.getUserList(search);
	}

	
}