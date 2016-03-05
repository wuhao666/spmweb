package com.pagechoice.lolth.entity;

import java.util.ArrayList;
import java.util.List;

public class LolthTask	 {
	
	private String taskId;
	private String taskName;
	private String taskType;
	private String data;
	private String webId;
	private String webName;
	private String webModule;
	private String taskNums;
	private String dataNums;
	private String startDate;
	private String endDate;
	private String webMath;
	private String remark;
	private String detailType;
	private String createDate;
	private String createUser;
	private List<LolthTaskDetail>  lolthTaskDetail = new ArrayList<LolthTaskDetail>();
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public String getTaskType() {
		return taskType;
	}
	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getWebId() {
		return webId;
	}
	public void setWebId(String webId) {
		this.webId = webId;
	}
	public String getWebName() {
		return webName;
	}
	public void setWebName(String webName) {
		this.webName = webName;
	}
	public String getWebModule() {
		return webModule;
	}
	public void setWebModule(String webModule) {
		this.webModule = webModule;
	}
	public String getTaskNums() {
		return taskNums;
	}
	public void setTaskNums(String taskNums) {
		this.taskNums = taskNums;
	}
	public String getDataNums() {
		return dataNums;
	}
	public void setDataNums(String dataNums) {
		this.dataNums = dataNums;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getWebMath() {
		return webMath;
	}
	public void setWebMath(String webMath) {
		this.webMath = webMath;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getDetailType() {
		return detailType;
	}
	public void setDetailType(String detailType) {
		this.detailType = detailType;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public List<LolthTaskDetail> getLolthTaskDetail() {
		return lolthTaskDetail;
	}
	public void setLolthTaskDetail(List<LolthTaskDetail> lolthTaskDetail) {
		this.lolthTaskDetail = lolthTaskDetail;
	}
	
	
	
	
}
