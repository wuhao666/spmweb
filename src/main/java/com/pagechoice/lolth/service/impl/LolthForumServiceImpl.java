package com.pagechoice.lolth.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.pagechoice.lolth.dao.LolthForumDao;
import com.pagechoice.lolth.entity.WebForumData;
import com.pagechoice.lolth.service.LolthForumService;

@Service
@Transactional(rollbackFor = Exception.class)
public class LolthForumServiceImpl implements LolthForumService{
	
	@Autowired
    @Qualifier("lolthForumDao")
    private LolthForumDao lolthForumDao;
	
	public String getForumDataList(String json) {
		
		Map<String,Object> jsonMap = (Map<String, Object>) JSON.parse(json);
		Map<String,Object> searchMap =  (Map<String, Object>)jsonMap.get("search");
		Map<String,Object> pageMap =  (Map<String, Object>)jsonMap.get("pageMap");
		
		Map<String,Object> reList = new HashMap<String,Object>();
		Map<String,Object> param = lolthForumDao.selectWebForumDataPage(searchMap,pageMap);
		List<WebForumData> lolList = lolthForumDao.selectWebForumDataList(searchMap,pageMap);
		
		reList.put("page",param);
		reList.put("data",lolList);
		
		String str = JSON.toJSONString(reList);

		return str;
	}

	public String insertForumDataSingle(String json) {
		
		System.out.println(" >>>>>>> json > : > " + json);
		
		Map<String,Object> param = JSONObject.parseObject(json);
		int id = Integer.valueOf(String.valueOf(param.get("id")));
		
		System.out.println(" >>>> Id : " + id);
		
		//String.valueOf(param.get("data"));
		
		/**
		int webId = Integer.valueOf(String.valueOf(param.get("webId")));
		String forumId = String.valueOf(param.get("forumId"));
		String keyword = String.valueOf(param.get("keyword"));
		String url = String.valueOf(param.get("url"));
		//String createUser = String.valueOf(param.get("createUser"));
		//String isDelete = String.valueOf(param.get("isDelete"));
		*/
		
		int webId = 1;
		String forumId = "1002";
		String keyword = "Test1";
		//String url = String.valueOf(param.get("url"));
		String createUser = "wuhao";
		String isDelete = "0";
		
		WebForumData data = new WebForumData();
		data.setWebId(webId);
		data.setForumId(forumId);
		data.setKeyword(keyword);
		data.setCreateUser(createUser);
		data.setIsDelete("0");
		
		Map<String,String> valiMap = new HashMap<String,String>(); 
		valiMap.put("forumId", forumId);
		WebForumData validateForumData = lolthForumDao.selectWebForumData(valiMap);
		
		if(validateForumData != null){
			return "2";
		}
		
		String str = lolthForumDao.insertWebForumDataPage(data);
		
		return str;
	}
	
}
