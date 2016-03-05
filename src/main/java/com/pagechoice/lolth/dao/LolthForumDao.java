package com.pagechoice.lolth.dao;

import java.util.List;
import java.util.Map;

import com.pagechoice.lolth.entity.WebForumData;

public interface LolthForumDao {
	
	List<WebForumData> selectWebForumDataList(Map<String,Object> searchMap,Map<String,Object> pageMap);
	
	Map<String,Object> selectWebForumDataPage(Map<String,Object> searchMap,Map<String,Object> pageMap);
	
	String insertWebForumDataPage(WebForumData webForumData);
	
	WebForumData selectWebForumData(Map<String,String> map);
	
}
