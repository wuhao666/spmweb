package com.pagechoice.sys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pagechoice.sys.dao.LoginDao;
import com.pagechoice.sys.entity.User;
import com.pagechoice.sys.service.LoginService;

@Service
@Transactional(rollbackFor = Exception.class)
public class LoginServiceImpl implements LoginService{
	
	@Autowired
    @Qualifier("loginDao")
    private LoginDao loginDao;

	public boolean validateUser(User user) {
		return false;
	}
	
	
	
}
