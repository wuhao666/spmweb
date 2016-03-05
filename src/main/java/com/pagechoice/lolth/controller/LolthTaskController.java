package com.pagechoice.lolth.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.pagechoice.lolth.service.LolthTaskService;
import com.pagechoice.sys.utils.StringUtils;



/**
 * 
 * @author wuhao
 * @reason 任务控制层/
 */
@Controller
@RequestMapping("/lolthTask")
public class LolthTaskController {
	
	 @Resource
	 LolthTaskService lolthTaskService;
	
	 /**
	  * 
	  * @param request
	  * @param response
	  * @param map
	  * @return
	  * 任务管理模块
	  */
	@RequestMapping(value = "/taskmanagerPage", method = { RequestMethod.GET, RequestMethod.POST })
	public String taskmanagerPage(HttpServletRequest request,HttpServletResponse response, ModelMap map){
			
		return "task/taskmanager";
	}
	
	@RequestMapping(value = "/createtaskPage", method = { RequestMethod.GET, RequestMethod.POST })
	public String createtaskPage(HttpServletRequest request,HttpServletResponse response, ModelMap map){
			
		return "task/createtask";
	}
	 
	 
	@RequestMapping(value = "/getLolthTaskId", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody String getLolthTaskId(HttpServletRequest request,HttpServletResponse response, ModelMap map
			){
		
		String taskId = StringUtils.getUniqueString(20);
		
		System.out.println(" >>>> : taskId > : " + taskId );
		
		return taskId;
	}
	 
	 
	 
	 
	@RequestMapping(value = "/startLolthTask/{json}", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody String startLolthTask(HttpServletRequest request,HttpServletResponse response, ModelMap map,
			@PathVariable String json){
		
		System.out.println("  >>>  json  >> " + json);
		
		String str = lolthTaskService.startLolthTask(json);
		
		return str;
	}
	
	@RequestMapping(value = "/getLolthTaskList/{json}", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody String selectLolthTask(HttpServletRequest request,HttpServletResponse response, ModelMap map,
			@PathVariable String json){
		
	
		String str = lolthTaskService.getLolthTaskList(json);
	
		System.out.println(">>>>>>>>>>>>>>>>>> json >>>>>>" + str );
		
		return str;
		
	}
	
	@RequestMapping(value = "/getLolthTaskDetail", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody String getLolthTaskDetail(HttpServletRequest request,HttpServletResponse response, ModelMap map,
			@RequestParam(value = "taskId", required = true)String taskId,
			@RequestParam(value = "classBean", required = true)String classBean
			){
		System.out.println(" >>>> taskId > : " + taskId);
		System.out.println(" >>>> classBean > : " + classBean);
		
		Map<String,String> param = new HashMap<String,String>();
		param.put("taskId", "C20160105035257030498");
		param.put("classBean", "lolthx.autohome.bbs.bean.AutoHomeBBSBean");
		String str = lolthTaskService.getLolthTask(param);
		
		System.out.println(" >>>>> : >  str : > " + str );
		
		return str;
	}
	
	
	
	@RequestMapping(value = "/newLolthTask", method = { RequestMethod.GET, RequestMethod.POST })
	public String newLolthTask(HttpServletRequest request,HttpServletResponse response, ModelMap map){
		
		return "lolth/newLolthTask";
	}
}
