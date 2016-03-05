package com.pagechoice.lolth.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.springframework.stereotype.Repository;

import com.pagechoice.lolth.dao.LolthForumDao;
import com.pagechoice.lolth.entity.WebForumData;
import com.pagechoice.sys.dbutils.GlobalComponents;

@Repository("lolthForumDao")
public class LolthForumDaoImpl implements LolthForumDao {

	public List<WebForumData> selectWebForumDataList(Map<String,Object> searchMap,Map<String,Object> pageMap) {
		String pageSize = String.valueOf(pageMap.get("pageSize")) ; //每页显示条数
		//String recordCount = map.get("recordCount");//总共的页数
		String currentPage = String.valueOf(pageMap.get("currentPage"));//当前页面
		
		String limit1 = String.valueOf((Integer.valueOf(currentPage) - 1) * Integer.valueOf(pageSize));
		String limit2 = pageSize;
		
		StringBuilder forumSql = new StringBuilder();
		forumSql.append(" select a.* from web_forum_data a ");
		forumSql.append(" where 1 = 1");
		if(searchMap.get("wedId") != null ){
			forumSql.append(" and wedId  = '" + String.valueOf(searchMap.get("wedId")) + "'");
		}
		forumSql.append(" limit " + limit1 +" , " + limit2 );
		List<WebForumData> forumList = new ArrayList<WebForumData>();

		try {
			forumList = GlobalComponents.spmDB.getRunner().query(forumSql.toString(), new BeanListHandler<WebForumData>(WebForumData.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return forumList;
	}

	public Map<String, Object> selectWebForumDataPage(Map<String,Object> searchMap,Map<String,Object> pageMap) {
		String pageSize = String.valueOf(pageMap.get("pageSize")) ; //每页显示条数
		String recordCount = "";
		String currentPage = String.valueOf(pageMap.get("currentPage")) ;//当前页面
		
		StringBuilder recordsSql = new StringBuilder();
		recordsSql.append(" select count(a.id)  from web_forum_data a ");
		recordsSql.append(" where 1= 1");
		if(searchMap.get("wedId") != null ){
			recordsSql.append(" and wedId  = '" + String.valueOf(searchMap.get("wedId")) + "'");
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
	
	//插入数据
	public String insertWebForumDataPage(WebForumData data) {
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into web_forum_data ");
		sql.append(" ( webId,forumId,keyword,createUser,createDate,isDelete ) values");
		sql.append(" (  ?,	?,	?,	?,	?,	? ) ");

		ArrayList<Object> params = new ArrayList<Object>();
		//params.add(task.getTaskId());
		params.add(data.getWebId());
		params.add(data.getForumId());
		params.add(data.getKeyword());
		params.add(data.getCreateUser());
		params.add(data.getCreateDate());
		params.add("0");

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

	public WebForumData selectWebForumData(Map<String, String> map) {
		StringBuilder forumSql = new StringBuilder();
		forumSql.append(" select a.* from web_forum_data a ");
		forumSql.append(" where 1 = 1");
		
		if(map.get("forumId") != null && !"".equals(map.get("forumId"))){
			forumSql.append(" and forumId = '" + map.get("forumId") +"' ");
		}
		
		List<WebForumData> forumList = new ArrayList<WebForumData>();

		try {
			forumList = GlobalComponents.spmDB.getRunner().query(forumSql.toString(), new BeanListHandler<WebForumData>(WebForumData.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(forumList.size() == 1){
			return forumList.get(0);
		}
		
		return null;
	}
	
	
}
