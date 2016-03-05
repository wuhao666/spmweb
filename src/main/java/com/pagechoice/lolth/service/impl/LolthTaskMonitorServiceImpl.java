package com.pagechoice.lolth.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pagechoice.lolth.dao.LolthTaskMonitorDao;
import com.pagechoice.lolth.entity.LolthTaskMonitor;
import com.pagechoice.lolth.service.LolthDataService;
import com.pagechoice.lolth.service.LolthTaskMonitorService;

@Service
@Transactional(rollbackFor = Exception.class)
public class LolthTaskMonitorServiceImpl implements LolthTaskMonitorService {
	
	@Autowired
    @Qualifier("lolthTaskMonitorDao")
	private LolthTaskMonitorDao lolthTaskMonitorDao;
	
	@SuppressWarnings("restriction")
	@Resource
	LolthDataService lolthDataService;

	
	public String lolthTaskMonitorAssigment() {
		
		List<LolthTaskMonitor> ltmList = lolthTaskMonitorDao.selectNotCompleteLolthTask();
		StringBuilder projectNames = new StringBuilder();
		List<Map<String,Object>> countList = new ArrayList<Map<String,Object>>();
		Map<String,String> map = new HashMap<String,String>();
		if(ltmList != null){
			for(int i = 0 ; i < ltmList.size() ; i++){
				projectNames.append("'");
				projectNames.append(String.valueOf(ltmList.get(i).getTaskName()));
				projectNames.append("'");
				projectNames.append(",");
			}
			projectNames.deleteCharAt(projectNames.length() - 1);
			
			System.out.println(" projectNames : > " + projectNames.toString() );
			
			countList = lolthTaskMonitorDao.selectLolthTaskCount(projectNames.toString());
			
			System.out.println(" : >>. :countList size " + countList.size());
			
			if(countList != null){
				for(int i = 0 ; i < countList.size();i++ ){
					String taskName = String.valueOf(countList.get(i).get("taskName"));
					String count = String.valueOf(countList.get(i).get("count"));
					System.out.println( "taskName :  " + taskName + ":::: count " + count);
					map.put(taskName, count);
				}
			}
			
			for(int i = 0 ; i < ltmList.size() ; i++){
				LolthTaskMonitor lolthTaskMonitor = ltmList.get(i);
				String count = map.get(lolthTaskMonitor.getTaskName());
				System.out.println(" >>> count : > " + count );
				if( count == null){
					System.out.println(" >>>>>>>>1111111111>>>>>>>>>>>>>>>>");
					lolthTaskMonitor.setAttribute1("0");
					lolthTaskMonitorDao.updateLolthTaskMonitorAttr1(lolthTaskMonitor);
				}else{
					if( lolthTaskMonitor.getAttribute1() == null){
						System.out.println(" >>>>>>>>222222222222>>>>>>>>>>>>>>>>");
						lolthTaskMonitor.setAttribute1(count);
						lolthTaskMonitorDao.updateLolthTaskMonitorAttr1(lolthTaskMonitor);
					}
					if( lolthTaskMonitor.getAttribute1() != null && lolthTaskMonitor.getAttribute2() == null){
						System.out.println(" >>>>>>>>3333333333333>>>>>>>>>>>>>>>>");
						if(!lolthTaskMonitor.getAttribute1().equals(count)){
							System.out.println(" >>>>>>>>4444444444>>>>>>>>>>>>>>>>");
							lolthTaskMonitor.setAttribute1(count);
							lolthTaskMonitorDao.updateLolthTaskMonitorAttr1(lolthTaskMonitor);
						}else{
							System.out.println(" >>>>>>>>555555555555>>>>>>>>>>>>>>>>");
							lolthTaskMonitor.setAttribute1(count);
							lolthTaskMonitorDao.updateLolthTaskMonitorAttr2(lolthTaskMonitor);
						}
					}
					if( lolthTaskMonitor.getAttribute1() != null && lolthTaskMonitor.getAttribute2() != null && lolthTaskMonitor.getAttribute3() == null){
						if(!lolthTaskMonitor.getAttribute2().equals(count)){
							lolthTaskMonitor.setAttribute1(count);
							lolthTaskMonitorDao.updateLolthTaskMonitorAttr1(lolthTaskMonitor);
						}else{
							
							/**
							 * 执行导出数据方法!
							 */
							String taskId = lolthTaskMonitor.getTaskId();
							boolean bool = lolthDataService.exportData(taskId);
							
							if(bool == true){
								lolthTaskMonitor.setAttribute3(count);
								lolthTaskMonitorDao.updateLolthTaskMonitorAttr3(lolthTaskMonitor);
							}
						}
					}
				}
			}
			
		}
		return null;
	}
	
}
