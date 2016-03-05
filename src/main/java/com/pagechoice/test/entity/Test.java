package com.pagechoice.test.entity;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import lakenono.db.DBBean;
import lakenono.db.annotation.Column;
import lakenono.db.annotation.DBTable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


@DBTable(name = "test_test")
@Data
@ToString(callSuper = false)
@EqualsAndHashCode(callSuper = false)
public class Test extends DBBean{
	
	@Column(selectColumn=true , columnAs="id")
	private String id;
	
	@Column(selectColumn=true , columnAs="标题")
	private String title;
	
	@Column(selectColumn=true , columnAs="名字")
	private String name;
	
	@Column(selectColumn=true , columnAs="链接")
	private String url;
	
	@Column(selectColumn=true , columnAs="文本")
	private String text;
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		String dbBean = "com.pagechoice.test.entity.Test";
		String taskId = "";
		Map map = new HashMap();
		//String str= DBBean.getSelectColumnDatas(Class.forName(dbBean), taskId, map,"1","10");
		//System.out.println(" >>>>> str : " + str );
		//String name = "";
		//Class<?> clazz = Class.forName("");
		//String[][] content = DBBean.getAll(clazz);
		//JSon
	}
	
}
