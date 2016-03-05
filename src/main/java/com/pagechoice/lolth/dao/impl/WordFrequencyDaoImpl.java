package com.pagechoice.lolth.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.pagechoice.lolth.dao.WordFrequencyDao;
import com.pagechoice.lolth.entity.WordFrequency;
import com.pagechoice.sys.dbutils.GlobalComponents;

public class WordFrequencyDaoImpl implements WordFrequencyDao {

	
	/**
	 * 保存正负面词汇
	 */
	public String saveWordFrequency(WordFrequency wfBean) {
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into word_frequency ");
		sql.append(" (taskId,tableName,typeName,word,frequency) ");
		sql.append("  values( ? , ? , ? , ? , ?) ");
		
		ArrayList<Object> params = new ArrayList<Object>();
		params.add(wfBean.getTaskId());
		params.add(wfBean.getTableName());
		params.add(wfBean.getTypeName());
		params.add(wfBean.getWord());
		params.add(wfBean.getFrequency());
		
		int result = 0 ;
		try {
			GlobalComponents.spmDB.getRunner().insert(sql.toString(), new ScalarHandler<Object>(),params.toArray());
			result = 1;
		} catch (SQLException e) {
			e.printStackTrace();
			result = 0;
		}
		return String.valueOf(result);
	}
	
	
	/**
	 * 获取正负面词汇列表
	 */
	public List<WordFrequency> selectWordFrequencyToList(Map<String, String> map) {
		StringBuilder sqlBuilder = new StringBuilder();
		sqlBuilder.append(" select * from word_frequency ");
		
		String taskId = map.get("taskId").toString();
		String tableName = map.get("tableName").toString();
		if( taskId == null || tableName == null){
			return null;
		}
		sqlBuilder.append(" where 1 = 1 ");
		sqlBuilder.append(" and  taskId = '" + taskId + "'");
		sqlBuilder.append(" and  tableName = '" + tableName + "'");
		
		List<WordFrequency> list = new ArrayList<WordFrequency>();
		try {
			
			list = GlobalComponents.spmDB.getRunner().query(sqlBuilder.toString(), new BeanListHandler<WordFrequency>(WordFrequency.class));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}

	
	
	
}
