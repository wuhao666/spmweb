package com.pagechoice.test.test;

import java.util.List;
import java.util.Map;

import org.seleniumhq.jetty7.util.ajax.JSON;

import com.alibaba.fastjson.JSONObject;

public class Test1 {
	
	public static void main(String[] args){
		String 	json = "{\"webId\":\"1\",\"projectName\":\"Test11111\",\"taskId\":\"C20151231001\","
				+ "\"data\":[{\"id\":\"1\",\"keyword\":\"test1\"},{\"id\":\"2\",\"keyword\":\"test2\"},{\"id\":\"3\",\"keyword\":\"test3\"}]}";

	
		Map<String,Object> map = JSONObject.parseObject(json);
		
		System.out.println( " webId : " + map.get("webId").toString());
		System.out.println( " projectName : " + map.get("projectName").toString());
		System.out.println( " taskId : " + map.get("taskId").toString());
		
		List<Map<String,String>> list = (List<Map<String,String>>) map.get("data");
		for(int i = 0 ; i < list.size() ; i++){
			System.out.println(" >>>>>> id  : " + list.get(i).get("id") + " >>>  keyword : " + list.get(i).get("keyword") ); 
		}
		
	}
	
}
