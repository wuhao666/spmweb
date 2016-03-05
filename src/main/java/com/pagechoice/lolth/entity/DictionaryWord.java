package com.pagechoice.lolth.entity;

import java.util.List;

public class DictionaryWord {
	
	private int dwId;
	private String name;
	private String createUser;
	private String createDate;
	private String isDelete;
	private List<DictionaryWordDetail> dictionaryWordDetail;
	public int getDwId() {
		return dwId;
	}
	public void setDwId(int dwId) {
		this.dwId = dwId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}
	public List<DictionaryWordDetail> getDictionaryWordDetail() {
		return dictionaryWordDetail;
	}
	public void setDictionaryWordDetail(List<DictionaryWordDetail> dictionaryWordDetail) {
		this.dictionaryWordDetail = dictionaryWordDetail;
	}
	
}
