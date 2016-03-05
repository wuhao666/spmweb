package com.pagechoice.test.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;

public class Test {
	
	public static void main(String[] args){
		
		//List list = new ArrayList();
		Map map = new HashMap();
		
		map.put("taskId", "C20151231001");
		map.put("projectName", "Test11111");
		map.put("webId", "1");
		
		List list1 = new ArrayList();
		Map map1 = new HashMap();
		map1.put("id", "1");
		map1.put("keyword", "test1");
		
		Map map2 = new HashMap();
		map2.put("id", "2");
		map2.put("keyword", "test2");
		
		Map map3 = new HashMap();
		map3.put("id", "3");
		map3.put("keyword", "test3");
		
		list1.add(map1);
		list1.add(map2);
		list1.add(map3);
		
		map.put("data", list1);
		
		
		String str  = JSON.toJSONString(map);
		
		//String json = "json:[taskId:C20151231001,projectName:Test11111,webId:1, data:{[id:1,keyword:test1],[id:2,keyword:test2],[id:3,keyword:3],[id:4,keyword:4]}]";
		
		System.out.println(" >>>  :  "  + str);
		
		
		
		
	}
	
}
