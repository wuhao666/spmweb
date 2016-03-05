package com.pagechoice.lolth.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.springframework.stereotype.Repository;

import com.pagechoice.lolth.dao.LolthTaskDao;
import com.pagechoice.lolth.entity.LolthTask;
import com.pagechoice.lolth.entity.LolthTaskDetail;
import com.pagechoice.sys.dbutils.GlobalComponents;

@Repository("lolthTaskDao")
public class LolthTaskDaoImpl implements LolthTaskDao {

	/**
	 * @reason
	 */
	public String saveLolthTask(LolthTask task) {
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into lolth_task ");
		sql.append(" ( taskId,taskName,taskType,data,webId,startDate,endDate,webMath,remark,detailType,createDate,createUser ) values");
		sql.append(" ( ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ?  ) ");

		ArrayList<Object> params = new ArrayList<Object>();
		params.add(task.getTaskId());
		params.add(task.getTaskName());
		params.add(task.getTaskType());
		params.add(task.getData());
		params.add(task.getWebId());
		params.add(task.getStartDate());
		params.add(task.getEndDate());
		params.add(task.getWebMath());
		params.add(task.getRemark());
		params.add(task.getDetailType());
		params.add(task.getCreateDate());
		params.add(task.getCreateUser());

		int result = 0;
		try {
			GlobalComponents.spmDB.getRunner().insert(sql.toString(), new ScalarHandler<Object>(), params.toArray());
			result = 1;
		} catch (SQLException e) {
			e.printStackTrace();
			result = 0;
		}
		return String.valueOf(result);
	}

	public String updateLolthTask() {
		return null;
	}

