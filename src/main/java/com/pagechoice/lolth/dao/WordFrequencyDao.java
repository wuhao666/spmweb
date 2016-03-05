package com.pagechoice.lolth.dao;

import java.util.List;
import java.util.Map;

import com.pagechoice.lolth.entity.WordFrequency;

public interface WordFrequencyDao {
	
	String saveWordFrequency(WordFrequency wfBean);
	
	List<WordFrequency> selectWordFrequencyToList(Map<String,String> map);
	
}
