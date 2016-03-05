package com.pagechoice.sys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pagechoice.sys.dao.UserDao;
import com.pagechoice.sys.entity.User;
import com.pagechoice.sys.service.UserService;

@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {

	@Autowired
    @Qualifier("userDao")
    private UserDao userDao;

	public String validateUser(String username, String passwd) {
		return userDao.validateUser(username,passwd);
	}

	public User selectUser(String username, String passwd) {
		return userDao.selectUser(username, passwd);
	}

	
	
}
