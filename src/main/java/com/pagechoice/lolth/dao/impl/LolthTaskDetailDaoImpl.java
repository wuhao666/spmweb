package com.pagechoice.lolth.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.springframework.stereotype.Repository;

import com.pagechoice.lolth.dao.LolthTaskDetailDao;
import com.pagechoice.lolth.entity.LolthTaskDetail;
import com.pagechoice.sys.dbutils.GlobalComponents;

@Repository("lolthTaskDetailDao")
public class LolthTaskDetailDaoImpl implements LolthTaskDetailDao{
	
	/**
	 * 
	 */
	public String saveLolthTaskDetail(LolthTaskDetail task) {
		
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into lolth_task_detail ");
		sql.append(" ( taskId,tableName,classBean,downloadUrl,main,order,remark ) values");
		sql.append( " ( ? , ? , ? , ? , ? , ? , ?  ) ");
		
		ArrayList<Object> params = new ArrayList<Object>();
		params.add(task.getTaskId());
		params.add(task.getTableName());
		params.add(task.getClassBean());
		params.add(task.getDownloadUrl());
		params.add(task.getMain());
		params.add(task.getOrder());
		params.add(task.getRemark());
		
		int result = 0;
		try {
			 result = GlobalComponents.spmDB.getRunner().insert(sql.toString(), new ScalarHandler<Integer>(),params.toArray());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return String.valueOf(result);
	}
	
	//批量插入 lolthDetail 
	public String saveLolthTaskDetailByList(List<LolthTaskDetail> list) {
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into lolth_task_detail ");
		sql.append(" ( taskId,tableName,tableNameCn,classBean,main,`order`,remark ) values ");
		ArrayList<Object> params = new ArrayList<Object>();
		int result = 0;
		if(list.size() > 0){
			for( int i = 0 ; i  < list.size() ; i++ ){
				LolthTaskDetail task = list.get(i);
				sql.append(" (  ? , ? , ? , ? , ? , ? , ?  ),");
				params.add(task.getTaskId());
				params.add(task.getTableName());
				params.add(task.getTableNameCn());
				params.add(task.getClassBean());
				params.add(task.getMain());
				params.add(task.getOrder());
				params.add(task.getRemark());
			}
			sql.deleteCharAt(sql.length() - 1);
			try {
				 GlobalComponents.spmDB.getRunner().update(sql.toString(), params.toArray());
				 result = 1;
			} catch (SQLException e) {
				e.printStackTrace();
				result = 0;
			}
		}
	 
		return String.valueOf(result);
	}
	
	
	public String updateLolthTaskDetail() {
		
		
		return null;
	}

	public List<LolthTaskDetail> selectLolthTaskDetailByTaskId(String taskId) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from lolth_task_detail ");
		sql.append(" where 1=1 ");
		sql.append(" and taskId = '" + taskId + "'");
		
		List<LolthTaskDetail> list = new ArrayList<LolthTaskDetail>();
		try {
			list = GlobalComponents.spmDB.getRunner().query(sql.toString(), new BeanListHandler<LolthTaskDetail>(LolthTaskDetail.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(list != null){
			return list;
		}
		return null;
	}

	public String updateLolthTaskDetailDownloadUrl(String id, String downloadUrl) {
		
		StringBuilder sql = new StringBuilder();
		sql.append(" update lolth_task_detail set downloadUrl = '" + downloadUrl + "'");
		sql.append(" where id = " + id);
		
		int result = 0;
		try {
			GlobalComponents.spmDB.getRunner().update(sql.toString());
			result = 1;
		} catch (SQLException e) {
			e.printStackTrace();
			result = 0;
		}
		
		return String.valueOf(result);
	}
	
	
}
