package com.pagechoice.sys.utils;

import javax.servlet.http.HttpSession;

import com.pagechoice.sys.entity.User;

public class UserUtil {
	    public static final String USER = "user";
	    
	    public static void saveUserToSession(HttpSession session, User user) {
	        session.setAttribute(USER, user);
	    }
	    
	    public static User getUserFromSession(HttpSession session) {
	        Object attribute = session.getAttribute(USER);
	        return attribute == null ? null : (User) attribute;
	    }
	    
	    
	    
}
