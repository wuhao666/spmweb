package com.pagechoice.lolth.dao.impl;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.springframework.stereotype.Repository;

import com.pagechoice.lolth.dao.LolthTaskMonitorDao;
import com.pagechoice.lolth.entity.LolthTaskMonitor;
import com.pagechoice.sys.dbutils.GlobalComponents;

@Repository("lolthTaskMonitorDao")
public class LolthTaskMonitorDaoImpl implements LolthTaskMonitorDao {

	public List<LolthTaskMonitor> selectNotCompleteLolthTask() {
		
		StringBuilder sql = new StringBuilder();
		sql.append("select a.* from lolth_task_monitor a");
		sql.append(" where 1 = 1");
		sql.append(" and a.isComplete = '0' ");
		
		List<LolthTaskMonitor> lolthTaskMonitorList = new ArrayList<LolthTaskMonitor>();
		try {
			lolthTaskMonitorList = GlobalComponents.spmDB.getRunner().query(sql.toString(), new BeanListHandler<LolthTaskMonitor>(LolthTaskMonitor.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if(lolthTaskMonitorList.size() > 0){
			return lolthTaskMonitorList;
		}
		
		return null;
	}
	
	
	public String infoLolthTaskMonitor(LolthTaskMonitor lolthTaskMonitor) {
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into lolth_task_monitor ");
		sql.append(" (taskId,taskName,attribute1,isComplete,`update`) values");
		sql.append( " ( ? , ? , ? , ? , ? ) ");
		
		ArrayList<Object> params = new ArrayList<Object>();
		params.add(lolthTaskMonitor.getTaskId());
		params.add(lolthTaskMonitor.getTaskName());
		params.add(lolthTaskMonitor.getAttribute1());
		params.add(lolthTaskMonitor.getIsComplete());
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String nowTime = df.format(new Date());
		params.add(nowTime);
		
		int result = 0;
		try {
			 GlobalComponents.spmDB.getRunner().insert(sql.toString(), new ScalarHandler<Integer>(),params.toArray());
			 result =1;
		} catch (SQLException e) {
			e.printStackTrace();
			result = 0;
		}
		
		return String.valueOf(result);
	}


	public List<Map<String, Object>> selectLolthTaskCount(String projectNames) {
		
		StringBuilder sql = new StringBuilder();
		sql.append("select a.projectName as `taskName`,count(a.projectName) as `count` from lakenono_lolth_task a ");
		sql.append(" where 1 = 1 ");
		sql.append(" and a.projectName in (" + projectNames + ") ");
		sql.append(" and a.`status` = 'todo' ");
		sql.append(" group by a.projectName ");
		
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		 try {
			 list = GlobalComponents.lolthxdb.getRunner().query(sql.toString(),  new MapListHandler());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}


	public String updateLolthTaskMonitorAttr1(LolthTaskMonitor lolthTaskMonitor) {
		
		StringBuilder sql = new StringBuilder();
		sql.append("update lolth_task_monitor a set a.attribute1 = ? , ");
		sql.append("a.attribute2 = null ,a.attribute3 = null ");
		sql.append("where a.taskId = ? ");
		
		ArrayList<Object> params = new ArrayList<Object>();
		params.add(lolthTaskMonitor.getAttribute1());
		params.add(lolthTaskMonitor.getTaskId());
		
		int result = 0;
		try {
			GlobalComponents.spmDB.getRunner().update(sql.toString(), params.toArray());
			result = 1;
		} catch (SQLException e) {
			e.printStackTrace();
			result = 0;
		}
		
		return String.valueOf(result);
	}


	public String updateLolthTaskMonitorAttr2(LolthTaskMonitor lolthTaskMonitor) {
		StringBuilder sql = new StringBuilder();
		sql.append("update lolth_task_monitor a set a.attribute2 = ?  ");
		sql.append("where a.taskId = ? ");
		
		ArrayList<Object> params = new ArrayList<Object>();
		params.add(lolthTaskMonitor.getAttribute1());
		params.add(lolthTaskMonitor.getTaskId());
		
		int result = 0;
		try {
			GlobalComponents.spmDB.getRunner().update(sql.toString(), params.toArray());
			result = 1;
		} catch (SQLException e) {
			e.printStackTrace();
			result = 0;
		}
		
		return String.valueOf(result);
	}


	public String updateLolthTaskMonitorAttr3(LolthTaskMonitor lolthTaskMonitor) {
		StringBuilder sql = new StringBuilder();
		sql.append("update lolth_task_monitor a set a.attribute3 = ? , a.isComplete = '1' ");
		sql.append("where a.taskId = ? ");
		
		ArrayList<Object> params = new ArrayList<Object>();
		params.add(lolthTaskMonitor.getAttribute1());
		params.add(lolthTaskMonitor.getTaskId());
		
		int result = 0;
		try {
			GlobalComponents.spmDB.getRunner().update(sql.toString(), params.toArray());
			result = 1;
		} catch (SQLException e) {
			e.printStackTrace();
			result = 0;
		}
		
		return String.valueOf(result);
	}
	
	
	
	
	
}
