package com.pagechoice.lolth.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pagechoice.lolth.service.WebContrastService;

@Controller
@RequestMapping("/webContrast")
public class WebContrastController {
	
	@SuppressWarnings("restriction")
	@Resource
	 WebContrastService webContrastService;
	
	@RequestMapping(value = "/getWebContratList", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody String getWebContratList(HttpServletRequest request,HttpServletResponse response, ModelMap map){
		
		Map<String,String> param = new HashMap<String,String>();
		
		String str = webContrastService.getWebContrastList(param);
		
		System.out.println(" >>>>>> str : " + str );
		
		return str;
		
	}
	
	
	
}
