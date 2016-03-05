package com.pagechoice.lolth.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.springframework.stereotype.Repository;

import com.pagechoice.lolth.dao.WebContrastDao;
import com.pagechoice.lolth.entity.WebContrast;
import com.pagechoice.sys.dbutils.GlobalComponents;

@Repository("webContrastDao")
public class WebContrastDaoImpl implements WebContrastDao {

	public List<Map<String, Object>> selectWebContrastClsAndTable(String webId) {
		StringBuilder sqlBuilder = new StringBuilder();
		sqlBuilder.append(" select a.webId,a.webName,a.webModule,a.webModuleType,a.webClass,");
		sqlBuilder.append(" b.classBean,b.tableName,b.tableNameCN,b.main,b.order ");
		sqlBuilder.append(" from web_contrast a ");
		sqlBuilder.append(" left join web_contrast_detail b on a.webId = b.webId ");
		sqlBuilder.append( " where a.webId = '" + webId + "'" );
		
		List<Map<String, Object>> list = null;
		try {		
			
			list = (List<Map<String, Object>>)GlobalComponents.spmDB.getRunner().query(sqlBuilder.toString(), new MapListHandler());
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	
	/**
	 * @author wuhao
	 * @reason 
	 */
	public List<WebContrast> getWebContrastList(Map<String, String> map) {
		StringBuilder sqlBuilder = new StringBuilder();
		sqlBuilder.append(" select * ");
		sqlBuilder.append(" from web_contrast  ");
		sqlBuilder.append(" where 1=1 ");
		if(map.get("webName") != null && !"".equals(map.get("webName"))){
			 sqlBuilder.append(" and CONCAT(a.webName,'-',a.webModule) like '%" + map.get("webName")  + "%'  ");
		}
		
		List<WebContrast> list = null;
		try {		
			
			list = GlobalComponents.spmDB.getRunner().query(sqlBuilder.toString(), new BeanListHandler<WebContrast>(WebContrast.class));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
}
