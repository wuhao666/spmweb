package com.pagechoice.lolth.dao;

import java.util.List;
import java.util.Map;

public interface WebSpiderDao {
	
	int selectCountByTask(Map<String,String> map);
	
	List<Map<String,Object>> selectWebSpiderData(Map<String,String> map);
	
}
