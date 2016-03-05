package com.pagechoice.sys.service;

import com.pagechoice.sys.entity.User;

public interface UserService {
	
	public String validateUser(String username,String passwd);
	
	public User selectUser(String username,String passwd);
	 
}
