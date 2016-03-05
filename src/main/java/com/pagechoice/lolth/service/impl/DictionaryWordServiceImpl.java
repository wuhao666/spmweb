package com.pagechoice.lolth.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pagechoice.lolth.service.DictionaryWordService;

@Service
@Transactional(rollbackFor = Exception.class)
public class DictionaryWordServiceImpl implements DictionaryWordService {
	
	
	public String getDictionaryList(String json) {
		
		return null;
	}
	
	
	
}
