package com.pagechoice.test.service.impl;

import java.util.HashMap;
import java.util.Map;

import lakenono.db.DBBean;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pagechoice.test.service.DBExecService;

@Service
@Transactional(rollbackFor = Exception.class)
public class DBExecServiceImpl implements DBExecService {

	public String selectDBData(Map<String, Object> map) {
		
		if(map.get("taskId") == null){
			return null;
		}
		String taskId = map.get("taskId").toString();
		if(map.get("bean") == null){
			return null;
		}
		String bean = map.get("bean").toString();
		Class<? extends DBBean> cla = null;
		String str = "";
		Map<String,String> param = new HashMap<String,String>();
		//param = (Map<String, String>) map.get("taskId");
		try {
			cla = (Class<? extends DBBean>) Class.forName(bean);
			//str = DBBean.getSelectColumnDatas(cla, taskId, param,"1","10");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return str;
	}


}
