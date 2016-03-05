package com.pagechoice.test.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.pagechoice.test.service.DBExecService;

@Controller
@RequestMapping("/testTable")
public class TestTableController {
	
	@Resource
	DBExecService dbExecService;
	
	@RequestMapping(value="/getTestTable" , method=RequestMethod.GET) 
	public String getTestTable(Model model,HttpServletRequest request,HttpServletResponse response) {
		
		System.out.println(">>>>>>>>>>>>>>>>  : >>> : >  getTestTable >  > > :: " );
		
	    return "/test/test";  
	}
	
	@RequestMapping(value="/getJsonData" ,method = { RequestMethod.GET, RequestMethod.POST }) 
	@ResponseBody 
	public void getJsonData(HttpServletRequest request,HttpServletResponse response,ModelMap map,
			@RequestBody String json) {
		//PrintWriter out;
		//response.setContentType("application/json");
    	//%7B"taskId":"C201601010001","taskName":"汽车之家爬取","webId":"1","startDate":"2015-01-01","endDate":"2015-12-31",
		//"detailType":"频道",
		//"data":[%7B"id":"1","keyword":"test1"%7D,%7B"id":"2","keyword":"test2"%7D,%7B"id":"3","keyword":"test3"%7D]%7D
		//%7B"taskId":%20"C20160108023015033322","bean":%20"lolthx.autohome.bbs.bean.AutoHomeBBSBean"%7D
		//
		//System.out.println(" >>>>>>>>>>>>>>>>>>> >getJsonData > ");
		
		System.out.println(">>>json : >   " + json);
		
		Map<String,Object> jsonMap = JSONObject.parseObject(json);
		String taskId = String.valueOf(jsonMap.get("taskId"));
		String bean = String.valueOf(jsonMap.get("bean"));
		
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("taskId", taskId);
		param.put("bean", bean);
		
		String str = dbExecService.selectDBData(param);
		
		System.out.println(">>>>>>>>>>>>> str : ");
		System.out.println(str);
		
		
		
		/**
		try {			
			Map<String,Object> param = new HashMap<String,Object>();
			param.put("taskId", taskId);
			param.put("bean", bean);
			
			String str = dbExecService.selectDBData(param);
			
		
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		*/
	
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
