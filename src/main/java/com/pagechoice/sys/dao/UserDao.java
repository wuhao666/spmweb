package com.pagechoice.sys.dao;

import com.pagechoice.sys.entity.User;

public interface UserDao{

	public String validateUser(String username,String passwd);
	
	public User selectUser(String username,String passwd);
}
