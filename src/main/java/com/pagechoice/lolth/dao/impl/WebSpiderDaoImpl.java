package com.pagechoice.lolth.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.springframework.stereotype.Repository;

import com.pagechoice.lolth.dao.WebSpiderDao;
import com.pagechoice.sys.dbutils.GlobalComponents;

@Repository("webSpiderDao")
public class WebSpiderDaoImpl implements WebSpiderDao {

	
	public int selectCountByTask(Map<String, String> map) {
		StringBuilder sqlBuilder = new StringBuilder();
		sqlBuilder.append(" select count(id) from  "); 
		String tableName = "";
		if( map.get("tableName") == null){
			return 0;
		}
		tableName = map.get("tableName");
		sqlBuilder.append( tableName );
		
		Object obj = null;
		try {
			obj = GlobalComponents.lolthxdb.getRunner().query(sqlBuilder.toString(), new ScalarHandler<Object>());
		} catch (SQLException e) {
			e.printStackTrace();
		}  
		int i=Integer.valueOf(obj.toString());  
		
		return i;
	}
	
	
	public List<Map<String, Object>> selectWebSpiderData(Map<String, String> map) {
		StringBuilder sqlBuilder = new StringBuilder();
		sqlBuilder.append(" select * from ");
		String tableName = "";
		if( map.get("tableName") == null){
			return null;
		}
		tableName = map.get("tableName");
		sqlBuilder.append( tableName );
		String limit1 = "0";
		if( map.get("limit1") == null ){
			System.out.println(" 未获取分页起始数据 ！ ");
		}else{
			limit1 = map.get("limit1").toString();
			sqlBuilder.append(" limit " + limit1 + " , 1000" );
		}
		
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		try {
			list = GlobalComponents.lolthxdb.getRunner().query(sqlBuilder.toString(), new MapListHandler() );
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
}
