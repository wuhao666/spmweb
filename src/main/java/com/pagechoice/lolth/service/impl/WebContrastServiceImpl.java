package com.pagechoice.lolth.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.pagechoice.lolth.dao.WebContrastDao;
import com.pagechoice.lolth.entity.WebContrast;
import com.pagechoice.lolth.service.WebContrastService;

@Service
@Transactional(rollbackFor = Exception.class)
public class WebContrastServiceImpl implements WebContrastService {

	@Autowired
    @Qualifier("webContrastDao")
    private WebContrastDao webContrastDao;
	
	public String getWebContrastList(Map<String, String> map) {
		
		List<WebContrast> list = webContrastDao.getWebContrastList(map);
		
		String webContrastStr = JSON.toJSONString(list);
		
		return webContrastStr;
	}
	
}
