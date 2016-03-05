package com.pagechoice.lolth.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.springframework.stereotype.Repository;

import com.pagechoice.lolth.dao.DictionaryWordDao;
import com.pagechoice.lolth.entity.DictionaryWord;
import com.pagechoice.lolth.entity.DictionaryWordDetail;
import com.pagechoice.sys.dbutils.GlobalComponents;

@Repository("dictionaryWordDao")
public class DictionaryWordDaoImpl implements DictionaryWordDao {
	
	/**
	 * 获取字典page页
	 * 
	 */
	public Map<String, Object> selectDictionaryWordPage(Map<String, Object> searchMap, Map<String, Object> pageMap) {
 
		String pageSize = String.valueOf(pageMap.get("pageSize")) ; //每页显示条数
		String recordCount = "";// String.valueOf(pageMap.get("recordCount"));//总共的页数
		String currentPage = String.valueOf(pageMap.get("currentPage")) ;//当前页面
		
		StringBuilder recordsSql = new StringBuilder();
		recordsSql.append("select count(a.dwId)  from dictionary_word a ");
		recordsSql.append(" where 1= 1");
		if(searchMap.get("name") != null){
			recordsSql.append(" and a.name like '%" + String.valueOf(searchMap.get("name")) + "%'");
		}
		try {
			Object obj = null;
			obj = GlobalComponents.spmDB.getRunner().query(recordsSql.toString(), new ScalarHandler<Object>());
			recordCount = String.valueOf(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("pageSize", pageSize);
		param.put("recordCount", recordCount);
		param.put("currentPage", currentPage);
		
		return param;
	}
	
	/**
	 * 获取dictionary列表
	 */
	public List<DictionaryWord> selectDictionaryWordList(Map<String, Object> searchMap, Map<String, Object> pageMap) {

		String pageSize = String.valueOf(pageMap.get("pageSize")) ; //每页显示条数
		//String recordCount = "";// String.valueOf(pageMap.get("recordCount"));//总共的页数
		String currentPage = String.valueOf(pageMap.get("currentPage")) ;//当前页面
		
		String limit1 = String.valueOf((Integer.valueOf(currentPage) - 1) * Integer.valueOf(pageSize));
		String limit2 = pageSize;
		
		StringBuilder taskSql = new StringBuilder();
		taskSql.append("select a.* from dictionary_word a");
		taskSql.append(" where 1= 1");
		if(searchMap.get("name") != null){
			taskSql.append(" and a.name like '%" + String.valueOf(searchMap.get("name")) + "%'");
		}
		taskSql.append(" limit " + limit1 +" , " + limit2 );
		List<DictionaryWord> dictWordkList = new ArrayList<DictionaryWord>();

		try {
			dictWordkList = GlobalComponents.spmDB.getRunner().query(taskSql.toString(), new BeanListHandler<DictionaryWord>(DictionaryWord.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return dictWordkList;
	}

	public List<DictionaryWordDetail> selectDictionaryWordDetailList(Map<String, Object> searchMap, Map<String, Object> pageMap) {
		String pageSize = String.valueOf(pageMap.get("pageSize")) ; //每页显示条数
		//String recordCount = "";// String.valueOf(pageMap.get("recordCount"));//总共的页数
		String currentPage = String.valueOf(pageMap.get("currentPage")) ;//当前页面
		
		String limit1 = String.valueOf((Integer.valueOf(currentPage) - 1) * Integer.valueOf(pageSize));
		String limit2 = pageSize;
		
		StringBuilder taskSql = new StringBuilder();
		taskSql.append("select a.* from dictionary_word_detail a");
		taskSql.append(" where 1= 1");
		if(searchMap.get("dwId") != null){
			taskSql.append(" and a.dwId = '" + String.valueOf(searchMap.get("dwId")) + "'");
		}
		if(searchMap.get("name") != null){
			taskSql.append(" and a.name like '%" + String.valueOf(searchMap.get("name")) + "%'");
		}
		taskSql.append(" limit " + limit1 +" , " + limit2 );
		List<DictionaryWordDetail> dictWordkDetailList = new ArrayList<DictionaryWordDetail>();

		try {
			dictWordkDetailList = GlobalComponents.spmDB.getRunner().query(taskSql.toString(), new BeanListHandler<DictionaryWordDetail>(DictionaryWordDetail.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return dictWordkDetailList;
	}

	public Map<String, Object> selectDictionaryWordDetailPage(Map<String, Object> searchMap, Map<String, Object> pageMap) {

		String pageSize = String.valueOf(pageMap.get("pageSize")) ; //每页显示条数
		String recordCount = "";// String.valueOf(pageMap.get("recordCount"));//总共的页数
		String currentPage = String.valueOf(pageMap.get("currentPage")) ;//当前页面
		
		StringBuilder recordsSql = new StringBuilder();
		recordsSql.append("select count(a.dwId)  from dictionary_word_detail a ");
		recordsSql.append(" where 1= 1");
		if(searchMap.get("dwId") != null){
			recordsSql.append(" and a.dwid = '" + String.valueOf(searchMap.get("dwId")) + "'");
		}
		if(searchMap.get("name") != null){
			recordsSql.append(" and a.name like '%" + String.valueOf(searchMap.get("name")) + "%'");
		}
		
		try {
			Object obj = null;
			obj = GlobalComponents.spmDB.getRunner().query(recordsSql.toString(), new ScalarHandler<Object>());
			recordCount = String.valueOf(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("pageSize", pageSize);
		param.put("recordCount", recordCount);
		param.put("currentPage", currentPage);
		
		return param;
	}
	
	/**
	 * 获取词典信息 
	
	
	public DictionaryWord selectDictionaryWordToMap(Map<String,String> map) {
		
		if( map.get("dwId") == null){
			return null;
		}
		String dwId = map.get("dwId").toString();
		StringBuilder sqlBuilder = new StringBuilder();
		sqlBuilder.append(" select * from dictionary_word ");
		sqlBuilder.append(" where dwId = '" + dwId + "' ");
		
		List<DictionaryWord> list = new ArrayList<DictionaryWord>();
		
		try {
			
			list = GlobalComponents.spmDB.getRunner().query(sqlBuilder.toString(), new BeanListHandler<DictionaryWord>(DictionaryWord.class));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if( list.size() == 1){
			return list.get(0);
		}
		
		return null;
	}

	public List<DictionaryWordDetail> selectDictionaryWordDetail(Map<String,String> map){
		if( map.get("dictId") == null){
			return null;
		}
		String dwId = map.get("dwId").toString();
		StringBuilder sqlBuilder = new StringBuilder();
		sqlBuilder.append(" select * from dictionary_word_detail ");
		sqlBuilder.append(" where dwId = '" + dwId + "' ");
		if(map.get("detailId") != null && !"".equals(map.get("detailId"))){
			sqlBuilder.append(" and id in (");
			String[] detailIds = map.get("detailId").split(",");
			for(int i = 0 ; i < detailIds.length ;i++){
				sqlBuilder.append(detailIds[i] + ",");
			}
			sqlBuilder.deleteCharAt(sqlBuilder.length() - 1);
			sqlBuilder.append(" )");
		}
		
		List<DictionaryWordDetail> list = new ArrayList<DictionaryWordDetail>();
		
		try {
			
			list = GlobalComponents.spmDB.getRunner().query(sqlBuilder.toString(), new BeanListHandler<DictionaryWordDetail>(DictionaryWordDetail.class));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(list.size() > 0){
			return list;
		}
		
		return null;
	}
	 */
	
}
