package com.pagechoice.test.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pagechoice.test.service.DBExecService;

@Controller
@RequestMapping("/rest")
public class JsonTestController {
	
	@Resource
	DBExecService dbExecService;
	
	@RequestMapping(value="/jsonfeed" , method=RequestMethod.GET) 
	//@PathVariable
	public @ResponseBody Object getJSON(Model model,HttpServletRequest request,HttpServletResponse response) {  
		
		System.out.println(111111);
		
	    return "";  
	}  
	
	@RequestMapping(value="/getData" , method=RequestMethod.GET) 
	public @ResponseBody String getData(Model model,HttpServletRequest request,HttpServletResponse response) {
		
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("taskId", "C20160108023015033322");
		param.put("bean", "lolthx.autohome.bbs.bean.AutoHomeBBSBean");
		
		String str = dbExecService.selectDBData(param);
		
	    return str;  
	}
	
}
