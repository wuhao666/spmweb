package com.pagechoice.lolth.service;

import java.util.Map;

public interface LolthTaskService {
	
	String startLolthTask(String json);
	
	String getLolthTaskList(String json);
	
	//single Lolth Task
	String getLolthTask(Map<String,String> map);
	
	
}
