package com.pagechoice.lolth.entity;

public class LolthTaskDetail {
	
	private int id;	
	private String taskId;
	private String tableName;
	private String tableNameCn;
	private String classBean;
	private String downloadUrl;
	private String main;
	private String order;
	private String remark;
	private LolthTask lolthTask; 
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getTableNameCn() {
		return tableNameCn;
	}
	public void setTableNameCn(String tableNameCn) {
		this.tableNameCn = tableNameCn;
	}
	public String getClassBean() {
		return classBean;
	}
	public void setClassBean(String classBean) {
		this.classBean = classBean;
	}
	public String getDownloadUrl() {
		return downloadUrl;
	}
	public void setDownloadUrl(String downloadUrl) {
		this.downloadUrl = downloadUrl;
	}
	public String getMain() {
		return main;
	}
	public void setMain(String main) {
		this.main = main;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public LolthTask getLolthTask() {
		return lolthTask;
	}
	public void setLolthTask(LolthTask lolthTask) {
		this.lolthTask = lolthTask;
	}
	
	
	
}
