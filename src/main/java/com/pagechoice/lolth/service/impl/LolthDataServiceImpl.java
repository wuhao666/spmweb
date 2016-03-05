package com.pagechoice.lolth.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import lakenono.db.DBBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.pagechoice.lolth.dao.LolthDataDao;
import com.pagechoice.lolth.dao.LolthTaskDetailDao;
import com.pagechoice.lolth.entity.LolthTaskDetail;
import com.pagechoice.lolth.service.LolthDataService;

@Service
@Transactional(rollbackFor = Exception.class)
public class LolthDataServiceImpl implements LolthDataService {
	
	@Autowired
    @Qualifier("lolthDataDao")
	private LolthDataDao lolthDataDao;
	
	@Autowired
    @Qualifier("lolthTaskDetailDao")
	private LolthTaskDetailDao lolthTaskDetailDao;
	
	
	public String getLolthDataList(String json) {
		//虚拟json串 解析json 
		Map<String,Object> param = JSONObject.parseObject(json);
		
		Map<String,Object> searchMap = (Map<String, Object>) param.get("search");
		
		Map<String,Object> pageMap = (Map<String, Object>) param.get("page");
		
		String dbBean = String.valueOf(param.get("dbBean"));
		String taskId = String.valueOf(param.get("taskId"));
		
		String id = String.valueOf(searchMap.get("id"));
		
		String currentPage = String.valueOf(pageMap.get("currentPage"));
		
		String str = null;
		try {
			str = DBBean.getSelectColumnDatas(Class.forName(dbBean), taskId, searchMap, pageMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println(" test test");
		
		return str;
	}

	public boolean exportData(String taskId) {
		List<LolthTaskDetail> list = new ArrayList<LolthTaskDetail>();
		list = lolthTaskDetailDao.selectLolthTaskDetailByTaskId(taskId);
		boolean bool = true;
		if(list != null){
			for(int i = 0 ; i < list.size() ; i++){
				String downloadUrl = list.get(i).getDownloadUrl();
				if(downloadUrl != null){
					
				}else{
					String id = String.valueOf(list.get(i).getId());
					String tableName = list.get(i).getTableName();
				
					downloadUrl = lolthDataDao.exportDataByTableName(tableName);
					
					System.out.println(">>>>>>>>>>>>>>>>>>>>downloadUrl : > " + downloadUrl);
					if( downloadUrl != null){
						String isUpdateUrl = lolthTaskDetailDao.updateLolthTaskDetailDownloadUrl(id, downloadUrl);
						if("0".equals(isUpdateUrl)){
							bool = false;
						}
					}else{
						bool = false;
					}
				}	
			}
		}
		return bool;
	}
}
