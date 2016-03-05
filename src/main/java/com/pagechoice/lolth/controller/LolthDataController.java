package com.pagechoice.lolth.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pagechoice.lolth.service.LolthDataService;

@Controller
@RequestMapping("/lolthData")
public class LolthDataController {
	
	@SuppressWarnings("restriction")
	@Resource
	LolthDataService lolthDataService;
	
	@RequestMapping(value = "/getLolthData", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody String getLolthData(HttpServletRequest request,HttpServletResponse response, ModelMap map
			
			){
		
		String testJson = 
				"{\"dbBean\":\"lolthx.autohome.bbs.bean.AutoHomeBBSBean\",\"taskId\":\"C20160105035257030498\","
				+ "\"search\":{\"text\":\"1\",\"content\":\"1\"},\"page\":{\"currentPage\":\"1\",\"pageSize\":\"15\"}}";
		
		String str = lolthDataService.getLolthDataList(testJson);
		
		System.out.println(" >>>> str : >>> " + str );
		
		return str;
	}
	
	
}
