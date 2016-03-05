package com.pagechoice.lolth.dao.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.pagechoice.lolth.dao.LolthDataDao;
import com.pagechoice.sys.dbutils.GlobalComponents;

@Repository("lolthDataDao")
public class LolthDataDaoImpl implements LolthDataDao {

	public int selectTableNameCount(String tableName) {
		StringBuilder recordsSql = new StringBuilder();
		recordsSql.append("select count(*)  from ");
		recordsSql.append(tableName);

		int recordCount = 0;
		try {
			Object obj = null;
			obj = GlobalComponents.lolthxdb.getRunner().query(recordsSql.toString(), new ScalarHandler<Object>());
			recordCount = Integer.valueOf(String.valueOf(obj));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return recordCount;

	}

	public String exportDataByTableName(String tableName) {
		boolean isSucess = false;
		int count = this.selectTableNameCount(tableName);
		
		HttpServletRequest request =  ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest(); 
		String loadUrl = "download" + File.separator + tableName + ".csv";
		String downloadUrl = request.getServletContext().getRealPath("/") + loadUrl ;	
		
		File file = new File(downloadUrl);
		
		long starTime=System.currentTimeMillis();
		System.out.println("开始时间" + starTime);
		
		FileOutputStream out = null;
		OutputStreamWriter osw = null;
		BufferedWriter bw = null;
		try {
			out = new FileOutputStream(file);
			osw = new OutputStreamWriter(out, "gbk");
			bw = new BufferedWriter(osw);	
			for (int limit1 = 0; limit1 < count; limit1 = limit1 + 10000) {
				//List<String> dataList = new ArrayList<String>();
				List<Map<String, Object>> list = this.selectDataLimit(tableName, String.valueOf(limit1));
				if (list != null) {
					for (int i = 0; i < list.size(); i++) {
						StringBuffer str = new StringBuffer();
						Map<String, Object> map = list.get(i);
						Set<String> keys = map.keySet();
						for (String key : keys) {
							str.append("\"" + String.valueOf(map.get(key)) + "\"");
							str.append(",");
						}
						bw.append(str.toString()).append("\r");
					}
				}
			}
			isSucess = true;
		} catch (Exception e) {
			System.out.println(" >>>>>>>>>>>  Exception : " + e );
			isSucess = false;
		} finally {
			if (bw != null) {
				try {
					bw.close();
					bw = null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (osw != null) {
				try {
					osw.close();
					osw = null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (out != null) {
				try {
					out.close();
					out = null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		long endTime=System.currentTimeMillis();
		System.out.println("结束时间" + endTime);
		long Time=endTime-starTime;
		System.out.println("总耗时" + Time);

		/**
		 * try { FileWriter fw = new FileWriter("E:\\text.csv"); for (int limit1
		 * = 0; limit1 < count ; limit1 = limit1 + 10000) { List<Map<String,
		 * Object>> list = this.selectDataLimit(tableName,
		 * String.valueOf(limit1)); StringBuffer str = new StringBuffer();
		 * if(list != null ){ for(int i = 0; i < list.size() ; i++){
		 * Map<String,Object> map = list.get(i); Set<String> keys =
		 * map.keySet(); for(String key :keys){ str.append(map.get(key));
		 * str.append(","); } str.append("\r\n"); } } fw.write(str.toString());
		 * fw.flush(); } fw.close(); } catch (IOException e) {
		 * e.printStackTrace(); }
		 */
		
		System.out.println(" >>>>>> " + isSucess);
		
		if(isSucess == true){
			return downloadUrl;
		}
		
		return null;
	}

	public List<Map<String, Object>> selectDataLimit(String tableName, String limit1) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from ");
		sql.append(tableName);
		sql.append(" limit " + limit1 + ", 10000");

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			list = GlobalComponents.lolthxdb.getRunner().query(sql.toString(), new MapListHandler());
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	public static void main(String args[]) {
		LolthDataDaoImpl dao = new LolthDataDaoImpl();
		// List list =
		// dao.selectDataLimit("data_autohome_bbs_c20160108023015033322","0");
		String bool = dao.exportDataByTableName("data_autohome_bbs_c20160108023015033322");

		System.out.println(bool);
	}

}
