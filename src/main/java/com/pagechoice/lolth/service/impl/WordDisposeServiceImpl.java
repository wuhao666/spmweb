package com.pagechoice.lolth.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.pagechoice.lolth.dao.DictionaryWordDao;
import com.pagechoice.lolth.dao.WebSpiderDao;
import com.pagechoice.lolth.entity.DictionaryWordDetail;
import com.pagechoice.lolth.service.WordDisposeService;

public class WordDisposeServiceImpl implements WordDisposeService {
	
	@Autowired
    @Qualifier("dictionaryWordDao")
    private DictionaryWordDao dictionaryWordDao;
	
	
	@Autowired
    @Qualifier("webSpiderDao")
    private WebSpiderDao webSpiderDao;
	
	public String wordDisposeService(Map<String, String> map) {
		/**
		List<DictionaryWordDetail> dwdlist = dictionaryWordDao.selectDictionaryWordDetail(map);
		
		List<Map<String,Object>> listDatas = webSpiderDao.selectWebSpiderData(map);
		
		
		 * 根据数据循环校验
		 * 多个子明细表数据
		
		DictionaryWordDetail dwd = null;
		List<List> list = new ArrayList<List>();
		for(int i = 0 ; i < dwdlist.size() ; i++){
			List<Map<String,Object>> wordsList = new ArrayList<Map<String,Object>>();
			Map<String,Object> param = new HashMap<String,Object>();
			
			dwd = new DictionaryWordDetail();
			dwd = dwdlist.get(i);
			String name = dwd.getName();
			String words = dwd.getWords();
			
			param.put("name", name);
			String[] wordsStr = words.split(",");
			for(int w = 0 ; w < wordsStr.length ; w++){
				
				
				
			}
			
			
		}
		
		 */
		
		
		
		return null;
	}

}
