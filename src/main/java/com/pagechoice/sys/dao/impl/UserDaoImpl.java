package com.pagechoice.sys.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.springframework.stereotype.Repository;

import com.pagechoice.sys.dao.UserDao;
import com.pagechoice.sys.dbutils.GlobalComponents;
import com.pagechoice.sys.entity.User;

@Repository("userDao")
public class UserDaoImpl implements UserDao {
	
	public String validateUser(String username,String passwd) {
		StringBuilder sqlBuilder = new StringBuilder();
		sqlBuilder.append(" select id,username,password,isdelete from sys_user where 1=1 and username = ?");
		
		List<Map<String, Object>> list = null;
		
		try {	
			
			list = (List<Map<String, Object>>)GlobalComponents.spmDB.getRunner().query(sqlBuilder.toString(),new MapListHandler(),new Object[]{username});

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(list.size() == 1){
			String password = String.valueOf(list.get(0).get("password"));
			if(!password.equals(passwd)){
				return "2";//密码错误
			}else{
				if(list.get(0).get("isdelete") != null && "1".equals(list.get(0).get("isdelete").toString()) ){
					return "-1";
				}else{
					return "1";
				}
			}
		}

		return "0";
	}

	public User selectUser(String username,String passwd){
		String sql = "select * from sys_user where username = '" + username +"' and password = '" + passwd + "'";  
		List<User> userlist = new ArrayList<User>();
	    try {
	    	userlist = GlobalComponents.spmDB.getRunner().query(sql, new BeanListHandler<User>(User.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}  
	    if(userlist.size() == 1 ){
	    	return userlist.get(0);
	    }
		return null;
	}
	
	
}
