package com.pagechoice.lolth.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pagechoice.lolth.service.LolthForumService;

@Controller
@RequestMapping("/forumData")
public class ForumDataController {
	
	 @SuppressWarnings("restriction")
	@Resource
	 LolthForumService lolthForumService;
	
	@RequestMapping(value = "/getForumDataList{json}", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody String selectLolthTask(HttpServletRequest request,HttpServletResponse response, ModelMap map,
			@PathVariable String json){
		
		String str = lolthForumService.getForumDataList(json);
	
		System.out.println(">>>>>>>>>>>>>>>>>> json >>>>>>" + str );

		return str;
		
	}
	
	@RequestMapping(value = "/insertForumDataSingle/{json}", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody String insertForumDataSingle(HttpServletRequest request,HttpServletResponse response, ModelMap map,
			@PathVariable String json){
		
		String str = lolthForumService.insertForumDataSingle(json);
		
		System.out.println(">>>>>>>>>>>>>>>>>> str >>>>>>" + str );
		
		return str;
		
	}
	
}
