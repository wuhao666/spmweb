package com.pagechoice.lolth.dao;

import java.util.List;
import java.util.Map;

import com.pagechoice.lolth.entity.LolthTaskMonitor;

public interface LolthTaskMonitorDao {
	
	List<LolthTaskMonitor> selectNotCompleteLolthTask();
	
	String infoLolthTaskMonitor(LolthTaskMonitor lolthTaskMonitor);
	
	List<Map<String,Object>> selectLolthTaskCount(String projectNames);
	
	String updateLolthTaskMonitorAttr1(LolthTaskMonitor lolthTaskMonitor);
	
	String updateLolthTaskMonitorAttr2(LolthTaskMonitor lolthTaskMonitor);
	
	String updateLolthTaskMonitorAttr3(LolthTaskMonitor lolthTaskMonitor);
	
}
