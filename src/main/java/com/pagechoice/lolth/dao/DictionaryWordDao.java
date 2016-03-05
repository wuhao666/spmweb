package com.pagechoice.lolth.dao;

import java.util.List;
import java.util.Map;

import com.pagechoice.lolth.entity.DictionaryWord;
import com.pagechoice.lolth.entity.DictionaryWordDetail;

public interface DictionaryWordDao {
	
	Map<String,Object>  selectDictionaryWordPage(Map<String,Object> searchMap,Map<String,Object> pageMap);
	
	List<DictionaryWord> selectDictionaryWordList(Map<String,Object> searchMap,Map<String,Object> pageMap);
	
	Map<String,Object>  selectDictionaryWordDetailPage(Map<String,Object> searchMap,Map<String,Object> pageMap);
	
	List<DictionaryWordDetail> selectDictionaryWordDetailList(Map<String,Object> searchMap,Map<String,Object> pageMap);
	
}