	public Map<String,Object> selectLolTaskPageDetail(Map<String,Object> searchMap,Map<String,Object> pageMap){
		
       
		String pageSize = String.valueOf(pageMap.get("pageSize")) ; //每页显示条数
		String recordCount = "";// String.valueOf(pageMap.get("recordCount"));//总共的页数
		String currentPage = String.valueOf(pageMap.get("currentPage")) ;//当前页面
		
		//String limit1 = String.valueOf((Integer.valueOf(currentPage) - 1) * Integer.valueOf(pageSize));
		//String limit2 = pageSize;
		
		StringBuilder recordsSql = new StringBuilder();
		recordsSql.append("select count(a.taskId)  from lolth_task a ");
		recordsSql.append(" where 1= 1");
		if(searchMap.get("name") != null){
			recordsSql.append(" and taskId like '%" + String.valueOf(searchMap.get("name")) + "%'");
			recordsSql.append(" and taskName like '%" + String.valueOf(searchMap.get("name")) + "%'");
		}
		if(searchMap.get("startDate") != null){
			recordsSql.append(" and DATE(createDate) like '%" + String.valueOf(searchMap.get("startDate")) + "%'");
		}
		if(searchMap.get("endDate") != null){
			recordsSql.append(" and DATE(createDate) like '%" + String.valueOf(searchMap.get("endDate")) + "%'");
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
	
	
	public List<LolthTask> selectLolthTask(Map<String,Object> searchMap,Map<String,Object> pageMap) {
		
		String pageSize = String.valueOf(pageMap.get("pageSize")) ; //每页显示条数
		//String recordCount = "";// String.valueOf(pageMap.get("recordCount"));//总共的页数
		String currentPage = String.valueOf(pageMap.get("currentPage")) ;//当前页面
		
		String limit1 = String.valueOf((Integer.valueOf(currentPage) - 1) * Integer.valueOf(pageSize));
		String limit2 = pageSize;
		
		StringBuilder taskSql = new StringBuilder();
		taskSql.append("select a.*,b.webName,b.webModule from lolth_task a 	left join web_contrast b on a.webId = b.webId ");
		taskSql.append(" where 1= 1");
		if(searchMap.get("name") != null){
			taskSql.append(" and a.taskId like '%" + String.valueOf(searchMap.get("name")) + "%'");
			taskSql.append(" and a.taskName like '%" + String.valueOf(searchMap.get("name")) + "%'");
		}
		if(searchMap.get("startDate") != null){
			taskSql.append(" and DATE(a.createDate) like '%" + String.valueOf(searchMap.get("startDate")) + "%'");
		}
		if(searchMap.get("endDate") != null){
			taskSql.append(" and DATE(a.createDate) like '%" + String.valueOf(searchMap.get("endDate")) + "%'");
		}
		taskSql.append(" limit " + limit1 +" , " + limit2 );
		List<LolthTask> lolthTaskList = new ArrayList<LolthTask>();

		try {
			lolthTaskList = GlobalComponents.spmDB.getRunner().query(taskSql.toString(), new BeanListHandler<LolthTask>(LolthTask.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}

		String taskDetailSql = "select * from lolth_task_detail where taskId = ? ";
		List<LolthTaskDetail> lolthTaskDetailList = null;

		List<LolthTask> reLolthTask = new ArrayList<LolthTask>();
		for (int i = 0; i < lolthTaskList.size(); i++) {
			LolthTask lolthTask = lolthTaskList.get(i);
			String taskId = lolthTask.getTaskId();
			lolthTaskDetailList = new ArrayList<LolthTaskDetail>();
			ArrayList<Object> params = new ArrayList<Object>();
			params.add(taskId);
			try {
				lolthTaskDetailList = GlobalComponents.spmDB.getRunner().query(taskDetailSql, new BeanListHandler<LolthTaskDetail>(LolthTaskDetail.class), params.toArray());
				lolthTask.setLolthTaskDetail(lolthTaskDetailList);
			} catch (SQLException e) {
				e.printStackTrace();
				continue;
			}
			// 获取爬取任务状态
			int tanums = 0;
			String success = "0";
			String taskNums = "";

			try {
				StringBuilder taskNumsSql = new StringBuilder();
				taskNumsSql.append("select count(a.`status`) as nums,a.`status` as status from lakenono_lolth_task a ");
				taskNumsSql.append("where a.projectName = '" + lolthTask.getTaskName() + "'");
				taskNumsSql.append("group by status ");
				List<Map<String, Object>> taskNumsList = GlobalComponents.lolthxdb.getRunner().query(taskNumsSql.toString(), new MapListHandler());
				if (taskNumsList != null) {
					for (int tni = 0; tni < taskNumsList.size(); tni++) {
						tanums = tanums + Integer.valueOf(String.valueOf(taskNumsList.get(tni).get("nums")));
						if ("success".equals(String.valueOf(taskNumsList.get(tni).get("status")))) {
							success = String.valueOf(taskNumsList.get(tni).get("nums"));
						}
					}
					taskNums = success + "/" + tanums;
				}
			} catch (Exception e) {
				e.printStackTrace();
				continue;
			}
			lolthTask.setTaskNums(taskNums);

			// 获取爬取任务量
			int dataNums = 0;
			if (lolthTaskDetailList != null) {
				for (int dni = 0; dni < lolthTaskDetailList.size(); dni++) {
					LolthTaskDetail lolthTaskDetail = lolthTaskDetailList.get(dni);
					if (lolthTaskDetail.getTableName() != null && "".equals(lolthTaskDetail.getTableName())) {
						String tableName = lolthTaskDetail.getTableName();
						StringBuilder dataNumsSql = new StringBuilder();
						dataNumsSql.append("select count(a.id)  from ");
						dataNumsSql.append(tableName);
						try {
							Object dataNumsObj = null;
							dataNumsObj = GlobalComponents.lolthxdb.getRunner().query(dataNumsSql.toString(), new ScalarHandler<Object>());
							dataNums = dataNums + Integer.valueOf(String.valueOf(dataNumsObj));
						} catch (Exception e) {
							e.printStackTrace();
							continue;
						}
					}
				}
			}
			
			lolthTask.setDataNums(String.valueOf(dataNums));
			reLolthTask.add(lolthTask);
		}
		return reLolthTask;
	}

	public LolthTask selectLolthTaskSingle(Map<String, String> map) {
		
		StringBuilder taskSql = new StringBuilder();
		taskSql.append("select a.*,b.webName,b.webModule from lolth_task a 	left join web_contrast b on a.webId = b.webId ");
		taskSql.append(" where 1= 1");
		taskSql.append(" and a.taskId = '" + map.get("taskId") + "'");
		
		List<LolthTask> lolthTaskList = new ArrayList<LolthTask>();

		try {
			lolthTaskList = GlobalComponents.spmDB.getRunner().query(taskSql.toString(), new BeanListHandler<LolthTask>(LolthTask.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}

		String taskDetailSql = "select * from lolth_task_detail where taskId = ? and classBean = ? ";
		List<LolthTaskDetail> lolthTaskDetailList = null;

		LolthTask lolthTask = new LolthTask();
		if ( lolthTaskList.size() == 1) {
			lolthTask = lolthTaskList.get(0);
			lolthTaskDetailList = new ArrayList<LolthTaskDetail>();
			ArrayList<Object> params = new ArrayList<Object>();
			params.add(map.get("taskId"));
			params.add(map.get("classBean"));
			try {
				lolthTaskDetailList = GlobalComponents.spmDB.getRunner().query(taskDetailSql, new BeanListHandler<LolthTaskDetail>(LolthTaskDetail.class), params.toArray());
				lolthTask.setLolthTaskDetail(lolthTaskDetailList);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			// 获取爬取任务状态
			int tanums = 0;
			String success = "0";
			String taskNums = "";

			try {
				StringBuilder taskNumsSql = new StringBuilder();
				taskNumsSql.append("select count(a.`status`) as nums,a.`status` as status from lakenono_lolth_task a ");
				taskNumsSql.append("where a.projectName = '" + lolthTask.getTaskName() + "'");
				taskNumsSql.append("group by status ");
				List<Map<String, Object>> taskNumsList = GlobalComponents.lolthxdb.getRunner().query(taskNumsSql.toString(), new MapListHandler());
				if (taskNumsList != null) {
					for (int tni = 0; tni < taskNumsList.size(); tni++) {
						tanums = tanums + Integer.valueOf(String.valueOf(taskNumsList.get(tni).get("nums")));
						if ("success".equals(String.valueOf(taskNumsList.get(tni).get("status")))) {
							success = String.valueOf(taskNumsList.get(tni).get("nums"));
						}
					}
					taskNums = success + "/" + tanums;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			lolthTask.setTaskNums(taskNums);

			// 获取爬取任务量
			int dataNums = 0;
			if (lolthTaskDetailList != null) {
				for (int dni = 0; dni < lolthTaskDetailList.size(); dni++) {
					LolthTaskDetail lolthTaskDetail = lolthTaskDetailList.get(dni);
					if (lolthTaskDetail.getTableName() != null && "".equals(lolthTaskDetail.getTableName())) {
						String tableName = lolthTaskDetail.getTableName();
						StringBuilder dataNumsSql = new StringBuilder();
						dataNumsSql.append("select count(a.id)  from ");
						dataNumsSql.append(tableName);
						try {
							Object dataNumsObj = null;
							dataNumsObj = GlobalComponents.lolthxdb.getRunner().query(dataNumsSql.toString(), new ScalarHandler<Object>());
							dataNums = dataNums + Integer.valueOf(String.valueOf(dataNumsObj));
						} catch (Exception e) {
							e.printStackTrace();
							continue;
						}
					}
				}
			}
			
			lolthTask.setDataNums(String.valueOf(dataNums));
		}
		return lolthTask;
	}
	
	
	
}
