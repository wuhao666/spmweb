package com.pagechoice.sys.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	@RequestMapping(value = "index")
	public String index(HttpServletRequest request){
		
		return "/task/taskmanager";
	}

}
