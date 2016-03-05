package com.pagechoice.lolth.dao;

import java.util.List;
import java.util.Map;

import com.pagechoice.lolth.entity.LolthTask;


public interface LolthTaskDao {
	
	String saveLolthTask(LolthTask lolthTask);
	
	String updateLolthTask();
	
	Map<String,Object> selectLolTaskPageDetail(Map<String,Object> searchMap,Map<String,Object> pageMap);
	
	List<LolthTask> selectLolthTask(Map<String,Object> searchMap,Map<String,Object> pageMap);
	
	LolthTask selectLolthTaskSingle(Map<String,String> map);
	
	
	
}
