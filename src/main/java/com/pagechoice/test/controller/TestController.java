package com.pagechoice.test.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pagechoice.lolth.service.LolthTaskMonitorService;

@Controller
@RequestMapping("/test")
public class TestController {
	
	@Resource
	LolthTaskMonitorService lolthTaskMonitorService;
	
	
	@RequestMapping(value="/testExport" , method=RequestMethod.GET) 
	public @ResponseBody String getData(Model model,HttpServletRequest request,HttpServletResponse response) {
		
		String str = lolthTaskMonitorService.lolthTaskMonitorAssigment();
		
	    return str;  
	}
	
	
}
