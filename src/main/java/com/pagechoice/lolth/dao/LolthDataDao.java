package com.pagechoice.lolth.dao;

import java.util.List;
import java.util.Map;

public interface LolthDataDao {
	
	int selectTableNameCount(String tableName);
	
	String exportDataByTableName(String tableName);
	
	List<Map<String, Object>> selectDataLimit(String tableName , String limit1);
	
}
