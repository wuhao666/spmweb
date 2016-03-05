package com.pagechoice.sys.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;

public class RegisterController {
	
	@RequestMapping( "register")
	public String login(HttpServletRequest request,HttpServletResponse response){
		
		return "";
	}
	
}
