package com.pagechoice.sys.controller;

import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pagechoice.sys.entity.User;
import com.pagechoice.sys.service.LoginService;
import com.pagechoice.sys.service.UserService;
import com.pagechoice.sys.utils.UserUtil;

/**
 * @author wuhao
 */
@Controller
public class LoginController {
	
   @Resource
	LoginService loginService;
   
   @Resource
   UserService userService;
	
	@RequestMapping( "login")
	public void login(HttpServletRequest request,HttpServletResponse response, HttpSession session,
			@RequestParam(value = "username", required = true)String username,
			@RequestParam(value = "passwd", required = true)String passwd){
		PrintWriter out;
		response.setContentType("application/json");
    	
		System.out.println("");
		
		try {			
			String bool = "0";
			
			User user = userService.selectUser(username, passwd);
			
			/**
			 * 0用户没有
			 * -1用户作废
			 * 1正确
			 */
			if(user == null){
				bool = "0";
			}else{
				if(user.getIsdelete().equals("1")){
					bool = "-1";
				}else{
					bool = "1";
					UserUtil.saveUserToSession(session, user);
				}
			}
			
			out = response.getWriter();
			out.print(bool);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	 @RequestMapping(value = "/logout")
	 public String logout(HttpSession session) {
	    session.removeAttribute("user");
	    return "../../login";
	 }
	
	 
}
